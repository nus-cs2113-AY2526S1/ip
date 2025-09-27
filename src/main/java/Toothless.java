import command.Task;
import exceptions.*;
import parser.Parser;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Toothless {
    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        Ui ui = new Ui(tasks);
        TaskList tl = new TaskList(tasks,ui);
        Parser parser = new Parser(tl,ui);
        Storage storage = new Storage(tasks,tl);

        Scanner input = new Scanner(System.in);

        ui.printLogo();
        ui.printCommandList();

        try{
            storage.createDirFile();
        }catch(IOException e){
            System.out.println("Error while creating directory");
        }


        File f = new File("./data/Toothless.txt");

        if(f.length() != 0){
            try{
                storage.getFileContents(f);
            }catch(FileNotFoundException e){
                System.out.println("Unable to retrieve file contents");
            }
        }

        String reply = input.nextLine();

        while(!reply.equals("bye")){
            parser.start(reply);
            reply = input.nextLine();
        }

        storage.exportToFile(f);

        ui.printBye();
    }
}
