package prime.parser;

public class Parser {
    // Hardcoding the Constants
    private static final String BY = "/by";
    private static final String FROM = "/from";
    private static final String TO = "/to";

    public static Command parse(String userInput) {
        // Split out the command word and the arguments using the Regex for space
        String[] parts = userInput.split(" ", 2);
        String commandWord = parts[0];

        String arguments;
        if (parts.length > 1) {
            arguments = parts[1];
        } else {
            arguments = "";
        }

        // Inspired from CG2111A mod
        switch (commandWord) {
        case "bye":
            return new Command(CommandType.BYE, arguments);
        case "list":
            return new Command(CommandType.LIST, arguments);
        case "mark":
            return new Command(CommandType.MARK, arguments);
        case "unmark":
            return new Command(CommandType.UNMARK, arguments);
        case "todo":
            return new Command(CommandType.TODO, arguments);
        case "deadline":
            return new Command(CommandType.DEADLINE, arguments);
        case "event":
            return new Command(CommandType.EVENT, arguments);
        case "delete":
            return new Command(CommandType.DELETE, arguments);
        default:
            return new Command(CommandType.INVALID, userInput);
        }
    }

    // Helps me extract information for description and by and stores it in an array
    public static String[] parseDeadline(String arguments) {
        int byIndex = arguments.indexOf(BY);
        if (byIndex == -1) {
            return null; // no by
        }
        String description = arguments.substring(0, byIndex).trim();
        String by = arguments.substring(byIndex + BY.length()).trim();
        return new String[]{description, by};
    }

    // Helps me extract information for description, from and to and stores it in an array
    public static String[] parseEvent(String arguments) {
        int fromIndex = arguments.indexOf(FROM);
        int toIndex = arguments.indexOf(TO);
        if (fromIndex == -1 || toIndex == -1) {
            return null; // no from or to
        }
        String description = arguments.substring(0, fromIndex).trim();
        String from = arguments.substring(fromIndex + FROM.length(), toIndex).trim();
        String to = arguments.substring(toIndex + TO.length()).trim();
        return new String[]{description, from, to};
    }
}
