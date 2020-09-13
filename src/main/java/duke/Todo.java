package duke;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public Todo(String description, char statusIcon) {
        super(description, statusIcon);
    }

    public String toString() {
        return "[T]" + super.toString();
    }
}