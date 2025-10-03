package chara;

import java.util.ArrayList;
import chara.task.Task;

/**
 * A specialized list that holds Task objects.
 */
public class TaskList extends ArrayList<Task> {
    /**
     * Returns a new TaskList containing tasks whose description
     * contains the given keyword (case-insensitive).
     *
     * @param keyword the search keyword
     * @return a TaskList of matching tasks
     */
    public TaskList find(String keyword) {
        TaskList results = new TaskList();
        for (Task t : this) {
            if (t.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                results.add(t);
            }
        }
        return results;
    }
}
