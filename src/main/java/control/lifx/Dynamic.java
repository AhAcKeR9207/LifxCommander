package control.lifx;

import java.util.HashMap;
import java.util.function.Consumer;

import control.lifx.Objects.Light;
import control.lifx.Objects.Pattern;
import control.lifx.Objects.PatternGroup;
import control.lifx.Objects.Sequence;

/**
 * Think of this class as a Constants class, but it contains variables that can be changed during runtime.
 */
public class Dynamic {
    public static HashMap<String, Consumer<String[]>> commands = new HashMap<String, Consumer<String[]>>();
    public static HashMap<String, Light> lightDict = new HashMap<String, Light>();
    public static HashMap<String, Pattern> patternDict = new HashMap<String, Pattern>();
    public static HashMap<String, PatternGroup> patternGroupDict = new HashMap<String, PatternGroup>();
    public static HashMap<String, Sequence> sequenceDict = new HashMap<String, Sequence>();
    // public static HashMap<String, String> aliasDict = new HashMap<String, String>();
}
