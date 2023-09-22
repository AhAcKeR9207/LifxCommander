package control.lifx.Messages.Device;

import control.lifx.DataTypes.Payload;

public class GetGroup extends Payload {
	int code = 51;
	
	public GetGroup() {}
	
	public int getCode() {
		return code;
	}
}