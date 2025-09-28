package superidol.command;

import superidol.storage.Storage;
import superidol.tasklist.TaskList;

public class FindCommand extends Command {
    private String keyword;

    public  FindCommand(String keyword) {
        this.keyword = keyword;
    }

    public void execute(TaskList taskList, Storage storage) {
        taskList.findByKeyword(keyword);
    }
}
