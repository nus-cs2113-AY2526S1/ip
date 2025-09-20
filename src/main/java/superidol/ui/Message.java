package superidol.ui;

import superidol.task.Task;

public class Message {
    static final String logo = " ____                       ___    _       _\n"
            + "/ ___| _   _ _ __   ___ _ _|_ _|__| | ___ | |\n"
            + "\\___ \\| | | | '_ \\ / _ \\ '__| |/ _` |/ _ \\| |\n"
            + " ___) | |_| | |_) |  __/ |  | | (_| | (_) | |\n"
            + "|____/ \\__,_| .__/ \\___|_| |___\\__,_|\\___/|_|\n"
            + "            |_|\n";
    static final String hello = "Hello! I'm SuperIdol\n";
    static final String greeting = "What can I do for you?";
    static final String invalidCommandResponse = "OOPS!!! I'm sorry, but I don't know what that means :-(\n"
            + "try: todo <task>\n"
            + "try: deadline <task> /by <time>\n"
            + "try: event <task> /from <start> /to <end>\n"
            + "try: (un)mark <index>\n"
            + "try: list\n"
            + "try: delete <index>";
    public static final String separator = "____________________________________________________________";
    static final String goodbyeMessage = "Bye. Hope to see you again soon!";

    // marking UI

    static final String markedResponse = "Nice! I've marked this task as done:";
    static final String unmarkedResponse = "OK, I've marked this task as not done yet:";
    static final String invalidMarkingResponse = "Wrong input! Try \"(un)mark <task_index>\"";
    static String outOfRangeListResponse() {
        return String.format("You only have %d task(s) in the list.", Task.taskCount);
    }

    // create UI

    static final String invalidDeadlineResponse = "Wrong input! Try \"deadline <task> /by <time>\"";
    static final String invalidEventResponse = "Wrong input! Try \"event <task> /from <start> /to <end>\"";
    static final String invalidTodoResponse = "Wrong input! Try \"todo <task>\"";
    static final String addingConfirmation = "Got it. I've added this task:";
    static String addingResult() {
        return String.format("Now you have %d tasks in the list.", Task.taskCount);
    }

    // list

    static final String emptyListResponse = "There are no tasks in the list.\n" + "Try add some.";

    // delete

    static final String deleteConfirmation = "Noted. I've removed this task:";
    static String deleteResult() {
        return String.format("Now you have %d tasks in the list.", Task.taskCount - 1);
    }

    static final String invalidDeleteResponse = "Wrong input! Try \"delete <task_index>\"";

    // load

    static final String localSaveLoadedResponse = "Loading local save, try \"list\"";

    // storage

    static final String cannotCreateFileResponse = "Cannot create new file";
    static final String invalidDataResponse = "Invalid data: ";
    static final String cannotOpenFileResponse = "Cannot open file";

}
