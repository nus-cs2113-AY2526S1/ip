package arpa.home.yikjin.app.kuro.command;

/**
 * Enum of all types of input command names
 */
public enum InputCommand {
    BYE("bye"), DEADLINE("deadline"), DELETE("delete"), EVENT("event"), LIST("list"), MARK("mark"), TODO(
            "todo"), UNMARK("unmark"), INVALID("");

    /**
     * Command name of each enum
     */
    private final String enumValue;

    /**
     * Enum of all types of input command names
     *
     * @param enumValue Command name of each enum
     */
    InputCommand(final String enumValue) {
        this.enumValue = enumValue;
    }

    /**
     * Convert a command string to a command enum
     *
     * @param command Command string to convert
     *
     * @return Converted command enum
     */
    public static InputCommand parse(final String command) {
        for (final InputCommand enumCommand : values()) {
            if (enumCommand.enumValue.equalsIgnoreCase(command)) {
                return enumCommand;
            }
        }

        return INVALID;
    }

    /**
     * Get the command string form of the command enum
     *
     * @return Command string of current enum
     */
    @Override
    public String toString() {
        return enumValue;
    }
}
