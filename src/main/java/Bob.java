public class Bob {
    private final Ui ui;
    public volatile boolean endConvo = false;

    public Bob(String filePath) {
        this.ui = new Ui();
        try {
            new Storage(filePath).loadTasks();
        } catch (Exception e) {
            ui.showLoadingError();
        }
    }

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

    public static void main(String[] args) {
        new Bob("data/bob.txt").run();
    }
}
