enum InputCommand {
    BYE("bye"), DEADLINE("deadline"), EVENT("event"), LIST("list"), MARK("mark"), TODO("todo"), UNMARK(
            "unmark"), INVALID("");

    private final String enumValue;

    InputCommand(final String enumValue) {
        this.enumValue = enumValue;
    }

    static InputCommand parse(final String command) {
        for (final InputCommand enumCommand : values()) {
            if (enumCommand.enumValue.equalsIgnoreCase(command)) {
                return enumCommand;
            }
        }

        return INVALID;
    }

    @Override
    public String toString() {
        return enumValue;
    }
}
