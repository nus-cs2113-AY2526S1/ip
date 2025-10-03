/**
 * A simple to-do task without any date/time fields.
 *
 * <p>Displays with a {@code [T]} type tag followed by the base {@link Task}
 * representation.</p>
 *
 * <p>Example output: {@code [T][ ] sleep}</p>
 *
 * @see Task
 */
public class Todo extends Task{
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
