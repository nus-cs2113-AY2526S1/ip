import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
/**
 * Simple persistence helper that writes task data to a text file and
 * (optionally) loads tasks back.
 *
 * <p><strong>Format:</strong> {@link #save(List)} writes one line per task
 * using each task's {@link Task#toString()} representation. This is a
 * human-readable format meant for display; it is not a stable serialization
 * and cannot be reliably parsed unless you implement a matching reader.</p>
 *
 * <p><strong>Append mode:</strong> Saving is done in <em>append</em> mode
 * (existing file contents are preserved). If you want to overwrite the file,
 * change the {@code FileWriter} constructor accordingly.</p>
 *
 * @see Task
 * @see TaskList
 */

public class Storage {
    private final File file;

    public Storage(String path) { //constructor
        this.file = new File(path);
    }

    public void save(List<Task> tasks) {
        try (FileWriter fw = new FileWriter(file.getAbsolutePath(), true)) {
            for (Task task : tasks) {
                fw.write(task.toString() + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getLocalizedMessage());
        }
    }


    public List<Task> load() {
        List<Task> loaded = new ArrayList<>();
        if (!file.exists()) return loaded;
        return loaded;
    }
}
