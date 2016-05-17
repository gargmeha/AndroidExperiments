package mehagarg.android.todotask.model;

import java.util.UUID;

/**
 * Created by meha on 5/17/16.
 */
public class Task {
    private String title;
    private String description;
    private UUID id;

    public Task() {
        id = UUID.randomUUID();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UUID getId() {
        return id;
    }
}
