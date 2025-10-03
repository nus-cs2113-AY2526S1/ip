/**
 * Entry point for the Victoria CLI application.
 *
 * <p>This class prints a banner and greeting, then hands control to
 * {@link AddListV2#AddlistV2()} to start the interactive task loop.
 * It also handles a couple of user-input related exceptions to provide
 * friendly error messages.</p>
 *
 * <p>Typical startup output:</p>
 * <pre>{@code
 * Hello from
 *  __     ___  ____
 *  \ \   / / |/ ___|
 *   \ \ / /| ' /
 *    \ V / | . \___
 *     \_/  |_|| ___|
 * ------------------------------
 * Hello! I'm Victoria
 * How may I help you?
 *
 * ------------------------------
 * }</pre>
 *
 * @author  Xylon
 */
public class Victoria {

    public static void main(String[] args) throws Exception {
        String logo =
                " __     ___  ____ \n"
                        + " \\ \\   / / |/ ___|\n"
                        + "  \\ \\ / /| ' /    \n"
                        + "   \\ V / | . \\___ \n"
                        + "    \\_/  |_|| ___|  \n";

        System.out.println("Hello from\n" + logo);
        System.out.println("-".repeat(30));
        System.out.println("Hello! I'm Victoria");
        System.out.println("How may I help you?\n");
        System.out.println("-".repeat(30) + "\n");
        try {
            AddListV2.AddlistV2();
        }
        catch(EmptyStringException e){
            System.out.println(":( there is no input , please type something");
        }
        catch(UnknownInputException e){
            System.out.println("oops sorry bro im not sure what does that mean, can you type another command");
        }
    }
}

