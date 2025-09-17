package prime.task;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String getTickUnTickIcon(){
        if (this.getIsDone()){
            return "[X]";
        } else {
            return "[ ]";
        }
    }

    @Override
    public String toString() {
        return this.description;
    }

    public String getDescription() {
        return this.description;
    }
}
