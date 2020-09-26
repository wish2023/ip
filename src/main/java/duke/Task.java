package duke;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, char statusIcon) {
        this.description = description;
        isDone = statusIcon == '\u2713' ? true : false;
    }


    /**
     * Mark task as done
     *
     */
    public void setDone() {
        isDone = true;
    }

    private String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String toString() {
        return String.format("[%s] %s", getStatusIcon(),description);
    }
}
