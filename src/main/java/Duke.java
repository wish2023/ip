import java.util.Scanner;

public class Duke {
    public static String[] list = new String[100];
    public static int listSize = 0;

    public static void addToList(String task) {
        list[listSize++] = task;
        System.out.printf("Okay! I have added %s\n", task);
    }

    public static void displayList() {
        for (int i = 0; i < listSize; i++) {
            System.out.printf("%d. %s\n", i + 1, list[i]);
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

        System.out.println("Hey it's your favorite chatbot buddy!");
        System.out.println("How can I assist you today?");

        while (!line.equals("bye")) {
            line = in.nextLine();
            switch (line) {
            case "bye":
                break;
            case "list":
                displayList();
                break;
            default:
                addToList(line);
                break;
            }
        }

        System.out.println("Bye see you SOON!");
    }
}
