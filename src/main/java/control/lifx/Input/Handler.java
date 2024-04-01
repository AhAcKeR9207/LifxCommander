package control.lifx.Input;

import static control.lifx.Dynamic.lightDict;
import static control.lifx.Dynamic.patternDict;
import static control.lifx.Dynamic.patternGroupDict;
import static control.lifx.Dynamic.sequenceDict;

import java.awt.Color;
import java.util.ArrayList;

import control.lifx.Dynamic;
import control.lifx.Constants.Defaults;
import control.lifx.Objects.Light;
import control.lifx.Objects.Pattern;
import control.lifx.Objects.PatternGroup;
import control.lifx.Objects.Sequence;

/**
 * Despite the similarity in naming conventions, this class does not interact with the Java Native Interface.
 * Its purpose is to load the commands hashmap with default consumers.
 */
public class Handler {
    /**
     * Creates a new Light object.
     * 
     * @param args A list of arguments from the command.
     */
    public static void addLight(ArrayList<String> args) {
        // Getting parameters for a Light object.
        // Any parameters that are not required are given their default values.
        String lightName = args.get(0);
        String ipAddress = args.get(1);
        Color color = Defaults.COLOR;
        int brightness = Defaults.BRIGHTNESS;
        boolean state = Defaults.STATE;

        // Checking if the help tag has been used.
        int helpPos = args.indexOf("-h");
        if (helpPos > -1) {
            // Printing the help message and returns to the InputManager.
            helpMsg("addLight");
            return;
        }

        // Checking if the brightness has been specified.
        int brightnessPos = args.indexOf("-b");
        if (brightnessPos > -1) {
            // Changing the brightness to the specified value.
            brightness = Integer.parseInt(args.get(brightnessPos + 1));
        }

        // Checking if the color has been specified.
        int colorPos = args.indexOf("-c");
        if (colorPos > -1) {
            // Getting the rgb int
            int rgb = Integer.parseInt(args.get(colorPos + 1).replaceAll("#", ""));

            // Changing the color to the specified value.
            color = new Color(rgb);
        }

        // Checking if the state has been specified.
        int statePos = args.indexOf("-s");
        if (statePos > -1) {
            // Switching the value of the state command.
            switch (args.get(statePos + 1).toLowerCase()) {
                case "1", "true":
                    state = true;
                    break;
                case "0", "false":
                    state = false;
                    break;
            }
        }

        // Adding the new light to the dictionary in Dynamic.
        lightDict.put(lightName, new Light(ipAddress, color, brightness, state));
    }

    /**
     * Modifies an existing light object.
     * 
     * @param args A list of arguments from the command.
     */
    public static void modLight(ArrayList<String> args) {
        // Getting the name of the light.
        String lightName = args.get(0);

        // Getting the light from lightDict.
        Light light = lightDict.get(lightName);
        
        // Checking if the help tag has been used.
        int helpPos = args.indexOf("-h");
        if (helpPos > -1) {
            // Printing the help message and returning to the InputManager.
            helpMsg("modLight");
            return;
        }

        // Checking if the brightness has been specified.
        int brightnessPos = args.indexOf("-b");
        if (brightnessPos > -1) {
            // Changing the brightness to the specified value
            light.setBrightness(Integer.parseInt(args.get(brightnessPos + 1)));
        }

        // Checking if the color has been specified.
        int colorPos = args.indexOf("-c");
        if (colorPos > -1) {
            // Getting the rgb int
            int rgb = Integer.parseInt(args.get(colorPos + 1).replaceAll("#", ""));

            // Changing the color to the specified value
            light.setColor(new Color(rgb));
        }

        // Checking if the IP address has been specified.
        int ipPos = args.indexOf("-i");
        if (ipPos > -1) {
            // Changing the IP address of the light.
            light.setIP(args.get(ipPos + 1));
        }

        // Checking if the name has been specified.
        int namePos = args.indexOf("-n");
        if (namePos > -1) {
            // Changing the name of the light.
            lightDict.remove(lightName);
            lightDict.put(args.get(namePos + 1), light);
        }

        // Checking if the state has been specified.
        int statePos = args.indexOf("-s");
        if (statePos > -1) {
            // Changing the state of the light.
            switch (args.get(statePos + 1).toLowerCase()) {
                case "1", "true":
                    light.setState(true);
                    break;
                case "0", "false":
                    light.setState(false);
                    break;
            }
        }
    }

    /**
     * Deletes an existing light object.
     * 
     * @param args A list of arguments for the command.
     */
    public static void delLight(ArrayList<String> args) {
        // Getting required parameters from args.
        String lightName = args.get(0);

        // Checking if the help tag has been used.
        int helpPos = args.indexOf("-h");
        if (helpPos > -1) {
            // Prints the help message and returns to the InputManager.
            helpMsg("delLight");
            return;
        }

        // Removing the light from the dictionary in Dynamic
        lightDict.remove(lightName);
    }

    /**
     * Creates a new Pattern object.
     * 
     * @param args A list of arguments for the command.
     */
    public static void addPattern(ArrayList<String> args) {
        // Getting required parameters from args.
        String patternName = args.get(0);

        // All other parameters are given their default values
        ArrayList<String> lightNames = new ArrayList<>();
        int brightness = Defaults.BRIGHTNESS;
        Color color = Defaults.COLOR;
        double duration = Defaults.DURATION;
        boolean state = Defaults.STATE;

        // Checking if the help tag has been used.
        int helpPos = args.indexOf("-h");
        if (helpPos > -1) {
            // Prints the help message and returns to the InputManager.
            helpMsg("addPattern");
            return;
        }

        // Checking if the add tag has been used.
        int addPos = args.indexOf("-a");
        // Since this tag can be repeated, we will make a loop to find all instances of it.
        while (addPos > -1) {
            // Adding the light names to the array.
            lightNames.add(args.get(addPos + 1));

            // Removing this add tag and its value
            args.remove(addPos);
            args.remove(addPos);

            // Checking for another add tag
            addPos = args.indexOf("-a");
        }

        // Checking if the brightness tag has been used.
        int brightnessPos = args.indexOf("-b");
        if (brightnessPos > -1) {
            // Changing the brightness to the specified value.
            brightness = Integer.parseInt(args.get(brightnessPos + 1));
        }

        // Checking if the color tag has been used.
        int colorPos = args.indexOf("-c");
        if (colorPos > -1) {
            // Getting the rgb int
            int rgb = Integer.parseInt(args.get(colorPos + 1).replaceAll("#", ""));

            // Changing the color to the specified value.
            color = new Color(rgb);
        }

        // Checking if the help tag has been used.
        int durationPos = args.indexOf("-d");
        if (durationPos > -1) {
            // Changing the duration to the specified value.
            duration = Integer.parseInt(args.get(durationPos + 1));
        }

        // Checking if the help tag has been used.
        int statePos = args.indexOf("-s");
        if (statePos > -1) {
            // Changing the state to the specified value.
            switch(args.get(statePos + 1)) {
                case "1", "true":
                    state = true;
                    break;
                case "0", "false":
                    state = false;
                    break;
            }
        }

        // Adding the new pattern to the dictionary in Dynamic.
        Dynamic.patternDict.put(patternName, new Pattern(lightNames, brightness, color, duration, state));
    }

    /**
     * 
     * 
     * @param args A list of arguments for the command.
     */
    public static void modPattern(ArrayList<String> args) {
        // Getting the name of the pattern.
        String patternName = args.get(0);

        // Getting the pattern from patternDict.
        Pattern pattern = patternDict.get(patternName);
        
        // Checking if the help tag has been used.
        int helpPos = args.indexOf("-h");
        if (helpPos > -1) {
            // Printing the help message and returning to the InputManager.
            helpMsg("modPattern");
            return;
        }
        
        // Checking if the add tag has been used.
        int addPos = args.indexOf("-a");
        // Since this tag can be repeated, we will make a loop to find all instances of it.
        while (addPos > -1) {
            // Adding the light names to the array.
            pattern.addLight(args.get(addPos + 1));

            // Removing this add tag and its value
            args.remove(addPos);
            args.remove(addPos);

            // Checking for another add tag
            addPos = args.indexOf("-a");
        }


        // Checking if the brightness has been specified.
        int brightnessPos = args.indexOf("-b");
        if (brightnessPos > -1) {
            // Changing the brightness to the specified value
            pattern.setBrightness(Integer.parseInt(args.get(brightnessPos + 1)));
        }

        // Checking if the color has been specified.
        int colorPos = args.indexOf("-c");
        if (colorPos > -1) {
            // Getting the rgb int
            int rgb = Integer.parseInt(args.get(colorPos + 1).replaceAll("#", ""));

            // Changing the color to the specified value
            pattern.setColor(new Color(rgb));
        }

        // Checking if the del tag has been used.
        int durationPos = args.indexOf("-d");
        // Since this tag can be repeated, we will make a loop to find all instances of it.
        if (durationPos > -1) {
            // Changing the pattern duration.
            pattern.setDuration(Integer.parseInt(args.get(durationPos + 1)));
        }

        // Checking if the name has been specified.
        int namePos = args.indexOf("-n");
        if (namePos > -1) {
            // Changing the name of the pattern.
            patternDict.remove(patternName);
            patternDict.put(args.get(namePos + 1), pattern);
        }

        // Checking if the remove tag has been used.
        int remPos = args.indexOf("-r");
        // Since this tag can be repeated, we will make a loop to find all instances of it.
        while (remPos > -1) {
            // Removing the light names from the array.
            pattern.delLight(args.get(remPos + 1));

            // Removing this remove tag and its value
            args.remove(remPos);
            args.remove(remPos);

            // Checking for another remove tag
            remPos = args.indexOf("-r");
        }

        // Checking if the state has been specified.
        int statePos = args.indexOf("-s");
        if (statePos > -1) {
            // Changing the state of the pattern.
            switch (args.get(statePos + 1).toLowerCase()) {
                case "1", "true":
                    pattern.setState(true);
                    break;
                case "0", "false":
                    pattern.setState(false);
                    break;
            }
        }
    }

    /**
     * 
     * @param args A list of arguments for the command.
     * 
     */
    public static void delPattern(ArrayList<String> args) {
        // Getting required parameters from args.
        String patternName = args.get(0);

        // Checking if the help tag has been used.
        int helpPos = args.indexOf("-h");
        if (helpPos > -1) {
            // Prints the help message and returns to the InputManager.
            helpMsg("delPattern");
            return;
        }

        // Removing the pattern from the dictionary in Dynamic
        patternDict.remove(patternName);
    }

    /**
     * 
     * 
     * @param args A list of arguments for the command.
     */
    public static void addPatternGroup(ArrayList<String> args) {

    }

    /**
     * 
     * 
     * @param args A list of arguments for the command.
     */
    public static void modPatternGroup(ArrayList<String> args) {

    }

    /**
     * 
     * 
     * @param args A list of arguments for the command.
     */
    public static void delPatternGroup(ArrayList<String> args) {
        // Getting required parameters from args.
        String patternGroupName = args.get(0);

        // Checking if the help tag has been used.
        int helpPos = args.indexOf("-h");
        if (helpPos > -1) {
            // Prints the help message and returns to the InputManager.
            helpMsg("delPatternGroup");
            return;
        }

        // Removing the patternGroup from the dictionary in Dynamic
        patternGroupDict.remove(patternGroupName);
    }

    /**
     * 
     * 
     * @param args A list of arguments for the command.
     */
    public static void addSequence(ArrayList<String> args) {

    }

    /**
     * 
     * 
     * @param args A list of arguments for the command.
     */
    public static void modSequence(ArrayList<String> args) {

    }

    /**
     * 
     * 
     * @param args A list of arguments for the command.
     */
    public static void delSequence(ArrayList<String> args) {
        // Getting required parameters from args.
        String sequenceName = args.get(0);

        // Checking if the help tag has been used.
        int helpPos = args.indexOf("-h");
        if (helpPos > -1) {
            // Prints the help message and returns to the InputManager.
            helpMsg("delSequence");
            return;
        }

        // Removing the sequence from the dictionary in Dynamic
        sequenceDict.remove(sequenceName);
    }

    /**
     * 
     * 
     * @param args A list of arguments for the command.
     */
    public static void runSequence(ArrayList<String> args) {

    }

    // /**
    //  * 
    //  * 
    //  * @param args A list of arguments for the command.
    //  */
    // public static void alias(ArrayList<String> args) {
    // 
    // }

    /** Prints all of the help messages for every command. */
    public static void help() {
        helpMsg("addLight");
        System.out.println();
        helpMsg("modLight");
        System.out.println();
        helpMsg("delLight");
        System.out.println();
        helpMsg("addPattern");
        System.out.println();
        helpMsg("modPattern");
        System.out.println();
        helpMsg("delPattern");
        System.out.println();
        helpMsg("addPatternGroup");
        System.out.println();
        helpMsg("modPatternGroup");
        System.out.println();
        helpMsg("delPatternGroup");
        System.out.println();
        helpMsg("addSequence");
        System.out.println();
        helpMsg("modSequence");
        System.out.println();
        helpMsg("delSequence");
        System.out.println();
        helpMsg("runSequence");
        System.out.println();
        // helpMsg("alias");
        // System.out.println();
        helpMsg("help");
        System.out.println();
    }


    public static void helpMsg(String command) {
        switch (command) {
            case "addLight":
                System.out.println("Command: addLight");
                System.out.println("Function: Creates a new light object.");
                System.out.println("Usage: addLight <lightName> <ipAddress> [options...]");
                System.out.println("        -b <brightness>    The brightness of the light (1 to 100)");
                System.out.println("        -c <color>         The new color of the light  (EX: \"#000000\")");
                System.out.println("        -h                 Displays this help message");
                System.out.println("        -s <state>         The new state of the light  (1, 0, \"true\", or \"false\")");
                break;
            
            case "modLight":
                System.out.println("Command: modLight");
                System.out.println("Function: Allows users to modify attributes of a light such as its name, IP, color, and state.");
                System.out.println("Usage: modLight <lightName> [options...]");
                System.out.println("        -b <brightness>    The new brightness of the light (1 to 100)");
                System.out.println("        -c <color>         The new color of the light      (EX: \"#000000\")");
                System.out.println("        -h                 Displays this help message");
                System.out.println("        -i <ipAddress>     The new IP Address of the light (EX: \"192.168.1.255\")");
                System.out.println("        -n <name>          The new name of the light       (EX: \"myLight\")");
                System.out.println("        -s <state>         The new state of the light      (1, 0, \"true\", or \"false\")");
                break;

            case "delLight":
                System.out.println("Command: delLight");
                System.out.println("Function: Deletes a light object.");
                System.out.println("Usage: delLight <lightName>");
                System.out.println("        -h    Displays this help message");
                break;

            case "addPattern":
                System.out.println("Command: addPattern");
                System.out.println("Function: Creates a new pattern object.");
                System.out.println("Usage: addPattern <patternName> [options...]");
                System.out.println("        -a <lightName>     The name of a light to add to the pattern (can be repeated)      (EX: \"myLight\")");
                System.out.println("        -b <brightness>    The brightness the light(s) will be while the pattern is running (1 to 100)");
                System.out.println("        -c <color>         The color the light(s) will be while the pattern is running      (EX: \"#000000\")");
                System.out.println("        -d <seconds>       The amount of time the pattern should run in seconds             (EX: 5)");
                System.out.println("        -h                 Displays this help message");
                System.out.println("        -s <state>         The state the light(s) will be while the pattern is running      (1, 0, \"true\", or \"false\")");
                break;

            case "modPattern":
                System.out.println("Command: modPattern");
                System.out.println("Function: Allows users to modify attributes of a pattern such as its name, color, affected lights, and more.");
                System.out.println("Usage: modPattern <patternName> [options...]");
                System.out.println("        -a <lightName>     The name of a light to add to the sequence (can be repeated)         (EX: \"myLight\")");
                System.out.println("        -b <brightness>    The new brightness the light(s) will be while the pattern is running (1 to 100)");
                System.out.println("        -c <color>         The new color the light(s) will be while the pattern is running      (EX: \"#000000\")");
                System.out.println("        -d <seconds>       The amount of time the pattern should run in seconds                 (EX: 5)");
                System.out.println("        -h                 Displays this help message");
                System.out.println("        -n <name>          The new name of the pattern                                          (EX: \"myPattern\")");
                System.out.println("        -r <lightName>     The name of a light to remove from the sequence (can be repeated)    (EX: \"myLight\")");
                System.out.println("        -s <state>         The state the lights will be while the pattern is running            (1, 0, \"true\", or \"false\")");
                break;

            case "delPattern":
                System.out.println("Command: delPattern");
                System.out.println("Function: Deletes a pattern object.");
                System.out.println("Usage: delPattern <patternName>");
                System.out.println("        -h    Displays this help message");
                break;

            case "addPatternGroup":
                System.out.println("Command: addPatternGroup");
                System.out.println("Function: Creates a new pattern group object.");
                System.out.println("Group Types:");
                System.out.println("    There are 2 valid group types, those being \"parallel\" and \"sequential\"");
                System.out.println("    The reason that the type parameter exists is so that users can run multiple patterns at once.");
                System.out.println("    If the user doesn't specify a valid type, then the type will default to sequential.");
                System.out.println("    Sequential mode: Runs each pattern one after the other, ending when the patterns finish or the duration of the pattern group expires.");
                System.out.println("Usage: addPatternGroup <groupName> [options...]");
                System.out.println("        -a <patternName>    The name of the patterns to add to the group (can be repeated)");
                System.out.println("        -d <seconds>        The amount of time the pattern group should run in seconds (overrides any previous times set)");
                System.out.println("        -h                  Displays this help message");
                System.out.println("        -t <groupType>      The new type of the pattern group");
                break;

            case "modPatternGroup":
                System.out.println("Command: modPatternGroup");
                System.out.println("Function: Allows users to modify attributes of a pattern group such as its name, type, affected patterns, and more.");
                System.out.println("Group Types:");
                System.out.println("    There are 2 valid group types, those being \"parallel\" and \"sequential\"");
                System.out.println("    The reason that the type parameter exists is so that users can run multiple patterns at once.");
                System.out.println("    If the user doesn't specify a valid type, then the type will default to sequential.");
                System.out.println("    Sequential mode: Runs each pattern one after the other, ending when the patterns finish or the duration of the pattern group expires.");
                System.out.println("Usage: modPatternGroup <groupName> [options...]");
                System.out.println("        -a <patternName>    The name of any patterns to add to the group (can be repeated)");
                System.out.println("        -d <seconds>        The amount of time the pattern group should run in seconds (overrides any previous times set)");
                System.out.println("        -h                  Displays this help message");
                System.out.println("        -n <groupName>      The new name of the pattern group");
                System.out.println("        -r <patternName>    The name of any patterns to remove from the group (can be repeated)");
                System.out.println("        -t <groupType>      The new type of the pattern group");
                break;

            case "delPatternGroup":
                System.out.println("Command: delPatternGroup");
                System.out.println("Function: Deletes a pattern group object.");
                System.out.println("Usage: delPatternGroup <groupName>");
                System.out.println("        -h    Displays this help message");
                break;

            case "addSequence":
                System.out.println("Command: addSequence");
                System.out.println("Function: Creates a new sequence object.");
                System.out.println("Usage: addSequence <sequenceName>");
                System.out.println("        -a <patternName>    The name of a pattern or pattern group to add to the sequence (can be repeated)");
                System.out.println("                            Check the help page of the \"addPatternGroup\" or \"modPatternGroup\" commands for info");
                System.out.println("                            on patternGroups and running two patterns at once.");
                System.out.println("        -d <seconds>        The amount of time the sequence should run in seconds (overrides any previous times set)");
                System.out.println("        -h                  Displays this help message");
                break;

            case "modSequence":
                System.out.println("Command: modSequence");
                System.out.println("Function: Allows users to modify attributes of a sequence such as its name, affected patterns, and duration.");
                System.out.println("Usage: modSequence <sequenceName> [options...]");
                System.out.println("        -a <patternName>     The name of a pattern or pattern group to add to the sequence (can be repeated)");
                System.out.println("        -d <seconds>         The amount of time the pattern group should run in seconds (overrides any previous times set)");
                System.out.println("        -h                   Displays this help message");
                System.out.println("        -n <sequenceName>    The new name of the sequence.");
                System.out.println("        -r <patternName>     The name of a pattern or pattern group to remove from the sequence (can be repeated)");
                break;

            case "delSequence":
                System.out.println("Command: delSequence");
                System.out.println("Function: Deletes a sequence object.");
                System.out.println("Usage: delSequence <sequenceName>");
                System.out.println("        -h    Displays this help message");
                break;

            case "runSequence":
                System.out.println("Command: runSequence");
                System.out.println("Function: Runs the specified sequence.");
                System.out.println("Usage: runSequence <sequenceName> [options...]");
                System.out.println("        -h    Displays this help message");
                System.out.println("        -s    The name of the sequence to run");
                System.out.println("        -d    The amount of time the pattern group should run in seconds (overrides any previous times set)");
                break;

            // case "alias":
            //     System.out.println("Command: alias");
            //     System.out.println("Function:");
            //     System.out.println("    Allows the user to rename commands and objects with some exceptions.");
            //     System.out.println("    Exception No 1: The user cannot name a command/object after an existing command/object.");
            //     System.out.println("    Exception No 2: The user cannot put spaces into the new name of a command/object.");
            //     System.out.println("    Exception No 3: You cannot use both the -c and -o ticks in the same line.");
            //     System.out.println("Usage: alias [options...]");
            //     System.out.println("        -c <command>    The command that the alias will reference");
            //     System.out.println("        -h              Displays this help message");
            //     System.out.println("        -l              Lists all created aliases");
            //     System.out.println("        -n <name>       The name of the alias");
            //     System.out.println("        -o <object>     The object that the alias will reference");
            //     break;

            case "help":
                System.out.println("Command: help");
                System.out.println("Function: Displays this help message regarding all the commands available to the user");
                System.out.println("Usage: help");
                break;
        }
    }
}