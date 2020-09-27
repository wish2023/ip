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

    private void printNumberOfTasks() {
        System.out.printf("Now you have %d task%s in the list.\n", tasks.size(),
                (tasks.size() == 1) ? "" : "s");
    }

    private void printDeleteMessage() {
        System.out.println("I have deleted this task!");
    }

    private void printErrorMessage() {
        System.out.println("You entered an invalid number");
    }

    private boolean isCommandInvalid(String command) {
        return !commands.contains(command);
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
     * Prints every task in the task list
     */
    public void displayList() {
        System.out.println("Here's your TASK LIST");
        printTasks();
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
        int taskNumber = Integer.parseInt(task) - 1;
        try {
            tasks.get(taskNumber).setDone();
            System.out.println("I have marked your task as done!");
            System.out.printf("\t%s\n", tasks.get(taskNumber));
        } catch (IndexOutOfBoundsException e) {
            printErrorMessage();
        }

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
        printNumberOfTasks();
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
        int taskNumber = Integer.parseInt(index) - 1;
        try {
            String deletedTask = "\t" + tasks.get(taskNumber);
            printDeleteMessage();
            System.out.println(deletedTask);
            tasks.remove(taskNumber);
        } catch (IndexOutOfBoundsException e) {
            printErrorMessage();
        }
        printNumberOfTasks();
    }


    /**
     * Executes user's command
     *
     * @param line Line input by user
     */
    public void manageTask(String line) {
        Parser parser = new Parser();
        String command = parser.getCommand(line);
        if (isCommandInvalid(command)) {
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
