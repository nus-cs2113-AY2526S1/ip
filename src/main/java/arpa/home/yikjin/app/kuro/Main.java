package arpa.home.yikjin.app.kuro;

import java.util.NoSuchElementException;

import arpa.home.yikjin.app.kuro.command.Runner;
import arpa.home.yikjin.app.kuro.exception.base.AppException;
import arpa.home.yikjin.app.kuro.storage.FileManager;
import arpa.home.yikjin.app.kuro.ui.Ui;

final class Main {
    public static void main(final String[] args) {
        init();
        commandRepl();
        Ui.close();
    }

    private static void init() {
        try {
            FileManager.loadFromDisk();
        } catch (final AppException e) {
            Ui.errException(e);
        }

        Ui.greet();
    }

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
