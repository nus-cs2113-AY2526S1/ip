public class ToDo extends Task {

    public ToDo(String description) {
        super(description); // superclass to call parent's constructor/method/field
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}