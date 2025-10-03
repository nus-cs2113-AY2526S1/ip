/**
 * Class Bob in the Bob task manager.
 */
public class Bob {
    private final Ui ui;
    public volatile boolean endConvo = false;

    /**
     * Constructs a new Bob.
     * @param filePath parameter.
     */
    public Bob(String filePath) {
        this.ui = new Ui();
        try {
            new Storage(filePath).loadTasks();
        } catch (Exception e) {
            ui.showLoadingError();
        }
    }

    /**
     * Runs the program
     */
    public void run() {
        ui.showWelcome();
        Parser parser = new Parser();
        while (!endConvo) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                parser.parsing(fullCommand, this);
            } catch (Exception e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Performs main.
     * @param args parameter.
     */
    public static void main(String[] args) {
        new Bob("data/bob.txt").run();
    }
}
