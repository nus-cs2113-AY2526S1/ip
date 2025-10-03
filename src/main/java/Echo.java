import java.util.Scanner;
public class Echo {
    public static void echo(String[] args){
        String answer;
        Scanner reply = new Scanner(System.in);
        answer = reply.nextLine();
        if(answer.equals("bye")) {
            System.out.println("-".repeat(30));
            System.out.println("Bye.Hope to see you again soon!\n");
            System.out.println("-".repeat(30));
        }
        else {
            System.out.println("-".repeat(30));
            System.out.println(answer + "\n");
            System.out.println("-".repeat(30) + "\n");
            echo(args);
        }
    }
}
