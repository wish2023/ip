package duke;

public class Parser {


    public static Task getTaskFromLine(String line) throws DukeException { // For file
        int taskTypePosition = splitCommandAndTask(line, ". [");
        char taskType = line.charAt(taskTypePosition + 3);
        char statusIcon = line.charAt(taskTypePosition + 6);

        Task task = null;

        switch (taskType) {
        case 'T':
            String todoTask = line.substring(taskTypePosition + 9);
            task = new Todo(todoTask, statusIcon);
            break;
        case 'D':
            int byPosition = splitCommandAndTask(line, "(by");
            String deadlineTask = line.substring(taskTypePosition + 9, byPosition - 1);
            String by = line.substring(byPosition + 5, line.length() - 1);
            task = new Deadline(deadlineTask, statusIcon, by);
            break;
        case 'E':
            int atPosition = splitCommandAndTask(line, "(at");
            String eventTask = line.substring(taskTypePosition + 9, atPosition - 1);
            String at = line.substring(atPosition + 5, line.length() - 1);
            task = new Event(eventTask, statusIcon, at);
            break;
        default:
            break;
        }

        return task;
    }

    public static int splitCommandAndTask(String line, String keyword) throws DukeException {
        if (!line.contains(keyword)) {
            throw new DukeException();
        }
        return line.indexOf(keyword);
    }


    public static String getCommand(String line) throws DukeException {
        int dividerPosition = getDividerPosition(line);

        String command = line.substring(0, dividerPosition);
        return command;
    }

    public static int getDividerPosition(String line) throws DukeException {
        int dividerPosition = 0;
        dividerPosition = splitCommandAndTask(line, " ");
        return dividerPosition;
    }

    public static String getInstance(String line) throws DukeException {
        int dividerPosition = getDividerPosition(line);

        String instance = line.substring(dividerPosition + 1);
        return instance;
    }
    
    public static Deadline getDeadline(String line) throws DukeException {
        int dividerPosition = getDividerPosition(line);
        int byPosition = splitCommandAndTask(line, "/by");;
        String deadlineTask = line.substring(dividerPosition + 1, byPosition - 1);
        String by = line.substring(byPosition + 4);
        return new Deadline(deadlineTask, by);
    }

    public static Event getEvent(String line) throws DukeException {
        int dividerPosition = getDividerPosition(line);
        int atPosition = splitCommandAndTask(line, "/at");
        String eventTask = line.substring(dividerPosition + 1, atPosition - 1);
        String date = line.substring(atPosition + 4);
        return new Event(eventTask, date);
    }

}
