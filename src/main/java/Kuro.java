import java.nio.charset.StandardCharsets;
import java.util.NoSuchElementException;
import java.util.Scanner;

final class Kuro {
    private static void greet() {
        final String nameLogo = """
                ██╗  ██╗██╗   ██╗██████╗  ██████╗
                ██║ ██╔╝██║   ██║██╔══██╗██╔═══██╗
                █████╔╝ ██║   ██║██████╔╝██║   ██║
                ██╔═██╗ ██║   ██║██╔══██╗██║   ██║
                ██║  ██╗╚██████╔╝██║  ██║╚██████╔╝
                ╚═╝  ╚═╝ ╚═════╝ ╚═╝  ╚═╝ ╚═════╝""";

        System.out.println("Hello! I'm");
        System.out.println(nameLogo);
        System.out.println();

        System.out.println("What can I do for you?");
    }

    private static void echoInput() {
        final Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);

        try {
            do {
                final String inputLine = scanner.nextLine().strip();

                if ("bye".equals(inputLine)) {
                    break;
                }

                System.out.println(inputLine);
            } while (true);
        } catch (final NoSuchElementException ignored) {
            // Ensure the code does not error out when passing in input via command line, and the input does not end
            // with "bye"
        }

        scanner.close();
    }

    private static void quit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void main(final String[] args) {
        Kuro.greet();
        Kuro.echoInput();
        Kuro.quit();
    }
}
