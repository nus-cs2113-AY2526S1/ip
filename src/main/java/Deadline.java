/**
 * Task with a single deadline/time by which it should be done.
 */
public class Deadline extends Task{

    protected String by;


    /**
     * Creates a deadline task.
     *
     * @param description task description.
     * @param by deadline of task
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

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

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + by + ")";
    }
}
