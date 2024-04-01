package control.lifx.Objects;

import static control.lifx.LifxCommander.ControlMethods.sendUdpMessage;

import java.awt.Color;
import java.io.IOException;

import control.lifx.Constants;
import control.lifx.DataTypes.Command;
import control.lifx.DataTypes.HSBK;
import control.lifx.Messages.Light.SetColor;
import control.lifx.Messages.Light.SetPower_Light;

public class Light { // extends Aliasable {
    // Instance variables
    private String ip;
    private Color color;
    private int brightness;
    private boolean state;
    
    /**
     * Creates a new Light object.
     * This class allows users to change the color, brightness, and state of a LIFX light on the local network.
     * 
     * @param ip The IP address of the LIFX bulb. (EX: "192.168.1.1")
     * @param color The color of the LIFX bulb. (Use the {@link java.awt.Color Color} class as the parameter.)
     * @param brightness The brightness of the LIFX bulb. (EX: 100)
     * @param state The state of the LIFX bulb. (EX: true)
     */
    public Light(String ip, Color color, int brightness, boolean state) {
        this.ip = ip;
        this.color = color;
        this.brightness = brightness;
        this.state = state;

        update();
    }

    /**
     * Sets the brightness of the LIFX bulb.
     * Updates the state of the light immediately.
     * 
     * @param brightness The new brightness of the LIFX bulb. (EX: 100)
     */
    public void setBrightness(int brightness) {
        this.brightness = brightness;
        update();
    }
    
    /**
     * Sets the color of the LIFX bulb.
     * Updates the state of the light immediately.
     * 
     * @param color The new color of the LIFX bulb. (Use the {@link java.awt.Color Color} class.)
     */
    public void setColor(Color color) {
        this.color = color;
        update();
    }

    /**
     * Sets the IP address of the LIFX bulb.
     * Updates the state of the light immediately.
     * 
     * @param ip The new IP address of the LIFX bulb. (EX: "192.168.1.1")
     */
    public void setIP(String ip) {
        this.ip = ip;
        update();
    }
    
    /**
     * Sets the state of the LIFX bulb.
     * Updates the state of the light immediately.
     * 
     * @param state The new state of the LIFX bulb. (EX: true)
     */
    public void setState(boolean state) {
        this.state = state;
        update();
    }

    /**
     * Gets the brightness of the LIFX bulb.
     * 
     * @return The brightness of the LIFX bulb.
     */
    public int getBrightness() {
        return this.brightness;
    }
    
    /**
     * Gets the color of the LIFX bulb.
     * 
     * @return The color of the LIFX bulb.
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Gets the IP address of the LIFX bulb.
     * 
     * @return The IP address of the LIFX bulb.
     */
    public String getIP() {
        return this.ip;
    }

    /**
     * Gets the state of the LIFX bulb.
     * 
     * @return The state of the LIFX bulb.
     */
    public boolean getState() {
        return this.state;
    }

    /** Sends a message to the LIFX bulb with its new data (state, color, brightness, etc). */
    public void update() {
        // Getting the HSBK color from the RGB string and adjusting the brightness.
        HSBK newColor = HSBK.RGBtoHSBK(color);
        newColor.setBrightness((int) (brightness / 100.0 * Constants.Levels.MAX));

        // Creating the payloads for color and state
        SetColor updateColor = new SetColor(newColor);
        SetPower_Light updateState = new SetPower_Light(this.state ? Constants.Levels.MIN : Constants.Levels.MAX);

        // Creating the commands from each payload
        Command updateColorCommand = new Command(updateColor);
        Command updateStateCommand = new Command(updateState);

        // Sending the UDP packets
        try {
            sendUdpMessage(updateColorCommand.getByteArray(), ip, Constants.PORT);
            sendUdpMessage(updateStateCommand.getByteArray(), ip, Constants.PORT);
        } catch (IOException err) {}
    }
}