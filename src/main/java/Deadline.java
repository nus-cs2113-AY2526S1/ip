public class Deadline extends Task {
    protected String do_by;

    public Deadline(String description, String do_by) {
        super(description); // superclass to call parent's constructor/method/field
        this.do_by = do_by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + do_by + ")";
    }
}

