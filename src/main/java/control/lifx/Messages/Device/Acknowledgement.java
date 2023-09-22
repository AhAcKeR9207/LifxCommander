package control.lifx.Messages.Device;

import control.lifx.DataTypes.Payload;

public class Acknowledgement extends Payload {
	int code = 45;
	
	public Acknowledgement() {}
	
	public int getCode() {
		return code;
	}
}