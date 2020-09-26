package duke;

import java.util.Scanner;

public class Ui {


    /**
     * Prints goodbye message
     *
     */
    public static void printGoodbye() {
        System.out.println("Bye see you SOON!");
    }


    /**
     * Prints hello message
     */
    public static void printHello() {
        System.out.println("Hey it's your favorite chatbot buddy!");
        System.out.println("How can I assist you today?");
    }


    /**
     * Interacts with the user based on user input
     *
     * @param in Scanner for input
     * @param line Line input by user
     * @param taskList Task list created by user
     * @throws DukeException If line is in incorrect format
     */
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
                manageList(line, taskList);
                break;
            }
        }
    }

    private static void manageList(String line, TaskList taskList) throws DukeException {
        taskList.manageTask(line);
    }

    private static void displayList(TaskList taskList) {
        taskList.displayList();
    }

    /**
     * Prints message when no prior task list is found
     *
     */
    public void showLoadingError() {
        System.out.println("No old tasklist found time to start fresh!");
    }
}
