
/**
 * A task representing an event that spans a start and end time.
 *
 * <p>Displays with an {@code [E]} type tag, followed by the base {@link Task}
 * representation and a trailing {@code (from: ... to: ...)} segment indicating
 * the event window.</p>
 *
 * <p>Example output: {@code [E][ ] CS lecture (from: 2pm to 10pm)}</p>
 *
 * @see Task
 */public class Events extends Task{
    protected String From;
    protected String To;

    public Events(String description , String To , String From){
        super(description); // calling the superclass constructor
        this.From =From;
        this.To= To;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + From + " to: " + To + ")";
    }
}

