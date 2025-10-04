package arpa.home.yikjin.app.kuro.command;

public enum InputCommand {
    BYE("bye"), DEADLINE("deadline"), DELETE("delete"), EVENT("event"), LIST("list"), MARK("mark"), TODO(
            "todo"), UNMARK("unmark"), INVALID("");

    private final String enumValue;

    InputCommand(final String enumValue) {
        this.enumValue = enumValue;
    }

    public static InputCommand parse(final String command) {
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
