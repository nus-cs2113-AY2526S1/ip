package duke.task;

public class Todo extends Task{
    public Todo(String name){
        super(name);
    }

    /**
     * return the information in a specified format
     * @return String - output, which is the final text message
     */
    @Override
    public String printinfo() {
        String output = "";
        output += "[T]";
        output += super.printinfo();
        return output;
    }

    @Override
    public String toString(){
        return ("T|" + isDone() +"|"+ getName());
    }
}
