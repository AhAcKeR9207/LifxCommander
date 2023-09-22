package control.lifx.Messages.Device;

import java.math.BigInteger;
import java.time.Instant;
import java.util.Random;

import control.lifx.DataTypes.Payload;

public class StateLocation extends Payload {
	int code = 50;
	byte[] location;			// 16-Bytes
	String label;				// 32-Bytes
	BigInteger updated_at;		// 64-Bits (Unsigned)
	
	public StateLocation() {
		location = new byte[16];
		new Random().nextBytes(location);
		label = "N/A";
		
		updated_at = BigInteger.valueOf(0L);
	}
	
	public StateLocation(String label) {
		location = new byte[16];
		new Random().nextBytes(location);
		this.label = label;
		
		updated_at = BigInteger.valueOf(0L);
	}
	
	public StateLocation(SetLocation setLocation) {
		location = setLocation.location;
		label = setLocation.label;
		
		BigInteger millis = BigInteger.valueOf(Instant.now().toEpochMilli());
		updated_at = millis.multiply(BigInteger.valueOf(1000000L));
	}
	
	public int getCode() {
		return code;
	}
	
	public byte[] getLocation() {
		return location;
	}
	
	public void setLocation(byte[] location) {
		this.location = location;
	}
	
	public String getLabel() {
		return label;
	}
	
	public void setLabel(String label) {
		this.label = label;
	}
	
	public BigInteger getUpdatedAt() {
		return updated_at;
	}
	
	public void setUpdatedAt(BigInteger updated_at) {
		this.updated_at = updated_at;
	}
	
	public void setUpdatedAtToNow() {
		BigInteger millis = BigInteger.valueOf(Instant.now().toEpochMilli());
		updated_at = millis.multiply(BigInteger.valueOf(1000000L));
	}
	
	public void setFromCommandByteArray(byte[] byteArray) {
		for(int i = 51; i > 35; i--) location[(-1 * i) + 51] = byteArray[i];
		
		byte[] labelBytes = new byte[32];
		for(int i = 52; i < 84; i++) labelBytes[i - 52] = byteArray[i];
		label = new String(labelBytes);
		
		byte[] updatedAtBytes = new byte[8];
		for(int i = 91; i > 83; i--) updatedAtBytes[(-1 * i) + 91] = byteArray[i];
		updated_at = new BigInteger(updatedAtBytes);
	}
}