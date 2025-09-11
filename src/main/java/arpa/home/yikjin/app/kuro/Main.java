package arpa.home.yikjin.app.kuro;

import java.util.NoSuchElementException;

import arpa.home.yikjin.app.kuro.command.Runner;
import arpa.home.yikjin.app.kuro.exception.base.AppException;
import arpa.home.yikjin.app.kuro.ui.Ui;

final class Main {
    public static void main(final String[] args) {
        Ui.greet();
        commandRepl();

        Ui.close();
    }

    private static void commandRepl() {
        do {
            try {
                final String inputLine = Ui.getUserPrompt();
                Runner.runCommand(inputLine);
            } catch (final AppException message) {
                Ui.errException(message);
            } catch (final NoSuchElementException ignored) {
                // Ensure the code does not error out when passing in input via command line, and the input does not end
                // with "bye"
                return;
            }
        } while (!Runner.isTerminateGiven());
    }
}
