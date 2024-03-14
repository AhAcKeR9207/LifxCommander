package control.lifx;

import static control.lifx.Dynamic.commands;

import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.BiFunction;

public class InputManager {
    Scanner scanner = new Scanner(System.in);

    /**
     * The main loop for this thread.  It constantly monitors the System.in stream for new commands.
     */
    public void run() {
        for (;;) {
            if (scanner.hasNextLine()) {
                interpretCommand(scanner.nextLine());
            }
        }
    }

    public void interpretCommand(String command) {
        String[] parts = command.split(" ");

        String keyword = parts[0];

        Consumer<String[]> function = commands.get(keyword);

        function.accept(parts);
    }
}