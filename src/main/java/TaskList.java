import java.util.ArrayList;
import java.util.List;

/**
 * Mutable container for {@link Task} objects with convenience methods
 * used by the CLI (e.g., add/mark/unmark/delete/list/find).
 *
 * <p>Methods that accept raw {@code args} strings assume specific formats
 * produced by your parser, e.g.:</p>
 * <ul>
 *   <li><b>addDeadline</b>: {@code "<description> /by <due>"}</li>
 *   <li><b>addEvent</b>: {@code "<description> / from <start> to <end>"}</li>
 *   <li><b>mark/unmark/delete</b>: a 1-based index like {@code "3"}</li>
 *   <li><b>find</b>: a non-empty substring to search in descriptions</li>
 * </ul>
 *
 * <p><strong>Note:</strong> {@link #asList()} returns the live backing list.
 * Mutations through that view will affect this {@code TaskList}.</p>
 *
 * @see Task
 * @see Deadline
 * @see Events
 * @see Todo
 */

public class TaskList {
    private final ArrayList<Task> tasks = new ArrayList<>();

    public TaskList() {} //can construct empty list
    public TaskList(List<Task> initial) { //constructing list with input
        if (initial != null) tasks.addAll(initial);
    }

    public boolean isEmpty() { return tasks.isEmpty(); }
    public int size() { return tasks.size(); }
    public Task get(int idx) { return tasks.get(idx); }
    public List<Task> asList() { return tasks; }

    public void list(Ui ui) {
        if (isEmpty()) {
            ui.showEmptyList();
        }
        ui.showLine();
        ui.showListHeader();
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
        ui.showLine();
    }

    public void addTodo(String description, Ui ui) throws EmptyStringException {
        if (description.isEmpty()) throw new EmptyStringException();
        Task t = new Task(description);
        tasks.add(t);
        ui.showTaskAdded(t, size());
    }

    public void mark(String args, Ui ui) throws EmptyStringException{
        if (args.isEmpty()) throw new EmptyStringException();
        int index = Integer.parseInt(args.trim()) - 1;
        Task t = tasks.get(index);
        t.markDone();
        ui.showMarked(t, true);
    }

    public void unmark(String args, Ui ui) throws EmptyStringException {
        if(args.isEmpty()) throw new EmptyStringException();
        int index = Integer.parseInt(args.trim()) - 1;
        Task t = tasks.get(index);
        t.markUndone();
        ui.showMarked(t, false);
    }

    public void addDeadline(String args, Ui ui) throws WrongFormatException {
        if(!args.contains("/by")) throw new WrongFormatException();
        int byIndex = args.indexOf("/by");
        String description = args.substring(0, byIndex).trim();
        String by = args.substring(byIndex + 3).trim();
        Task t = new Deadline(description, by);
        tasks.add(t);
        ui.showAddedDeadline(t, size());
    }

    public void addEvent(String args, Ui ui) throws WrongFormatException {
        if(!args.contains("from") || !args.contains("to") || !args.contains("/")) throw new WrongFormatException();
        int indexFrom = args.indexOf("from");
        int indexTo = args.indexOf("to");
        int indexSlash = args.indexOf("/");
        String from = args.substring(indexFrom + 4, indexTo).trim();
        String to = args.substring(indexTo + 2).trim();
        String description = args.substring(0, indexSlash).trim();
        Task t = new Events(description, to, from);
        tasks.add(t);
        ui.showAddedEvent(t, size());
    }

    public void delete(String args, Ui ui) {
        int index = Integer.parseInt(args.trim()) - 1;
        Task t = tasks.remove(index);
        ui.showDelete(t, size());
    }

    public void find(String args, Ui ui) throws NothingFoundException, EmptyStringException {
        ArrayList<Task> foundList = new ArrayList<>();
        if (args.isEmpty()) throw new EmptyStringException();
        for (Task t : tasks) {
            String description = t.description;
            if(description.contains(args)) {
                foundList.add(t);
            }
        }
        if (foundList.isEmpty()) throw new NothingFoundException();
        ui.showLine();
        ui.showListHeader();
        for (int i = 0; i < foundList.size(); i++) {
            System.out.println((i + 1) + ". " + foundList.get(i));
        }
        ui.showLine();
    }
}
