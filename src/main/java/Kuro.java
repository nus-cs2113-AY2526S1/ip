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

    private static void quit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void main(final String[] args) {
        Kuro.greet();
        Kuro.quit();
    }
}
