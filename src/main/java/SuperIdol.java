import java.util.Scanner;

public class SuperIdol {

    private static String[] toDoList = new String[100];
    private static int listCount = 0;

    public static void main(String[] args) {
        String logo = " ____                       ___    _       _ \n"
                + "/ ___| _   _ _ __   ___ _ _|_ _|__| | ___ | |\n"
                + "\\___ \\| | | | '_ \\ / _ \\ '__| |/ _` |/ _ \\| |\n"
                + " ___) | |_| | |_) |  __/ |  | | (_| | (_) | |\n"
                + "|____/ \\__,_| .__/ \\___|_| |___\\__,_|\\___/|_|\n"
                + "            |_|                              \n";
        System.out.println("Hello! I'm SuperIdol\n" + logo);
        System.out.println("What can I do for you?");

        Scanner in = new Scanner(System.in);
        while (true) {
            String command =  in.nextLine();
            if (command.equals("exit")) {
                talk("Bye. Hope to see you again soon!");
                System.exit(0);
            }
            else if (command.equals("list")) {
                showList();
            }
            else {
                addToList(command);
            }
        }
    }

    public static void talk(String response) {
        System.out.println("____________________________________________________________");
        System.out.println(response);
        System.out.println("____________________________________________________________");
    }

    public static void addToList(String command) {
        toDoList[listCount] = command;
        listCount++;
        talk("added: " + command);
    }

    public static void showList() {
        System.out.println("____________________________________________________________");
        for (int i = 0; i < listCount; i++) {
            System.out.println((i+1) + ". " + toDoList[i]);
        }
        System.out.println("____________________________________________________________");
    }
}
