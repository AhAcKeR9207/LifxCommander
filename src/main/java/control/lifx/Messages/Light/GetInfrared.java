package control.lifx.Messages.Light;

import control.lifx.DataTypes.Payload;

public class GetInfrared extends Payload {
	int code = 120;
	
	public GetInfrared() {}
	
	public int getCode() {
		return code;
	}
}