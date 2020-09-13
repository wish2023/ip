package duke;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static final String INITIALISING = "initialising";
    public static final int MAX_LIST_SIZE = 100;
    //private static Task[] tasks = new Task[MAX_LIST_SIZE];
    private static ArrayList<Task> tasks = new ArrayList<>(MAX_LIST_SIZE);
    private static int listSize = 0;

    public static void addToList(Task task) {
        listSize++;
        tasks.add(task);
        System.out.println("Okay! I have added this:");
        System.out.printf("\t%s\n", task);
        System.out.printf("Now you have %d task%s in the list.\n", listSize,
                (listSize == 1)? "": "s");
    }


    public static void displayList() {
        System.out.println("Here's your TODO");
        for (int i = 0; i < listSize; i++) {
            System.out.printf("%d.%s\n", i + 1, tasks.get(i));
        }
    }

    public static void markTaskAsDone(String task) {
        int taskNumber = Integer.parseInt(task);
        tasks.get(taskNumber - 1).setDone();
        System.out.println("I have marked your task as done!");
        System.out.printf("\t%s\n", tasks.get(taskNumber - 1));
    }

    public static void deleteTask(String task) {
        int taskNumber = Integer.parseInt(task);
        System.out.println("I have deleted this task!");
        System.out.printf("\t%s\n", tasks.get(taskNumber - 1));
        tasks.remove(taskNumber - 1);
        listSize--;
        System.out.printf("Now you have %d task%s in the list.\n", listSize,
                (listSize == 1)? "": "s");
    }

    public static void updateList(String line) throws DukeException {
        int dividerPosition = 0;

        try {
            dividerPosition = splitCommandAndTask(line, " ");
        } catch (DukeException e) {
            System.out.println("Whoa! Please enter a command AND a task");
            return;
        }
        String command = line.substring(0, dividerPosition);
        String task = line.substring(dividerPosition + 1);

        if (command.equals("done")) {
            markTaskAsDone(task);

        } else if (command.equals("todo")) {
            addToList(new Todo(task));

        } else if (command.equals(("delete"))) {
            deleteTask(task);

        } else if (command.equals("deadline")) {
            int byPosition = 0;

            try {
                byPosition = splitCommandAndTask(line, "/by");
            } catch (DukeException e) {
                System.out.println("Please follow the deadline format: deadline *task* /by *date*");
                return;
            }
            String deadlineTask = line.substring(dividerPosition + 1, byPosition - 1);
            String by = line.substring(byPosition + 4);
            addToList(new Deadline(deadlineTask, by));

        } else if (command.equals("event")) {
            int atPosition = 0;

            try {
                atPosition = splitCommandAndTask(line, "/at");
            } catch (DukeException e) {
                System.out.println("Please follow the event format: event *task* /at *date*");
                return;
            }
            String eventTask = line.substring(dividerPosition + 1, atPosition - 1);
            String date = line.substring(atPosition + 4);
            addToList(new Event(eventTask, date));

        } else {
            System.out.println("Whoops you may have typed a wrong command");
        }
    }

    public static int splitCommandAndTask(String line, String keyword) throws DukeException {
        if (!line.contains(keyword)) {
            throw new DukeException();
        }
        return line.indexOf(keyword);
    }

    
    public static void runBot(Scanner in, String line) throws DukeException {
        while (!line.equals("bye")) {
            line = in.nextLine();
            switch (line) {
            case "bye":
                break;
            case "list":
                displayList();
                break;
            default:
                updateList(line);
                break;
            }
        }
    }

    public static void printGoodbye() {
        System.out.println("Bye see you SOON!");
    }

    public static void printHello() {
        System.out.println("Hey it's your favorite chatbot buddy!");
        System.out.println("How can I assist you today?");
    }


    public static void main(String[] args) throws DukeException {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        Scanner in = new Scanner(System.in);
        String line = INITIALISING;
        printHello();
        runBot(in, line);
        printGoodbye();
    }

}