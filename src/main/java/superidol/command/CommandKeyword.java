package superidol.command;

public enum CommandKeyword {
    EXIT,
    LIST,
    MARK,
    UNMARK,
    TODO,
    DEADLINE,
    EVENT,
    DELETE,
    INVALID;

    public static CommandKeyword getCommand(String command) {
        String keyword = command.split(" ")[0];

        try {
            return CommandKeyword.valueOf(keyword.toUpperCase());
        } catch (IllegalArgumentException e) {
            return CommandKeyword.INVALID;
        }
    }
}


