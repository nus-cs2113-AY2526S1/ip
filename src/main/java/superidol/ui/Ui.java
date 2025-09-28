package superidol.ui;

import superidol.task.Task;

public class Ui {
    
    public static void greeting() {
        respond(Message.hello + Message.logo + Message.greeting);
    }
    public static void respondToInvalidCommand() {
        respond(Message.invalidCommandResponse);
    }
    public static void respond(String response) {
        System.out.println(Message.separator);
        System.out.println(response);
        System.out.println(Message.separator);
    }
    public static void goodbye() {
        respond(Message.goodbyeMessage);
    }

    // marking UI

    public static void respondMarking(Task task, boolean isDone) {
        System.out.println(Message.separator);
        if (isDone) {
            System.out.println(Message.markedResponse);
        } else {
            System.out.println(Message.unmarkedResponse);
        }
        System.out.println(task.getTask());
        System.out.println(Message.separator);
    }
    public static void respondInvalidMarking() {
        respond(Message.invalidMarkingResponse);
    }
    public static void respondOutOfListRange() {
        respond(Message.outOfRangeListResponse());
    }

    // adding UI

    public static void respondInvalidDeadline() {
        respond(Message.invalidDeadlineResponse);
    }
    public static void respondInvalidEvent() {
        respond(Message.invalidEventResponse);
    }
    public static void respondInvalidTodo() {
        respond(Message.invalidTodoResponse);
    }
    public static void respondAddingTask(Task newTask) {
        System.out.println(Message.separator);
        System.out.println(Message.addingConfirmation);
        System.out.println(newTask.getTask());
        System.out.println(Message.addingResult());
        System.out.println(Message.separator);
    }

    // list

    public static void respondEmptyList() {
        respond(Message.emptyListResponse);
    }

    // delete

    public static void respondDeletingTask(Task task) {
        System.out.println(Message.separator);
        System.out.println(Message.deleteConfirmation);
        System.out.println(task.getTask());
        System.out.println(Message.deleteResult());
        System.out.println(Message.separator);
    }
    public static void respondInvalidDeleting() {
        respond(Message.invalidDeleteResponse);
    }

    // load
    public static void respondLoadedLocalSave() {
        respond(Message.localSaveLoadedResponse);
    }

    // storage
    public static void respondCannotCreateNewFile() {
        respond(Message.cannotCreateFileResponse);
    }
    public static void respondInvalidData(String data) {
        System.out.print(Message.invalidDataResponse);
        System.out.println(data);
    }
    public static void respondCannotOpenFile() {
        System.out.println(Message.cannotOpenFileResponse);
    }

    // print
    public static void respondInvalidPrint() {
        respond(Message.invalidPrintResponse);
    }
    public static void respondEmptyPrint() {
        System.out.println(Message.emptyResultResponse);
    }

    // find
    public static void respondInvalidFind() {
        respond(Message.invalidFindResponse);
    }
    public static void respondEmptyFind() {
        System.out.println(Message.emptyResultResponse);
    }
    public static void respondValidFind() {
        System.out.println(Message.validFindResponse);
    }

    // separator
    public static void separate() {
        System.out.println(Message.separator);
    }
}
