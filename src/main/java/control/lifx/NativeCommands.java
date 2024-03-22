package control.lifx;

import java.awt.Color;
import java.util.List;

/**
 * Despite the similarity in naming conventions, this class does not interact with the Java Native Interface.
 * Its purpose is to load the commands hashmap with default consumers.
 */
public class NativeCommands {
    /**
     * Creates a new Light object.
     * 
     * @param args A list of arguments for the command.
     */
    public void addLight(List<String> args) {
        // Getting parameters for a Light object.
        // Any parameters that are not required are given their default values.
        String lightName = args.get(0);
        String ipAddress = args.get(1);
        Color color = Color.WHITE;
        boolean state = true;

        // Checking if the color has been specified.
        int colorPos = args.indexOf("-c");
        if (colorPos > -1) {
            // Converts the RGB string to an awt Color class.
            String rgbStr = args.get(colorPos + 1).replace("#", "");
            int rgbInts = Integer.parseInt(rgbStr, 16);
            color = new Color(rgbInts & 0xFF, (rgbInts >> 8) & 0xFF, (rgbInts >> 16) & 0xFF);
        }

        // Checking if the help tag has been used.
        int helpPos = args.indexOf("-h");
        if (helpPos > -1) {
            // Prints the help message and returns to the InputManager.
            helpMsg("addLight");
            return;
        }

        int statePos = args.indexOf("-s");
        if (statePos > -1) {

            switch (args.get(statePos + 1).toLowerCase()) {
                case "1":
                case "true":
                    state = true;
                    break;
                case "0":
                case "false":
                    state = false;
                    break;
            }
        }

        Dynamic.lightDict.put(lightName, new Light(ipAddress, color, state));
    }



    public void helpMsg(String command) {
        
    }
}