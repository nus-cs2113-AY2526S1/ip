package zoro.core;

/**
 * Enum representing the different states of the Zoro application.
 * Used to control the main application flow and determine which mode is active.
 */
public enum State {
    /** Main menu state - displays options and waits for user selection */
    MENU,
    /** Echo mode state - repeats user input back to them */
    ECHO,
    /** Task management state - handles all task-related operations */
    TASK,
    /** Exit state - terminates the application */
    EXIT
}
