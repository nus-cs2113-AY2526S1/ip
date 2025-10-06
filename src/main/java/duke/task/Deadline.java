package duke.task;

public class Deadline extends Task{
    private String by;
    public Deadline(String name, String by){
        super(name);
        this.by = by;
    }
    public String getBy() {
        return by;
    }

    /**
     * return the information in a specified format
     * @return String - output, which is the final text message
     */
    @Override
    public String printinfo() {
        String output = "";
        output += "[D]";
        output += (super.printinfo());
        output += " by: " + this.by;
        return output;
    }
    @Override
    public String toString(){
        return ("D|" + isDone()+ "|" + getName() + "|" + by);
    }
}
