package control.lifx.Messages.Light;

import control.lifx.DataTypes.Payload;

public class GetPower_Light extends Payload{
	int code = 116;
	
	public GetPower_Light() {}
	
	public int getCode() {
		return code;
	}
}