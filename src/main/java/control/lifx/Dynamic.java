package control.lifx;

import java.util.HashMap;
import java.util.function.Consumer;

/**
 * Think of this class as a Constants class, but it contains variables that can be changed during runtime.
 */
public class Dynamic {
    public static HashMap<String, Consumer<String[]>> commands = new HashMap<String, Consumer<String[]>>();
    public static HashMap<String, Light> lightDict = new HashMap<String, Light>();
}
