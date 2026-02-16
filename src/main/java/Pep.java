import java.util.Scanner;
import commands.Command;
import exceptions.PepException;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

public class Pep {
    private final String chatbotName;
    private final Ui ui;
    private final TaskList taskList;
    private final Storage storage;

    public Pep(String name, String filePath) {
        this.chatbotName = name;
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        TaskList loadedTasks;
        try {
            loadedTasks = storage.load();
            ui.showMessage("Loaded " + loadedTasks.getCount() + " tasks from file.");
        } catch (PepException e) {
            ui.showError(e.getMessage());
            loadedTasks = new TaskList(); // start fresh
        }
        this.taskList = loadedTasks;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        ui.showWelcome(chatbotName);

        boolean isExit = false;
        while (!isExit) {
            String userInput = scanner.nextLine();
            try {
                Command command = Parser.parse(userInput);
                command.execute(taskList, ui);
                storage.save(taskList); // Save after every change
                isExit = command.isExit();
            } catch (PepException e) {
                ui.showError(e.getMessage());
            }
        }
        scanner.close();
    }

    public static void main(String[] args) {
        Pep pepBot = new Pep("Pep", "./data/pep.txt");
        pepBot.run();
    }
}