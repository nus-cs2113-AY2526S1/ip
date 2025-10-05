package arpa.home.yikjin.app.kuro;

import java.util.NoSuchElementException;

import arpa.home.yikjin.app.kuro.command.Runner;
import arpa.home.yikjin.app.kuro.exception.base.AppException;
import arpa.home.yikjin.app.kuro.storage.FileManager;
import arpa.home.yikjin.app.kuro.ui.Ui;

/**
 * Main entry point of Kuro
 */
final class Main {
    /**
     * Main entry method of Kuro
     *
     * @param args Command line arguments passed to Kuro
     */
    public static void main(final String[] args) {
        init();
        commandRepl();
        Ui.close();
    }

    /**
     * Initialise Kuro by loading tasks from file, and greeting the user
     */
    private static void init() {
        try {
            FileManager.loadFromDisk();
        } catch (final AppException e) {
            Ui.errException(e);
        }

        Ui.greet();
    }

    /**
     * Main program loop of Kuro, to execute each command given interactively
     */
    private static void commandRepl() {
        do {
            try {
                final String inputLine = Ui.getUserPrompt();
                Runner.runCommand(inputLine);
            } catch (final AppException e) {
                Ui.errException(e);
            } catch (final NoSuchElementException ignored) {
                // Ensure the code does not error out when passing in input via command line, and the input does not end
                // with "bye"
                return;
            }
        } while (!Runner.isTerminateGiven());
    }
}
