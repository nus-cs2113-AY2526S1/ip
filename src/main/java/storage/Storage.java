package storage;

import command.Task;
import tasklist.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class contains all the methods for file IO
 *
 * @author  Lai Kai Jie Jeremy
 * @since   2025-08-17
 */

public class Storage {
    private ArrayList<Task> tasks;
    private TaskList tl;

    public Storage(ArrayList<Task> tasks,TaskList tl){
        this.tasks = tasks;
        this.tl = tl;
    }

    /**
     * Writes content to a file
     *
     * @param f File class
     * @param textToAdd text input
     * @throws IOException invokes when file is unaccessible
     */
    public void writeToFile(File f, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(f);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Checks whether a directory/file has been created
     *
     * @throws IOException invokes when file is unaccessible
     */
    public void checkDirFile() throws IOException {
        File dir = new File("data");
        File file =  new File(dir,"Toothless.txt");

        if(!dir.exists()){
            createDirFile(dir,file);
        }
    }

    /**
     * Creates a new directory and file
     *
     * @throws IOException invokes when file is unaccessible
     */
    public void createDirFile(File dir, File file) throws IOException{
        if (dir.mkdir()) {
            System.out.println("Directory created");
        }

        if(file.createNewFile()){
            System.out.println("File created");
        }
    }

    /**
     * Retrieves all the content in the file
     * and adds the task to the list
     *
     * @param f File class
     * @throws FileNotFoundException invokes when the file does not exist
     */
    public void getFileContents(File f) throws FileNotFoundException {
        Scanner s = new Scanner(f);

        int lineCounter = 1;

        while (s.hasNext()) {
            String line = s.nextLine();

            int bar1Index = line.indexOf("|");
            int bar2Index = line.lastIndexOf("|");

            String type = line.substring(0, bar1Index).trim();
            String mark = line.substring(bar2Index + 1, bar2Index + 3).trim();
            String taskName = line.substring(bar1Index + 1, bar2Index).trim();

            if(type.equals("T")){
                tl.readFileToDo(taskName);
            }else if(type.equals("D")){
                String by = tl.extractBy(line);
                tl.readFileDeadline(taskName, by);
            }else{
                String from = tl.extractFromTo(line, "from");
                String to = tl.extractFromTo(line, "to");

                tl.readFileEvent(taskName,from,to);
            }

            if(mark.equals("X")){
                tl.readFileMark(lineCounter);
            }

            lineCounter++;
        }
    }

    /**
     * Writes to the file all the contents in the list
     *
     * @param f File class
     */
    public void exportToFile(File f) {
        String fileContent = "";

        for(Task t : tasks){
            fileContent += t.getType() + " | " + t.getTaskName() + " | " + t.getMarkStatus() + " ";

            if(t.getType().equals("D")){
                fileContent +=  "/by " + t.getBy() + "\n";
            }else if(t.getType().equals("E")){
                fileContent += "/from " + t.getFrom() + " /to " + t.getTo() + "\n";
            }else{
                fileContent += "\n";
            }
        }

        try{
            writeToFile(f, fileContent);
        }catch(IOException e){
            System.out.println("Error while writing to file");
        }
    }

    /**
     * Populates existing tasks in the file
     *
     * @param f File
     */
    public void bootFile(File f){
        try{
            checkDirFile();
        }catch(IOException e){
            System.out.println("Error while creating directory");
        }

        if(f.length() != 0){
            try{
                getFileContents(f);
            }catch(FileNotFoundException e){
                System.out.println("Unable to retrieve file contents");
            }
        }
    }
}
