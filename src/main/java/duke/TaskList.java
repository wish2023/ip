package duke;

import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> tasks;



    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        tasks = new ArrayList<>();
        for (Task task: taskList) {
            tasks.add(task);
        }
    }

    /**
     * Prints every task in the task list
     */
    public static void displayList() {
        System.out.println("Here's your TODO");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("%d.%s\n", i + 1, tasks.get(i));
        }
    }



    /**
     * Returns size of the task list
     *
     * @return Size of the task list
     */
    public static int getTaskListSize() {
        return tasks.size();
    }

    /**
     * Marks specified task as done.
     * Lets the user know specified task has been marked as done.
     *
     * @param task Task to be marked
     */
    public static void markTaskAsDone(String task) {
        int taskNumber = Integer.parseInt(task);
        tasks.get(taskNumber - 1).setDone();
        System.out.println("I have marked your task as done!");
        System.out.printf("\t%s\n", tasks.get(taskNumber - 1));
    }

    /**
     * Adds specified task to the task list
     * Lets the user know specified task has been added to the task list
     *
     * @param task Task to be added
     */
    public static void addToList(Task task) {
        tasks.add(task);
        System.out.println("Okay! I have added this:");
        System.out.printf("\t%s\n", task);
        System.out.printf("Now you have %d task%s in the list.\n", tasks.size(),
                (tasks.size() == 1) ? "" : "s");
    }

    /**
     * Returns the task description at the specified position in task list
     *
     * @param index Position of task in the task list
     * @return Task description
     */
    public static String getLine(int index) {
        return tasks.get(index).toString();
    }

    /**
     * Deletes the task at the specified position in the task list
     *
     * @param index Position of task in task list
     */
    public static void deleteTask(String index) {
        int taskNumber = Integer.parseInt(index);
        System.out.println("I have deleted this task!");
        System.out.printf("\t%s\n", tasks.get(taskNumber - 1));
        tasks.remove(taskNumber - 1);
        System.out.printf("Now you have %d task%s in the list.\n", tasks.size(),
                (tasks.size() == 1)? "": "s");
    }

    /**
     * Update the task list based on user input
     *
     * @param line Line input by user
     * @throws DukeException If line is in incorrect format
     */
    public static void updateTasks(String line) throws DukeException {
        Parser parser = new Parser();
        String command;
        try {
            command = parser.getCommand(line);
        } catch (DukeException e) {
            System.out.println("Whoa! Please enter a command AND a task");
            return;
        }
        String instance = parser.getInstance(line);

        if (command.equals("done")) {
            markTaskAsDone(instance);

        } else if (command.equals("todo")) {
            addToList(new Todo(instance));

        } else if (command.equals(("delete"))) {
            deleteTask(instance);

        } else if (command.equals("deadline")) {
            try {
                Deadline deadline = parser.getDeadline(line);
                addToList(deadline);
            } catch (DukeException e) {
                System.out.println("Please follow the deadline format: deadline *task* /by *date*");
            }

        } else if (command.equals("event")) {

            try {
                Event event = parser.getEvent(line);
                addToList(event);
            } catch (DukeException e) {
                System.out.println("Please follow the event format: event *task* /at *date*");
            }

        } else {
            System.out.println("Whoops you may have typed a wrong command");
        }
    }
}
