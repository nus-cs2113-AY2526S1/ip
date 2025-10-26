/**
 * Parses raw user input strings into command words and arguments,
 * to be interpreted and executed by the program.
 */
public class Parser {
    public static Command parse(String input) {
        String[] words = input.split(" ", 2);
        String command = words[0];
        String args = (words.length > 1) ? words[1] : "";
        return new Command(command, args);
    }

    /**
     * Parses a 1-based index from an argument string.
     * @param arg the argument that should contain a positive integer (1-based)
     * @return the parsed integer
     * @throws BenException if the argument is missing, non-numeric, or <= 0
     */
    public static int parseOneBasedIndex(String arg) throws BenException {
        if (arg == null || arg.trim().isEmpty()) {
            throw new BenException("Please provide an index, e.g., 'mark 2'.");
        }
        try {
            int idx = Integer.parseInt(arg.trim());
            if (idx <= 0) {
                throw new BenException("Index must be a positive integer starting from 1.");
            }
            return idx;
        } catch (NumberFormatException e) {
            throw new BenException("Index must be a positive integer, e.g., 'delete 3'.");
        }
    }
}