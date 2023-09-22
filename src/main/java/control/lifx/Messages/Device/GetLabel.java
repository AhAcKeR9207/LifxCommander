package control.lifx.Messages.Device;

import control.lifx.DataTypes.Payload;

public class GetLabel extends Payload {
	int code = 23;
	
	public GetLabel() {}
	
	public int getCode() {
		return code;
	}
}