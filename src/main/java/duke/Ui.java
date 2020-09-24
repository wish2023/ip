package duke;

import java.util.Scanner;

public class Ui {


    public static void printGoodbye() {
        System.out.println("Bye see you SOON!");
    }


    public static void printHello() {
        System.out.println("Hey it's your favorite chatbot buddy!");
        System.out.println("How can I assist you today?");
    }



    public static void runBot(Scanner in, String line, TaskList taskList) throws DukeException {
        while (!line.equals("bye")) {
            line = in.nextLine();
            switch (line) {
            case "bye":
                break;
            case "list":
                displayList(taskList);
                break;
            default:
                updateList(line, taskList);
                break;
            }
        }
    }

    private static void updateList(String line, TaskList taskList) throws DukeException {
        taskList.updateTasks(line);
    }

    public static void displayList(TaskList taskList) {
        taskList.displayList();
    }

    public void showLoadingError() {
        System.out.println("No old tasklist found time to start fresh!");
    }
}
