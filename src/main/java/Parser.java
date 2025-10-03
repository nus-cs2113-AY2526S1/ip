/**
 * Parses raw user input into a structured command plus arguments.
 *
 * <p>The parser splits on the first space: the token before the space is taken
 * as the command verb, and everything after it (if any) is preserved as the
 * raw argument string. Unsupported verbs trigger an {@link UnknownInputException}.</p>
 *
 * <p>Supported commands (verbatim): {@code todo}, {@code list}, {@code mark},
 * {@code unmark}, {@code event}, {@code deadline}, {@code bye}, {@code delete},
 * {@code save}, {@code find}.</p>
 *
 * <p>Examples:</p>
 * <pre>{@code
 * "todo Buy milk"        -> command="todo",     args="Buy milk"
 * "deadline submit /by 2025-10-05 23:59" -> command="deadline", args="submit /by 2025-10-05 23:59"
 * "list"                 -> command="list",     args=""
 * }</pre>
 *
 * @see Parser.ParsedCommand
 */
public class Parser {

    public static ParsedCommand parse(String input) throws UnknownInputException {
        String[] parts = input.split(" ", 2);
        String command = parts[0].toLowerCase();
        String arguments = parts.length > 1 ? parts[1] : "";
        switch (command) {
        case "todo":
        case "list":
        case "mark":
        case "unmark":
        case "event":
        case "deadline":
        case "bye":
        case "delete":
        case "save":
        case "find":
            return new ParsedCommand(command, arguments);
        default:
            throw new UnknownInputException();
        }
    }

    public static class ParsedCommand {
        public final String command;
        public final String args;
        public ParsedCommand(String command, String args) {
            this.command = command;
            this.args = args;
        }
    }
}
