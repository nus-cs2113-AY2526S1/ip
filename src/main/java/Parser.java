import exceptions.MissingDeadlineByWhen;
import exceptions.MissingDeadlineDescription;
import exceptions.MissingDeleteIndex;
import exceptions.MissingEventDescription;
import exceptions.MissingEventFromWhen;
import exceptions.MissingEventToWhen;
import exceptions.MissingFindDescription;
import exceptions.MissingTodoDescription;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Parser that extracts the command from user input as well as the necessary parameters
 * then executes the relevant commands
 */
public class Parser {
    public Parser(TaskList taskList, FileHandler fileHandler) {

        Scanner keyboard = new Scanner(System.in);
        String input = keyboard.nextLine();

        while (!input.equals("bye")) {
            try {
                switch (input.split(" ", 2)[0]) {
                case "list":
                    taskList.listTasks();
                    break;
                case "find":
                    if (input.split(" ", 2).length < 2) {
                        throw new MissingFindDescription();
                    }
                    taskList.findTask(input.split(" ", 2)[1]);
                    break;
                case "mark":
                    taskList.markTaskAsDone(Integer.parseInt(input.split(" ")[1]) - 1);
                    break;
                case "unmark":
                    taskList.markTaskAsNotDone(Integer.parseInt(input.split(" ")[1]) - 1);
                    break;
                case "todo":
                    if (input.split(" ", 2).length < 2) {
                        throw new MissingTodoDescription();
                    }
                    taskList.addTask(new Todo(input.split(" ", 2)[1]), fileHandler);
                    break;
                case "deadline":
                    if (input.split(" ", 2).length < 2) {
                        throw new MissingDeadlineDescription();
                    } else if (input.split(" ", 2)[1].split(" /by").length < 2) {
                        throw new MissingDeadlineByWhen();
                    }
                    String deadlineDescription = input.split(" ", 2)[1].split(" /by")[0];
                    String deadlineString = input.split(" ", 2)[1].split(" /by ")[1];
                    try {
                        LocalDate deadline = LocalDate.parse(deadlineString);
                        taskList.addTask(new Deadline(deadlineDescription, deadline), fileHandler);
                    } catch (DateTimeParseException e) {
                        System.out.println("Invalid deadline format, please enter in a yyyy-mm-dd format");
                        break;
                    }
                    break;
                case "event":
                    if (input.split(" ", 2).length < 2) {
                        throw new MissingEventDescription();
                    } else if (input.split(" /from").length < 2) {
                        throw new MissingEventFromWhen();
                    } else if (input.split(" /to").length < 2) {
                        throw new MissingEventToWhen();
                    }
                    String eventDescription = input.split(" ", 2)[1].split(" /from")[0];
                    String startDate = input.split(" /from ")[1].split(" /to")[0];
                    String endDate = input.split(" /to ")[1];
                    taskList.addTask(new Event(eventDescription, startDate, endDate), fileHandler);
                    break;
                case "delete":
                    if (input.split(" ", 2).length < 2) {
                        throw new MissingDeleteIndex();
                    }
                    taskList.removeTask(Integer.parseInt(input.split(" ", 2)[1]) - 1, fileHandler);
                    break;
                default:
                    System.out.println("Please enter a valid command");
                }
            } catch (MissingTodoDescription e) {
                System.err.println("Todo cannot be empty");
            } catch (MissingDeadlineDescription e) {
                System.err.println("Deadline description is missing");
            } catch (MissingDeadlineByWhen e) {
                System.err.println("Deadline by when is missing");
            } catch (MissingEventDescription e) {
                System.err.println("Event description is missing");
            } catch (MissingEventFromWhen e) {
                System.err.println("Event from when is missing");
            } catch (MissingEventToWhen e) {
                System.err.println("Event to when is missing");
            } catch (MissingDeleteIndex e) {
                System.err.println("Delete index missing");
            } catch (MissingFindDescription e) {
                System.err.println("Find string missing");
            } catch (Exception e) {
                System.err.println("Please enter a valid command");
            }

            input = keyboard.nextLine();
        }
        keyboard.close();
    }
}
