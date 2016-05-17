package mehagarg.android.todotask.model;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by meha on 5/17/16.
 */
public class TaskList {
    Context context;
    public ArrayList<Task> tasks;
    public static TaskList instance;

    private TaskList(Context context) {
        this.context = context.getApplicationContext();
        tasks = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Task task = new Task();
            task.setTitle("Title #" + i);
            task.setDescription("Description of the Task #" + i);
            tasks.add(task);
        }
    }

    public static TaskList getInstance(Context context) {
        if (instance == null) {
            instance = new TaskList(context);
        }
        return instance;
    }

    public Task getTaskDetails(UUID id) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getId().equals(id)) {
                return tasks.get(i);
            }
        }
        return null;
    }

    public int getCount() {
        return tasks.size();
    }

    public List<Task> getTasks(){
        return tasks;
    }
}

