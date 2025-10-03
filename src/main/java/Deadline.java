/**
 * A task with a due date/time.
 *
 * <p>Displays with a {@code [D]} type tag, followed by the base {@link Task}
 * representation and a trailing {@code (by: ...)} segment indicating when the
 * task is due.</p>
 *
 * <p>Example output: {@code [D][ ] Submit report (by: Sunday 19 sept)}</p>
 *
 * @see Task
 */
public class Deadline extends Task {
    protected String by;

    public Deadline(String description , String by) {
        super(description);//calling superclass constructor
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}