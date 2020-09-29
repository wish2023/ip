package duke;

public class Parser {

    private static final String SPACE = " ";
    private static final int START = 0;
    private static final String TASK_START_KEYWORD = ". [";
    private static final String BY_KEYWORD_FILE = "(by";
    private static final String AT_KEYWORD_FILE = "(at";
    public static final String BY_KEYWORD_INPUT = "/by";
    public static final String AT_KEYWORD_INPUT = "/at";

    private static int getDividerPosition(String line) throws DukeException {
        int dividerPosition = getIndexOfKeyword(line, SPACE);
        return dividerPosition;
    }

    private static int getIndexOfKeyword(String line, String keyword) throws DukeException {
        if (!line.contains(keyword)) {
            throw new DukeException();
        }
        return line.indexOf(keyword);
    }

    private static String getDateFromInput(String line, int dividerPosition) {
        return line.substring(dividerPosition + 4);
    }

    private static String getTaskFromInput(String line, int startPosition, int endPosition) {
        return line.substring(startPosition + 1, endPosition - 1);
    }

    private static String getTodoFromFile(String line, int taskStartPosition) {
        return line.substring(taskStartPosition + 9);
    }

    private static String getTaskFromFile(String line, int startPosition, int endPosition) {
        return line.substring(startPosition + 9, endPosition - 1);
    }

    private static char getStatusIconFromFile(String line, int taskStartPosition) {
        return line.charAt(taskStartPosition + 6);
    }

    private static char getTaskTypeFromFile(String line, int taskStartPosition) {
        return line.charAt(taskStartPosition + 3);
    }

    private static String getDateFromFile(String line, int datePosition) {
        return line.substring(datePosition + 5, line.length() - 1);
    }

    private static boolean isInstanceEmpty(String instance) {
        return instance.length() == 0;
    }



    /**
     * Returns a task given a line from duke.txt
     *
     * @param line Line in duke.txt
     * @return Task the line refers to
     * @throws DukeException If file is in incorrect format
     */
    public static Task getTaskFromLine(String line) throws DukeException {
        int taskStartPosition = getIndexOfKeyword(line, TASK_START_KEYWORD);
        char taskType = getTaskTypeFromFile(line, taskStartPosition);
        char statusIcon = getStatusIconFromFile(line, taskStartPosition);

        Task task = null;

        switch (taskType) {
        case 'T':
            String todoTask = getTodoFromFile(line, taskStartPosition);
            task = new Todo(todoTask, statusIcon);
            break;
        case 'D':
            int byPosition = getIndexOfKeyword(line, BY_KEYWORD_FILE);
            String deadlineTask = getTaskFromFile(line, taskStartPosition, byPosition);
            String deadlineDate = getDateFromFile(line, byPosition);
            task = new Deadline(deadlineTask, statusIcon, deadlineDate);
            break;
        case 'E':
            int atPosition = getIndexOfKeyword(line, AT_KEYWORD_FILE);
            String eventTask = getTaskFromFile(line, taskStartPosition, atPosition);
            String eventDate = getDateFromFile(line, atPosition);
            task = new Event(eventTask, statusIcon, eventDate);
            break;
        default:
            break;
        }

        return task;
    }


    /**
     * Returns the command from a user input containing the command and instance
     *
     * @param line Line containing command and instance
     * @return User's command to Duke
     */
    public static String getCommand(String line) {
        int dividerPosition;
        String command;
        try {
            dividerPosition = getDividerPosition(line);
            command = line.substring(START, dividerPosition);
        } catch (DukeException e) {
            command = line; // Command is only 1 word
        }

        return command;
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
        if (isInstanceEmpty(instance)) {
            throw new DukeException();
        }

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
        int byPosition = getIndexOfKeyword(line, BY_KEYWORD_INPUT);
        String deadlineTask = getTaskFromInput(line, dividerPosition, byPosition);
        String date = getDateFromInput(line, byPosition);
        return new Deadline(deadlineTask, date);
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
        int atPosition = getIndexOfKeyword(line, AT_KEYWORD_INPUT);
        String eventTask = getTaskFromInput(line, dividerPosition, atPosition);
        String date = getDateFromInput(line, atPosition);
        return new Event(eventTask, date);
    }



}
