package control.lifx.Messages.Device;

import control.lifx.DataTypes.Payload;

public class GetHostInfo extends Payload {
	int code = 12;
	
	public GetHostInfo() {}
	
	public int getCode() {
		return code;
	}
}