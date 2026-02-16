/**
 * Task with a deadline by which it should be done
 */
public class Deadline extends Task{

    protected String by;


    /**
     * Creates a deadline task
     *
     * @param description task description
     * @param by deadline of task
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Parses a deadline command string and constructs a {@code Deadline}
     *
     * @param sentenceArray user input in an array
     * @param sentence full input string
     * @return result
     * @throws BobException if the description or deadline is missing
     */
    public static Deadline getDeadline(String[] sentenceArray, String sentence) throws BobException {
        if (sentenceArray.length < 2 || sentenceArray[1].isEmpty()) {
            throw new BobException("Description of a deadline cannot be empty - Bob 😡");
        }
        int firstSpaceIndex = sentence.indexOf(" ");
        int byIndex = sentence.indexOf("/by");
        String deadlineTask = sentence.substring(firstSpaceIndex + 1, byIndex).trim();
        String by = sentence.substring(byIndex + 3).trim();
        return new Deadline(deadlineTask, by);
    }

    /**
     * Returns the string representation of the deadline task
     *
     * @return result
     */
    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + by + ")";
    }
}
