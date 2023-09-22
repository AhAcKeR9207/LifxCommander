package control.lifx.LifxCommander;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.NetworkInterface;
import java.net.InterfaceAddress;
import java.net.InetAddress;
import java.net.DatagramPacket;
import java.util.Enumeration;

public class ControlMethods {
	public static int port = 56700;
	
	public static void sendBroadcastMessage(byte[] messageByteArray) throws IOException {
		DatagramSocket socket = new DatagramSocket();
		socket.setBroadcast(true);
		
		Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
		while(interfaces.hasMoreElements()) {
			NetworkInterface networkInterface = interfaces.nextElement();
			
			if(networkInterface.isLoopback() || !networkInterface.isUp()) {
				continue;
			}
			
			for(InterfaceAddress interfaceAddress : networkInterface.getInterfaceAddresses()) {
				InetAddress broadcast = interfaceAddress.getBroadcast();
				if(broadcast == null) continue;
			
				DatagramPacket packet = new DatagramPacket(messageByteArray, messageByteArray.length, broadcast, port);
				socket.send(packet);
			}
		}
		socket.close();
	}
	
	public static void sendUdpMessage(String message, String ipAddress) throws IOException {
		byte[] messageByteArray = CommonMethods.convertHexStringToByteArray(message);
		InetAddress address = InetAddress.getByName(ipAddress);
		DatagramPacket packet = new DatagramPacket(messageByteArray, messageByteArray.length, address, port);
		DatagramSocket socket = new DatagramSocket();
		socket.send(packet);
		socket.close();	
	}
	
	public static void sendUdpMessage(byte[] messageByteArray, String ipAddress) throws IOException {
		InetAddress address = InetAddress.getByName(ipAddress);
		DatagramPacket packet = new DatagramPacket(messageByteArray, messageByteArray.length, address, port);
		DatagramSocket socket = new DatagramSocket();
		socket.send(packet);
		socket.close();	
	}
	
	public static byte[] receiveUdpMessage() throws IOException{
		DatagramSocket socket = new DatagramSocket(port);
		byte[] data = new byte[1500];
			
		DatagramPacket packet = new DatagramPacket(data, data.length);
		while(true) {
			socket.receive(packet);
			socket.close();
			
			return packet.getData();
		}
	}	
}