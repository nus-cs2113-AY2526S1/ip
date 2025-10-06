package duke.task;

public class Event extends Task{
    private String from;
    private String to;
    public Event(String name, String from, String to){
        super(name);
        this.from = from;
        this.to = to;
    }
    public String getFrom() {
        return from;
    }
    public String getTo() {
        return to;
    }

    /**
     * return the information in a specified format
     * @return String - output, which is the final text message
     */
    @Override
    public String printinfo() {
        String output = "";
        output += "[E]";
        output += super.printinfo();
        output += "(from: " + this.from + " to: " + this.to + ")";
        return output;
    }
    @Override
    public String toString(){
        return ("E|" + isDone() + "|" + getName() + "|" + from + "|" + to);

    }
}
