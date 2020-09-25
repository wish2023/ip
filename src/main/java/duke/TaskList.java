package duke;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;



    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        tasks = new ArrayList<>();
        for (Task task: taskList) {
            tasks.add(task);
        }
    }

    public void displayList() {
        System.out.println("Here's your TODO");
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

    public void addToList(String line) throws DukeException {
        Parser parser = new Parser();
        Task task = parser.getTaskFromLine(line);
        tasks.add(task);
    }

    public int getTaskListSize() {
        return tasks.size();
    }

    public void markTaskAsDone(String task) {
        int taskNumber = Integer.parseInt(task);
        tasks.get(taskNumber - 1).setDone();
        System.out.println("I have marked your task as done!");
        System.out.printf("\t%s\n", tasks.get(taskNumber - 1));
    }

    public void addToList(Task task) {
        tasks.add(task);
        System.out.println("Okay! I have added this:");
        System.out.printf("\t%s\n", task);
        System.out.printf("Now you have %d task%s in the list.\n", tasks.size(),
                (tasks.size() == 1) ? "" : "s");
    }

    public String getLine(int index) {
        return tasks.get(index).toString();
    }

    public void deleteTask(String task) {
        int taskNumber = Integer.parseInt(task);
        System.out.println("I have deleted this task!");
        System.out.printf("\t%s\n", tasks.get(taskNumber - 1));
        tasks.remove(taskNumber - 1);
        System.out.printf("Now you have %d task%s in the list.\n", tasks.size(),
                (tasks.size() == 1)? "": "s");
    }

    public void manageTask(String line) throws DukeException {
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
