package espresso.inputchecker;

/**
 * Centralized repository of error messages used for input validation.
 * These constants are referenced by {@link Parser} and {@link Validator}
 * to provide consistent feedback when user commands are malformed or incomplete.
 */
public class Messages {

    // ─────────────────────────────────────────────────────────────
    // Event Command Errors
    // ─────────────────────────────────────────────────────────────

    /** Triggered when the /from parameter exists but has no content. */
    static final String INVALID_EVENT_FROM =
            "Your /from parameter is empty. Please add something behind the parameter /from";

    /** Triggered when the /from parameter is missing entirely. */
    static final String INVALID_EVENT_MISSING_FROM_PARAMETER =
            "Your /from parameter is missing. Please add the parameter /from";

    /** Triggered when the /to parameter exists but has no content. */
    static final String INVALID_EVENT_TO =
            "Your /to parameter is empty. Please add something behind the parameter /to";

    /** Triggered when the /to parameter is missing entirely. */
    static final String INVALID_EVENT_MISSING_TO_PARAMETER =
            "Your /to parameter is missing. Please add the parameter /to";

    /** Triggered when the event name is missing before the /from parameter. */
    static final String INVALID_EVENT_NAME =
            "Your event is missing a name. Please ensure there is something before /from";

    /** Triggered when /from appears after /to, violating expected order. */
    static final String INVALID_EVENT_WRONG_ORDER =
            "Your event command is in the wrong order. Please ensure the /from is before the /to";

    // ─────────────────────────────────────────────────────────────
    // Todo Command Errors
    // ─────────────────────────────────────────────────────────────

    /** Triggered when a todo command lacks a task name. */
    static final String INVALID_TODO_NAME =
            "Your todo is missing a name. Please ensure there is something after todo";

    // ─────────────────────────────────────────────────────────────
    // Deadline Command Errors
    // ─────────────────────────────────────────────────────────────

    /** Triggered when the /by parameter exists but has no content. */
    static final String INVALID_DEADLINE_BY =
            "Your deadline is missing a due date/time. Please ensure there is something after the /by";

    /** Triggered when the /by parameter is missing entirely. */
    static final String INVALID_DEADLINE_MISSING_BY_PARAMETER =
            "Your /by parameter is missing. Please add the parameter /by";

    /** Triggered when the deadline name is missing before the /by parameter. */
    static final String INVALID_DEADLINE_NAME =
            "Your deadline is missing a name. Please ensure there is something after deadline but before /by";

    // ─────────────────────────────────────────────────────────────
    // Index-Based Command Errors (mark, unmark, delete)
    // ─────────────────────────────────────────────────────────────

    /** Triggered when the task index is not a valid integer. */
    static final String INVALID_INDEX =
            "Your task index is not a valid number. Please enter an integer";

    /** Triggered when the task index is missing from the command. */
    static final String INVALID_MISSING_INDEX =
            "Your task index is missing. Please enter an integer";

    // ─────────────────────────────────────────────────────────────
    // General Command Errors
    // ─────────────────────────────────────────────────────────────

    /** Triggered when the command keyword is unrecognized or unsupported. */
    static final String INVALID_KEYWORD =
            "Your command is invalid.";

    /** Triggered when the find command is missing a keyword to search for. */
    static final String INVALID_FIND_MISSING_KEYWORD =
            "Your task description is missing. Please add something after the word 'find'";
}