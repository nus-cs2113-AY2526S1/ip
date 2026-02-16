/**
 * Represents a simple task without a timeline. A <code>Todo</code> object extends
 * {@link Task} and is used for basic todo items.
 */
public class ToDo extends Task{
    /**
     * Constructs a new Todo task with the given description.
     *
     * @param description Description of the task
     */
    public ToDo(String description){
        super(description);
    }

    /**
     * Returns the task formatted as a string.
     *
     * @return Formatted task string.
     */
    @Override
    public String listTasks(){
        return("[T]" + super.listTasks());
    }
}
