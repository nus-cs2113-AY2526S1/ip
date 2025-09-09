import java.util.NoSuchElementException;

final class Main {
    private static final Ui UI_INPUT = new Ui();

    public static void main(final String[] args) {
        Ui.greet();
        commandRepl();

        UI_INPUT.close();
    }

    private static void commandRepl() {
        do {
            try {
                final String inputLine = UI_INPUT.getUserPrompt();
                CommandRunner.runCommand(inputLine);
            } catch (final NoSuchElementException ignored) {
                // Ensure the code does not error out when passing in input via command line, and the input does not end
                // with "bye"
                return;
            }
        } while (!CommandRunner.isTerminateGiven());
    }
}
