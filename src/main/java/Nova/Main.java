package Nova;

import Nova.ui.Nova;

public class Main {
    public static void main(String[] args) {
        String filePath = "./NovaData/Nova.txt";
        new Nova(filePath).run();
    }
}
