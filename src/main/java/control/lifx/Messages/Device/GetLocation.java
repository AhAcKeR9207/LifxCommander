package control.lifx.Messages.Device;

import control.lifx.DataTypes.Payload;

public class GetLocation extends Payload {
	int code = 48;
	
	public GetLocation() {}
	
	public int getCode() {
		return code;
	}
}