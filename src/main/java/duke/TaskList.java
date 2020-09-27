package duke;

import java.util.ArrayList;
import java.util.Arrays;

public class TaskList {
    private ArrayList<Task> tasks;
    ArrayList<String> commands = new ArrayList<String>
            (Arrays.asList("todo", "deadline", "event", "done", "delete", "find"));


    public TaskList() {
        tasks = new ArrayList<>();
    }


    public TaskList(ArrayList<Task> taskList) {
        tasks = new ArrayList<>();
        for (Task task: taskList) {
            tasks.add(task);
        }
    }

    private boolean isCommandValid(String command) {
        return commands.contains(command);
    }


    /**
     * Prints every task in the task list
     */
    public void displayList() {
        System.out.println("Here's your TASK LIST");
        printTasks();
    }


    private ArrayList<Task> getShortlistedTasks(String instance) {
        ArrayList<Task> shortlistedTasks = new ArrayList<>();
        for (Task task: tasks) {
            if (task.getDescription().contains(instance)) {
                shortlistedTasks.add(task);
            }
        }
        return shortlistedTasks;
    }


    private void printTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("%d.%s\n", i + 1, tasks.get(i));
        }
    }


    /**
     * Returns size of the task list
     *
     * @return Size of the task list
     */
    public int getTaskListSize() {
        return tasks.size();
    }


    /**
     * Marks specified task as done.
     * Lets the user know specified task has been marked as done.
     *
     * @param task Task to be marked
     */
    public void markTaskAsDone(String task) {
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
    public void addToList(Task task) {
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
    public String getLine(int index) {
        return tasks.get(index).toString();
    }


    /**
     * Deletes the task at the specified position in the task list
     *
     * @param index Position of task in task list
     */
    public void deleteTask(String index) {
        int taskNumber = Integer.parseInt(index);
        System.out.println("I have deleted this task!");
        System.out.printf("\t%s\n", tasks.get(taskNumber - 1));
        tasks.remove(taskNumber - 1);
        System.out.printf("Now you have %d task%s in the list.\n", tasks.size(),
                (tasks.size() == 1)? "": "s");
    }


    /**
     * Executes user's command
     *
     * @param line Line input by user
     * @throws DukeException If line is in incorrect format
     */
    public void manageTask(String line) throws DukeException {
        Parser parser = new Parser();
        String command = parser.getCommand(line);
        if (!isCommandValid(command)) {
            System.out.println("I don't know what " + command + " means");
            return;
        }

        String instance;

        try {
            instance = parser.getInstance(line);
        } catch (DukeException e) {
            System.out.println("Whoa! Please don't leave the description empty");
            return;
        }


        if (command.equals("done")) {
            markTaskAsDone(instance);

        } else if (command.equals("todo")) {
            addToList(new Todo(instance));

        } else if (command.equals("delete")) {
            deleteTask(instance);

        } else if (command.equals("find")) {
            System.out.println("Here are the matching tasks in your list:");
            TaskList shortListedTasks = new TaskList(getShortlistedTasks(instance));
            shortListedTasks.printTasks();

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
