package control.lifx.Objects;

import java.awt.Color;
import java.util.ArrayList;

public class Pattern extends Aliasable {
    // Instance variables
    private ArrayList<String> lightNames;
    private int brightness;
    private Color color;
    private double duration;
    private boolean state;

    /**
     * Creates a new Pattern object.
     * This class allows users to make a group of lights follow the same command for a certain amount of time.
     * 
     * @param lightNames An arraylist of the names of every light that this Pattern will use.
     * @param brightness The brightness each light will be while the pattern is running.
     * @param color The color each light will be while the pattern is running.
     * @param duration The amount of time the pattern will run in seconds.
     * @param state The state each light will be while the pattern is running.
     */
    public Pattern(ArrayList<String> lightNames, int brightness, Color color, double duration, boolean state) {
        this.lightNames = lightNames;
        this.brightness = brightness;
        this.color = color;
        this.duration = duration;
        this.state = state;
    }

    /**
     * Sets the brightness of the Pattern object.
     * 
     * @param brightness The new brightness of the Pattern.
     */
    public void setBrightness(int brightness) {
        this.brightness = brightness;
    }

    /**
     * Sets the color of the Pattern object.
     * 
     * @param color The new color of the Pattern.
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Sets the duration of the Pattern object.
     * 
     * @param duration The new duration of the Pattern.
     */
    public void setDuration(double duration) {
        this.duration = duration;
    }

    /**
     * Sets the state of the Pattern object.
     * 
     * @param state The new state of the Pattern.
     */
    public void setState(boolean state) {
        this.state = state;
    }

    /**
     * Gets the brightness of the Pattern object.
     * 
     * @return The brightness of the Pattern.
     */
    public int getBrightness() {
        return brightness;
    }

    /**
     * Gets the color of the Pattern object.
     * 
     * @return The color of the Pattern.
     */
    public Color getColor() {
        return color;
    }

    /**
     * Gets the duration of the Pattern object.
     * 
     * @return The duration of the Pattern.
     */
    public double getDuration() {
        return duration;
    }

    /**
     * Gets the state of the Pattern object.
     * 
     * @return The state of the Pattern.
     */
    public boolean getState() {
        return state;
    }

    /**
     * Gets the array of light names of the Pattern object.
     * 
     * @return The light names of the Pattern.
     */
    public ArrayList<String> getLightNames() {
        return lightNames;
    }

    /**
     * Adds a light to the lightNames array.
     * 
     * @param lightName The name of the light to add.
     */
    public void addLight(String lightName) {
        if (lightNames.contains(lightName)) {
            // Make some custom errors (This will be something like LightAlreadyUsed)
            return;
        }

        lightNames.add(lightName);
    }

    /**
     * Removes a light from the lightNames array.
     * 
     * @param lightName The name of the light to remove.
     */
    public void delLight(String lightName) {
        lightNames.remove(lightName);
    }
}