package duke;


import java.io.FileNotFoundException;
import java.util.Scanner;


public class Duke {
    private static final String INITIALISE_MESSAGE = "initialising";
    //private static ArrayList<Task> tasks = new ArrayList<>();
    private static final String FILE_PATH = "data/duke.txt";
    private static final String DIRECTORY_PATH = "data";
    private static Storage storage;
    private static TaskList tasks;
    private static Ui ui;



    private static void runDuke() throws DukeException {
        ui = new Ui();
        Scanner in = new Scanner(System.in);
        storage = new Storage(FILE_PATH, DIRECTORY_PATH);
        try {
            tasks = new TaskList(storage.load());
        }
        catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
        String line = INITIALISE_MESSAGE;
        ui.printHello();
        ui.runBot(in, line, tasks);
        ui.printGoodbye();
        storage.save(tasks);
    }


    public static void main(String[] args) throws DukeException {
        runDuke();
    }
}
