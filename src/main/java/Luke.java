public class Luke {
    public static final String GREETING = """
            ____________________________________________________________
             Hello! I'm Luke
             What can I do for you?
            ____________________________________________________________
            """;
    public static final String EXIT_RESPONSE = """
            ____________________________________________________________
            Bye Bye
            ____________________________________________________________
            """;

    public static void main(String[] args) {
        System.out.println(GREETING);

        TaskList taskList1 = new TaskList();
        FileHandler fileHandler = new FileHandler(taskList1);
        new Parser(taskList1, fileHandler);

        System.out.println(EXIT_RESPONSE);
    }
}
