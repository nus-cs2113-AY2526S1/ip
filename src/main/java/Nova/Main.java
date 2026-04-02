package Nova;

import Nova.ui.Nova;

/**
 * The entry point of the Nova application.
 * <p>
 * Responsibilities:
 * - Initializes the Nova app with the data file path.
 * - Starts the application by calling run().
 */
public class Main {

    /**
     * The main method that launches the Nova application.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        String filePath = "./NovaData/Nova.txt";
        new Nova(filePath).run();
    }
}
