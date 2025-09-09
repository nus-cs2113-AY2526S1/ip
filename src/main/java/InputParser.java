import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

final class InputParser {
    private static final int INIT_POS_ARGS_CAPACITY = 5;
    private static final int INIT_NAMED_ARGS_CAPACITY = 2;
    private static final Pattern SPACES = Pattern.compile("\\s+");
    private static final String[] EMPTY_STRING_ARRAY = new String[0];

    private final ArrayList<String> positionalArgs;
    private final Map<String, String[]> namedArgs;
    private InputCommand command;

    InputParser() {
        command = InputCommand.INVALID;
        positionalArgs = new ArrayList<>(INIT_POS_ARGS_CAPACITY);
        namedArgs = new HashMap<>(INIT_NAMED_ARGS_CAPACITY);
    }

    static String mapArgsToString(final String[] args) {
        if (args == null) {
            return "";
        }
        return String.join(" ", args);
    }

    void parse(final String input) {
        final String[] tokens = SPACES.split(input);

        command = InputCommand.INVALID;
        positionalArgs.clear();
        namedArgs.clear();

        boolean isCommandParsing = true;
        boolean isPosArgsParsed = false;
        String namedArgName = "";
        final ArrayList<String> namedArgValue = new ArrayList<>(3);

        for (final String token : tokens) {
            if (isCommandParsing) {
                command = InputCommand.parse(token);

                if (command == InputCommand.INVALID) {
                    positionalArgs.add(token);
                }

                isCommandParsing = false;
            } else if (!isPosArgsParsed) {
                if (token.charAt(0) == '/') {
                    isPosArgsParsed = true;
                    namedArgName = token.substring(1);
                } else {
                    positionalArgs.add(token);
                }
            } else {
                if (token.charAt(0) == '/') {
                    namedArgs.put(namedArgName, namedArgValue.toArray(EMPTY_STRING_ARRAY));

                    namedArgName = token.substring(1);
                    namedArgValue.clear();
                } else {
                    namedArgValue.add(token);
                }
            }
        }

        if (isPosArgsParsed && !namedArgName.isEmpty()) {
            namedArgs.put(namedArgName, namedArgValue.toArray(EMPTY_STRING_ARRAY));
        }
    }

    @Override
    public String toString() {
        return command + ": " + positionalArgs + " | " + namedArgs;
    }

    InputCommand getCommand() {
        return command;
    }

    String[] getPositionalArgs() {
        return positionalArgs.toArray(EMPTY_STRING_ARRAY);
    }

    Map<String, String[]> getNamedArgs() {
        return Collections.unmodifiableMap(namedArgs);
    }
}
