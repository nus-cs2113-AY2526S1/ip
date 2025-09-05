import java.util.NoSuchElementException;

final class Kuro {
    private static final Ui UI = new Ui();

    public static void main(final String[] args) {
        Ui.greet();
        Kuro.commandRepl();

        Kuro.UI.close();
    }

    private static void commandRepl() {
        do {
            try {
                final String inputLine = Kuro.UI.getUserPrompt();
                CommandRunner.runCommand(inputLine);
            } catch (final NoSuchElementException ignored) {
                // Ensure the code does not error out when passing in input via command line, and the input does not end
                // with "bye"
                return;
            }
        } while (!CommandRunner.isTerminateGiven());
    }
}
