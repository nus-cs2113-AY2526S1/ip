/**
 * Class that implements the method that deals with the user commands
 * Implement the other classes
 * @see Ui
 * @see Storage
 * @see TaskList
 * @see Parser
 */

import java.util.List;
public class AddListV2 {
    public static void AddlistV2() throws Exception {
        Ui ui = new Ui();
        Storage storage = new Storage("tasks.txt");
        List<Task> loaded = storage.load();
        TaskList taskList = new TaskList(loaded);

        while (true) {
            String input = ui.readCommand();
            Parser.ParsedCommand pc; //declaration not constructor like eg Account.balance
            try {
                pc = Parser.parse(input);
            } catch (UnknownInputException e) {
                ui.showError("what saying you give me a command plez");
                continue;
            }

            try {
                switch (pc.command) {
                case "todo":
                    taskList.addTodo(pc.args, ui);
                    break;
                case "list":
                    taskList.list(ui);
                    break;
                case "mark":
                    taskList.mark(pc.args, ui);
                    break;
                case "unmark":
                    taskList.unmark(pc.args, ui);
                    break;
                case "event":
                    taskList.addEvent(pc.args, ui);
                    break;
                case "deadline":
                    taskList.addDeadline(pc.args, ui);
                    break;
                case "delete":
                    taskList.delete(pc.args, ui);
                    break;
                case "save":
                    storage.save(taskList.asList());
                    break;
                case "find":
                    taskList.find(pc.args, ui);
                    break;
                case "bye":
                    ui.showBye();
                    return;
                }
            } catch (EmptyStringException e) {
                ui.showError("Description cannot be empty.");
            } catch(NothingFoundException e){
                ui.showError("cant find anything matching ur keyword eh");
            } catch(WrongFormatException e){
                ui.showError("format wrong , missing a /");
            } catch (Exception e) {
                ui.showError(e.getMessage());
            }
        }
    }
}
