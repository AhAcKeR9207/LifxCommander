package control.lifx.Messages.Device;

import control.lifx.DataTypes.Payload;

public class GetWifiFirmware extends Payload {
	int code = 18;
	
	public GetWifiFirmware() {}
	
	public int getCode() {
		return code;
	}
}