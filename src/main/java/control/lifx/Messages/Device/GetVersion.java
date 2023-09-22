package control.lifx.Messages.Device;

import control.lifx.DataTypes.Payload;

public class GetVersion extends Payload {
	int code = 32;
	
	public GetVersion() {}
	
	public int getCode() {
		return code;
	}
}