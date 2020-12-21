package hussamheriz.aug.todolistproject.Models;

public class Task {

    private int categoryId;
    private String title;
    private String date;
    private String description;
    private boolean isDone;

    public Task(int categoryId, String title, String date, String description, boolean isDone) {
        this.categoryId = categoryId;
        this.title = title;
        this.date = date;
        this.description = description;
        this.isDone = isDone;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }
}
