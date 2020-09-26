package duke;

public class Parser {


    /**
     * Returns a task given a line from duke.txt
     *
     * @param line Line in duke.txt
     * @return Task the line refers to
     * @throws DukeException If file is not formatted correctly
     */
    public static Task getTaskFromLine(String line) throws DukeException { // For file
        int taskTypePosition = getIndexOfKeyword(line, ". [");
        char taskType = line.charAt(taskTypePosition + 3);
        char statusIcon = line.charAt(taskTypePosition + 6);

        Task task = null;

        switch (taskType) {
        case 'T':
            String todoTask = line.substring(taskTypePosition + 9);
            task = new Todo(todoTask, statusIcon);
            break;
        case 'D':
            int byPosition = getIndexOfKeyword(line, "(by");
            String deadlineTask = line.substring(taskTypePosition + 9, byPosition - 1);
            String by = line.substring(byPosition + 5, line.length() - 1);
            task = new Deadline(deadlineTask, statusIcon, by);
            break;
        case 'E':
            int atPosition = getIndexOfKeyword(line, "(at");
            String eventTask = line.substring(taskTypePosition + 9, atPosition - 1);
            String at = line.substring(atPosition + 5, line.length() - 1);
            task = new Event(eventTask, statusIcon, at);
            break;
        default:
            break;
        }

        return task;
    }


    private static int getIndexOfKeyword(String line, String keyword) throws DukeException {
        if (!line.contains(keyword)) {
            throw new DukeException();
        }
        return line.indexOf(keyword);
    }


    /**
     * Returns the command from a user input containing the command and instance
     *
     * @param line Line containing command and instance
     * @return User's command to Duke
     * @throws DukeException If user inputs a single word only
     */
    public static String getCommand(String line) throws DukeException {
        int dividerPosition = getDividerPosition(line);

        String command = line.substring(0, dividerPosition);
        return command;
    }

    private static int getDividerPosition(String line) throws DukeException {
        int dividerPosition = 0;
        dividerPosition = getIndexOfKeyword(line, " ");
        return dividerPosition;
    }

    /**
     * Returns the instance that a command relates to from user input
     *
     * @param line Line containing command and instance
     * @return Specific instance user's command is applied to
     * @throws DukeException if user inputs a single word only
     */
    public static String getInstance(String line) throws DukeException {
        int dividerPosition = getDividerPosition(line);

        String instance = line.substring(dividerPosition + 1);
        return instance;
    }

    /**
     * Creates a deadline based on user input
     *
     * @param line Line containing command and instance
     * @return Deadline created by user
     * @throws DukeException If line is in incorrect format
     */
    public static Deadline getDeadline(String line) throws DukeException {
        int dividerPosition = getDividerPosition(line);
        int byPosition = getIndexOfKeyword(line, "/by");;
        String deadlineTask = line.substring(dividerPosition + 1, byPosition - 1);
        String by = line.substring(byPosition + 4);
        return new Deadline(deadlineTask, by);
    }

    /**
     * Creates an event based on user input
     *
     * @param line Line containing command and instance
     * @return Event created by user
     * @throws DukeException If line is in incorrect format
     */
    public static Event getEvent(String line) throws DukeException {
        int dividerPosition = getDividerPosition(line);
        int atPosition = getIndexOfKeyword(line, "/at");
        String eventTask = line.substring(dividerPosition + 1, atPosition - 1);
        String date = line.substring(atPosition + 4);
        return new Event(eventTask, date);
    }

}
