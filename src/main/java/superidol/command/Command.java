package superidol.command;

public enum Command {
    EXIT,
    LIST,
    MARK,
    UNMARK,
    TODO,
    DEADLINE,
    EVENT,
    DELETE,
    INVALID;

    public static Command getCommand(String command) {
        String keyword = command.split(" ")[0];

        try {
            return Command.valueOf(keyword.toUpperCase());
        } catch (IllegalArgumentException e) {
            return Command.INVALID;
        }
    }
}


