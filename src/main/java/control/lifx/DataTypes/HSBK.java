package control.lifx.DataTypes;

import java.awt.Color;

import control.lifx.Constants;

public class HSBK {
	public int hue;			// 16-Bits
	public int saturation;	// 16-Bits
	public int brightness;	// 16-Bits
	public int kelvin;		// 16-Bits
	
	public HSBK() {
		this.hue = 0;
		this.saturation = Constants.Levels.MAX;
		this.brightness = Constants.Levels.MAX;
		this.kelvin = Constants.Kelvin.MEDIUM;
	}
	
	public HSBK(int hue, int saturation, int brightness, int kelvin) {
		this.hue = hue;					// EX: 43713.6777
		this.saturation = saturation;	// EX: 65535
		this.brightness = brightness;	// EX: 65535
		this.kelvin = kelvin;			// EX: 4000
	}
	
	public HSBK(HSBK hsbk) {
		this.hue = hsbk.hue;
      	this.saturation = hsbk.saturation;
		this.brightness = hsbk.brightness;
		this.kelvin = hsbk.kelvin;
	}

	// Converts RGB color scheme to HSBK color scheme.
	public static HSBK RGBtoHSBK (Color rgb) {
		float[] hsb = Color.RGBtoHSB(rgb.getRed(), rgb.getGreen(), rgb.getBlue(), null);
  
		int hue = (int) (hsb[0] * 65535);
		int saturation = (int) (hsb[1] * 65535);
		int brightness = (int) (hsb[2] * 65535);
		int kelvin = 4000;
  
		HSBK hsbk = new HSBK(hue, saturation, brightness, kelvin);
		return hsbk;
	}
	
	public int getHue() {
		return hue;
	}
	
	public void setHue(int hue) {
		this.hue = hue;
	}
	
	public int getSaturation() {
		return saturation;
	}
	
	public void setSaturation(int saturation) {
		this.saturation = saturation;
	}
	
	public int getBrightness() {
		return brightness;
	}
	
	public void setBrightness(int brightness) {
		this.brightness = brightness;
	}
	
	public int getKelvin() {
		return kelvin;
	}
	
	public void setKelvin(int kelvin) {
		this.kelvin = kelvin;
	}	
}