package duke;


import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;

public class Duke {
    public static final String INITIALISE_MESSAGE = "initialising";
    public static final int MAX_LIST_SIZE = 100;
    private static ArrayList<Task> tasks = new ArrayList<>(MAX_LIST_SIZE);
    public static final String FILE_PATH = "data/duke.txt";
    public static final String DIRECTORY_PATH = "data";
    private static int listSize = 0;


    public static void writeToFile() throws IOException {
        FileWriter filewriter = new FileWriter(FILE_PATH);

        for (int i = 0; i < listSize; i++) {
            String line = tasks.get(i).toString();
            filewriter.write((i + 1) + ". " + line + "\n");
        }

        filewriter.close();
    }


    public static void initialiseList() throws FileNotFoundException, DukeException {
        File file = new File(FILE_PATH);
        Scanner fileScanner = new Scanner(file);
        while (fileScanner.hasNext()) {
            addToList(fileScanner.nextLine());
        }
    }


    private static void addToList(String line) throws DukeException {
        int taskTypePosition = splitCommandAndTask(line, ". [");
        char taskType = line.charAt(taskTypePosition + 3);
        char statusIcon = line.charAt(taskTypePosition + 6);

        switch (taskType) {
        case 'T':
            String task = line.substring(taskTypePosition + 9);
            addToList(new Todo(task, statusIcon));
            break;
        case 'D':
            int byPosition = splitCommandAndTask(line, "(by");
            String deadlineTask = line.substring(taskTypePosition + 9, byPosition - 1);
            String by = line.substring(byPosition + 5, line.length() - 1);
            addToList(new Deadline(deadlineTask, statusIcon, by));
            break;
        case 'E':
            int atPosition = splitCommandAndTask(line, "(at");
            String eventTask = line.substring(taskTypePosition + 9, atPosition - 1);
            String at = line.substring(atPosition + 5, line.length() - 1);
            addToList(new Event(eventTask, statusIcon, at));
            break;
        default:
            break;
        }
    }


    public static void save() {
        try {
            File directory = new File((DIRECTORY_PATH));
            if (!directory.isDirectory()) {
                directory.mkdir();
            }

            File file = new File(FILE_PATH);
            if (!file.exists()) {
                file.createNewFile();
            }
            writeToFile();

        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }


    public static void boot() throws DukeException {
        try {
            initialiseList();
        } catch (FileNotFoundException e) {
            System.out.println("No prior list found. Time to start a new list!");
        }
    }


    public static void addToList(Task task) {
        listSize++;
        tasks.add(task); // Add try here
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
        Scanner in = new Scanner(System.in);
        String line = INITIALISE_MESSAGE;
        boot();
        printHello();
        runBot(in, line);
        printGoodbye();
        save();
    }
}
