package control.lifx.Messages.Light;

import control.lifx.DataTypes.Payload;

public class Get extends Payload{
	int code = 101;
	
	public Get() {}
	
	public int getCode() {
		return code;
	}
}