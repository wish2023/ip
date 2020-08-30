import java.util.Scanner;

public class Duke {
    public static Task[] list = new Task[100];
    public static int listSize = 0;

    public static void addToList(Task task) {
        list[listSize++] = task;
        System.out.printf("Okay! I have added %s\n", task.getDescription());
    }

    public static void displayList() {
        System.out.println("Here's your TODO");
        for (int i = 0; i < listSize; i++) {
            System.out.printf("%d.%s\n", i + 1,list[i]);
        }
    }

    public static void updateTask(String line) {
        int taskNumber = Integer.parseInt(line.replace("done ",""));
        list[taskNumber - 1].setDone();
        System.out.println("I have marked your task as done!");
        System.out.printf("[%s] %s\n", list[taskNumber - 1].getStatusIcon(),
                list[taskNumber - 1].getDescription());
    }

    public static void updateList(String line) {
        if (line.contains("done ")) {
            updateTask(line);
        } else {
            Task t = new Task(line);
            addToList(t);
        }
    }


    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        Scanner in = new Scanner(System.in);
        String line = "initialising";

        printMessage();

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
        System.out.println("Bye see you SOON!");
    }

    public static void printMessage() {
        System.out.println("Hey it's your favorite chatbot buddy!");
        System.out.println("How can I assist you today?");
    }


}
