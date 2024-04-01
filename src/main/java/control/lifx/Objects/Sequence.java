package control.lifx.Objects;

import java.util.ArrayList;

import control.lifx.Dynamic;

public class Sequence { // extends Aliasable {
    // Instance variables
    private ArrayList<String> patternNames;
    private double duration;

    /**
     * Creates a new PatternGroup object.
     * This class allows users to group patterns together so they run one after the other or all at once.
     * 
     * @param patternNames An arraylist of the names of every Pattern that this PatternGroup will use.
     * @param type The type of PatternGroup this object is. (Sequential is everything one after the other, Parallel is all at once)
     */
    public Sequence(ArrayList<String> patternNames, double duration) {
        this.patternNames = patternNames;
        this.duration = duration;
    }

    /**
     * Sets the duration of the Sequence object.
     * 
     * @param duration The new duration of the Sequence.
     */
    public void setDuration(double duration) {
        this.duration = duration;
    }

    /**
     * Gets the duration of the Sequence object.
     * 
     * @return The duration of the Sequence.
     */
    public double getDuration() {
        return duration;
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

    /** Runs the sequence. */
    public void runSequence() {
        double tempDuration = duration;
        // Parses every pattern name in the patternNames ArrayList.
        for (String patternName : patternNames) {
            // If the patternName is an alias, then find its identity.
            // if (Dynamic.aliasDict.containsKey(patternName)) {
            //     Aliasable identity = findAliasIdentity(patternName);
            // 
            //     // This finds the object that the alias points to.
            //     switch (identity) {
            //         case PatternGroup pg:
            //             // If duration is 0, then it hasn't been set.  This gets the duration from the pattern group.
            //             if (tempDuration == 0) tempDuration = pg.getDuration();
            // 
            //             // Parsing all of the patterns in the PatternGroup.
            //             for (String pattern : pg.getPatternNames()) {
            //                 // If duration is 0, then it hasn't been set.  This gets the duration from the pattern group.
            //                 if (tempDuration == 0) tempDuration = Dynamic.patternDict.get(pattern).getDuration();
            // 
            //                 // Parsing all of the lights in each pattern
            //                 for (String light : Dynamic.patternDict.get(pattern).getLightNames()) {
            //                     Dynamic.lightDict.get(light).update();
            //                 }
            //             }
            //             break;
            // 
            //         case Pattern p:
            //             // If duration is 0, then it hasn't been set.  This gets the duration from the pattern.
            //             if (tempDuration == 0) tempDuration = p.getDuration();
            // 
            //             // Parsing all of the lights in the Pattern.
            //             for (String light : p.getLightNames()) {
            //                 Dynamic.lightDict.get(light).update();
            //             }
            //             break;
            // 
            //         // There shouldn't be any lights or other sequences in the patternNames array, so they are ignored.
            //         default:
            //             break;
            //     }
            // }

            // Checks if the name corresponds with a Pattern or PatternGroup
            if (Dynamic.patternDict.containsKey(patternName)) {
                // Handling the pattern
                Pattern pattern = Dynamic.patternDict.get(patternName);
                for (String lightName : pattern.getLightNames()) {
                    // Getting each light from the pattern
                    Light light = Dynamic.lightDict.get(lightName);
                }
            } else if (Dynamic.patternGroupDict.containsKey(patternName)) {
                // Handling the pattern group
                PatternGroup patternGroup = Dynamic.patternGroupDict.get(patternName);
                for (String pattern : patternGroup.getPatternNames()) {
                    // Getting each pattern from the pattern group
                    for (String lightName : Dynamic.patternDict.get(pattern).getLightNames()) {
                        // Getting each light from the pattern
                        Light light = Dynamic.lightDict.get(lightName);
                        // Updating the light's state
                        light.update();
                    }
                }

            } else {
                // Somehow, something other than a Pattern or PatternGroup was put into the array.
                // This could throw a "PatternNotFound" exception.
            }

            // Pausing the thread in accordance with the duration.
            try {
                Thread.sleep((long) tempDuration * 1000);
            } catch (InterruptedException err) {}
        }
    }
}