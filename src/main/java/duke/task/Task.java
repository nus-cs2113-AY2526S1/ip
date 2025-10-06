package duke.task;

import duke.DukeException;

public class Task {
    private String name;
    private boolean done;
    public Task(String name) {
        this.name = name;
        done = false;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public boolean isDone() {
        return done;
    }
    public void setDone(boolean done) {
        this.done = done;
    }
    /**
     * return the information in a specified format
     * @return String - output, which is the final text message
     */
    public String printinfo(){
        String output = "";
        output += "[";
        if (this.done){
            output += "X";
        }
        else{
            output += " ";
        }
        output += "] " + this.name;
        return output;
    }
    public String toString(){
        return this.name;
    }
    public void printLastTask(){}
}
