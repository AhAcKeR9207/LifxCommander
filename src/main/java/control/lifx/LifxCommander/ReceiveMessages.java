package control.lifx.LifxCommander;

import java.io.IOException;
import java.math.BigInteger;
import java.net.InetAddress;

import control.lifx.DataTypes.Command;
import control.lifx.Messages.Device.EchoResponse;
import control.lifx.Messages.Device.StateGroup;
import control.lifx.Messages.Device.StateHostFirmware;
import control.lifx.Messages.Device.StateHostInfo;
import control.lifx.Messages.Device.StateInfo;
import control.lifx.Messages.Device.StateLabel;
import control.lifx.Messages.Device.StateLocation;
import control.lifx.Messages.Device.StatePower_Device;
import control.lifx.Messages.Device.StateService;
import control.lifx.Messages.Device.StateVersion;
import control.lifx.Messages.Device.StateWifiFirmware;
import control.lifx.Messages.Device.StateWifiInfo;
import control.lifx.Messages.Light.StateInfrared;
import control.lifx.Messages.Light.StatePower_Light;
import control.lifx.Messages.Light.State_Light;

public class ReceiveMessages extends Thread {
	int port = 56700;
	
	public void run() {
		try {
			System.out.println("Listening on udp:" + InetAddress.getLocalHost().getHostAddress() + ":56700");
			while(true) {
				byte[] byteArray = ControlMethods.receiveUdpMessage();
				Command command = new Command();
				command.setFromCommandByteArray(byteArray);

				if(command.getProtocol().getType() == 3) {
					System.out.println("StateService------------------------------------------------");
					System.out.println("     Service: " + ((StateService)command.getPayload()).getService());
					System.out.println("     Port: " + ((StateService)command.getPayload()).getPort());
				} else if(command.getProtocol().getType() == 13) {
					System.out.println("StateHostInfo----------------------------------------------------");
					System.out.println("     Signal Strength: " + ((StateHostInfo)command.getPayload()).getSignal() + "mW");
					System.out.println("     tx: " + ((StateHostInfo)command.getPayload()).getTx() + " bytes");
					System.out.println("     rx: " + ((StateHostInfo)command.getPayload()).getRx() + " bytes");
				} else if(command.getProtocol().getType() == 15) {
					int major = (int) (((((StateHostFirmware)command.getPayload()).getVersion()) & 0xffff0000) >> 16);
					int minor = (int) (((StateHostFirmware)command.getPayload()).getVersion() & 0xffff);

					System.out.println("StateHostFirmware------------------------------------------------");
					System.out.println("     Build Date: " + CommonMethods.getDateAsString(((StateHostFirmware)command.getPayload()).getBuild()));
					System.out.println("     Version: " + ((StateHostFirmware)command.getPayload()).getVersion());
					System.out.println("     Version: " + major + "." + minor);
				} else if(command.getProtocol().getType() == 17) {
					System.out.println("StateWifiInfo----------------------------------------------------");
					System.out.println("     Signal Strength: " + ((StateWifiInfo)command.getPayload()).getSignal() + "mW");
					System.out.println("     tx: " + ((StateWifiInfo)command.getPayload()).getTx() + " bytes");
					System.out.println("     rx: " + ((StateWifiInfo)command.getPayload()).getRx() + " bytes");
				} else if(command.getProtocol().getType() == 19) {
					System.out.println("StateWifiFirmware------------------------------------------------");
					System.out.println("     Build Date: " + CommonMethods.getDateAsString(((StateWifiFirmware)command.getPayload()).getBuild()));
					System.out.println("     Version: " + ((StateWifiFirmware)command.getPayload()).getVersion());
				} else if(command.getProtocol().getType() == 22) {
					System.out.println("StatePower_Device------------------------------------------------");
					System.out.println("     Level: " + ((StatePower_Device)command.getPayload()).getLevel());
				} else if(command.getProtocol().getType() == 25) {
					System.out.println("StateLabel-------------------------------------------------------");
					System.out.println("     Label: " + ((StateLabel)command.getPayload()).getLabel());
				} else if(command.getProtocol().getType() == 33) {
					System.out.println("StateVersion-----------------------------------------------------");
					System.out.println("     Vendor: " + ((StateVersion)command.getPayload()).getVendor());
					System.out.println("     Product: " + ((StateVersion)command.getPayload()).getProduct());
					System.out.println("     Version: " + ((StateVersion)command.getPayload()).getVersion());
				} else if(command.getProtocol().getType() == 35) {
					System.out.println("StateInfo--------------------------------------------------------");
					System.out.println("     Time: " + CommonMethods.getDateAsString(((StateInfo)command.getPayload()).getTime()));
					System.out.println("     Uptime: " + ((StateInfo)command.getPayload()).getUptime().divide(BigInteger.valueOf(1000000000L)) + "s");
					System.out.println("     Downtime: " + ((StateInfo)command.getPayload()).getDowntime().divide(BigInteger.valueOf(1000000000L)) + "s");
				} else if(command.getProtocol().getType() == 45) {
					System.out.println("Acknowledged---------------------------------------------------");
				} else if(command.getProtocol().getType() == 50) {
					System.out.println("StateLocation----------------------------------------------------");
					System.out.println("     Label: " + ((StateLocation)command.getPayload()).getLabel());
					System.out.println("     Time: " + CommonMethods.getDateAsString(((StateLocation)command.getPayload()).getUpdatedAt()));
				} else if(command.getProtocol().getType() == 53) {
					System.out.println("StateGroup--------------------------------------------------------");
					System.out.println("     Label: " +((StateGroup)command.getPayload()).getLabel());
					System.out.println("     Time: " + CommonMethods.getDateAsString(((StateGroup)command.getPayload()).getUpdatedAt()));
				} else if(command.getProtocol().getType() == 59) {
					System.out.println("EchoResponse------------------------------------------------------");
					System.out.println("     Payload: " + CommonMethods.getHexValueAsString(((EchoResponse)command.getPayload()).getPayload()));
				} else if(command.getProtocol().getType() == 107) {
					System.out.println("State------------------------------------------------------------");
					System.out.println("     Hue: " + ((State_Light)command.getPayload()).getColor().getHue());
					System.out.println("     Saturation: " + ((State_Light)command.getPayload()).getColor().getSaturation());
					System.out.println("     Brightness: " + ((State_Light)command.getPayload()).getColor().getBrightness());
					System.out.println("     Kelvin: " + ((State_Light)command.getPayload()).getColor().getKelvin());
					System.out.println("     Power: " + ((State_Light)command.getPayload()).getPower());				
					System.out.println("     Label: " + ((State_Light)command.getPayload()).getLabel());
				} else if(command.getProtocol().getType() == 118) {
					System.out.println("StatePower_Light------------------------------------------------");
					System.out.println("     Level: " + ((StatePower_Light)command.getPayload()).getLevel());
				} else if(command.getProtocol().getType() == 121) {
					System.out.println("StateInfrared---------------------------------------------------");
					System.out.println("     Brightness = " + ((StateInfrared)command.getPayload()).getBrightness());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}