package espresso.commands;

/**
 * Represents the types of commands that can be parsed and executed by the application.
 * Each enum value corresponds to a specific user action or command.
 */
public enum CommandType {
    /** Lists all tasks. */
    LIST,

    /** Marks a task as completed. */
    MARK,

    /** Unmarks a task (sets it as not completed). */
    UNMARK,

    /** Deletes a task. */
    DELETE,

    /** Finds tasks that match given keywords. */
    FIND,

    /** Adds a todo task. */
    TODO,

    /** Adds a deadline task. */
    DEADLINE,

    /** Adds an event task. */
    EVENT,

    /** Represents an invalid command. */
    INVALID,

    /** Represents a valid command (used internally). */
    VALID,

    /** Displays a help tool */
    HELP
}
