package control.lifx.Messages.Device;

import control.lifx.DataTypes.Payload;

public class GetService extends Payload {
	int code = 2;
	
	public GetService() {}
	
	public int getCode() {
		return code;
	}
}