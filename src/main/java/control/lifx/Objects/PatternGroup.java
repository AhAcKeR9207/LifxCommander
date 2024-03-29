package control.lifx.Objects;

import java.util.ArrayList;

public class PatternGroup extends Aliasable {
    /**
     * These are the enums that determine what type of PatternGroup is running.
     */
    public enum PatternType {
        Parallel,
        Sequential
    }

    // Instance variables
    private ArrayList<String> patternNames;
    private double duration;
    private PatternType type;

    /**
     * Creates a new PatternGroup object.
     * This class allows users to group patterns together so they run one after the other or all at once.
     * 
     * @param patternNames An arraylist of the names of every Pattern that this PatternGroup will use.
     * @param type The type of PatternGroup this object is. (Sequential is everything one after the other, Parallel is all at once)
     */
    public PatternGroup(ArrayList<String> patternNames, PatternType type) {
        this.patternNames = patternNames;
        this.type = type;
    }

    /**
     * Sets the duration of the PatternGroup object.
     * 
     * @param duration The new duration of this PatternGroup
     */
    public void setDuration(double duration) {
        this.duration= duration;
    }

    /**
     * Sets the type of the PatternGroup object.
     * 
     * @param type The new type of pattern of this PatternGroup
     */
    public void setType(PatternType type) {
        this.type = type;
    }

    /**
     * Gets the duration of the PatternGroup object.
     * 
     * @return The duration of the PatternGroup.
     */
    public double getDuration() {
        return duration;
    }

    /**
     * Gets the type of the PatternGroup object.
     * 
     * @return The type of the PatternGroup.
     */
    public PatternType getType() {
        return type;
    }

    /**
     * Gets the array of pattern names of the PatternGroup object.
     * 
     * @return The pattern names of the PatternGroup.
     */
    public ArrayList<String> getPatternNames() {
        return patternNames;
    }

    /**
     * Adds a pattern to the patternNames array.
     * 
     * @param patternName The name of the pattern to add.
     */
    public void addPattern(String patternName) {
        if (patternNames.contains(patternName)) {
            // Make some custom errors (This will be something like PatternAlreadyUsed)
            return;
        }

        patternNames.add(patternName);
    }

    /**
     * Removes a pattern from the patternNames array.
     * 
     * @param patternName The name of the pattern to remove.
     */
    public void delPattern(String patternName) {
        patternNames.remove(patternName);
    }
}