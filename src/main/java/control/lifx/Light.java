package control.lifx;

import java.awt.Color;
import java.io.IOException;

import control.lifx.DataTypes.Command;
import control.lifx.DataTypes.HSBK;
import control.lifx.LifxCommander.ControlMethods;
import control.lifx.Messages.Light.SetColor;
import control.lifx.Messages.Light.SetPower_Light;

public class Light extends ControlMethods {
   private String ip;
   private Color color;
   private double brightness;
   private int state;
   
   public Light(String ip, Color color) {
      this.ip = ip;
      this.color = color;
      brightness = 1;
      state = Constants.Power.ON;

      updateColor();
      updateState();
   }
   
   public void setColor(Color color) {
      this.color = color;
      updateColor();
   }
   
   public void setBrightness(int brightness) {
      this.brightness = brightness / 100.0;
      updateColor();
   }
   
   public void setState(boolean state) {
      if (state) {
         this.state = Constants.Power.ON;
      } else {
         this.state = Constants.Power.OFF;
      }
      updateState();
   }
   
   public String getIP() {
      return this.ip;
   }
   
   public Color getColor() {
      return this.color;
   }

   public boolean getState() {
      if (this.state == 0) {
         return true;
      }
   
      return false;
   }
   
   private void updateColor() {
      try {
         Color newColor = new Color(
            Math.max((int)(this.color.getRed() * brightness), 0),
            Math.max((int)(this.color.getGreen() * brightness), 0),
            Math.max((int)(this.color.getBlue() * brightness), 0)
         );
         
         HSBK hsbk = HSBK.RGBtoHSBK(this.color);
         hsbk.setBrightness((int) (this.brightness * 100));
         SetColor updateColor = new SetColor(hsbk);
         Command updateCommand = new Command(updateColor);
         sendUdpMessage(updateCommand.getByteArray(), this.ip, Constants.PORT);
      } catch (IOException e) {}
   }
   
   private void updateState() {
      try {
         SetPower_Light updatePower = new SetPower_Light(this.state);
         Command updatePowerCommand = new Command(updatePower);
         sendUdpMessage(updatePowerCommand.getByteArray(), this.ip, Constants.PORT);
      } catch (IOException e) {}
   }
}