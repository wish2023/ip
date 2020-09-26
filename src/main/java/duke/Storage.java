package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private static String filePath;
    private static String directoryPath;


    public Storage(String filePath, String directoryPath) {
        this.filePath = filePath;
        this.directoryPath = directoryPath;
    }

    /**
     * Returns a task list after reading from duke.txt
     *
     * @return Task list created
     * @throws DukeException If file is not formatted correctly
     * @throws FileNotFoundException If duke.txt is not found
     */
    public static ArrayList<Task> load() throws DukeException, FileNotFoundException {

        ArrayList<Task> taskList = new ArrayList<>();
        File file = new File(filePath);
        Scanner fileScanner = new Scanner(file);
        Parser parser = new Parser();
        while (fileScanner.hasNext()) {
            Task task = parser.getTaskFromLine(fileScanner.nextLine());
            taskList.add(task);
        }
        return taskList;
    }

    private static void writeToFile(TaskList taskList) throws IOException {
        FileWriter filewriter = new FileWriter(filePath);

        for (int i = 0; i < taskList.getTaskListSize(); i++) {
            String line = taskList.getLine(i);
            filewriter.write((i + 1) + ". " + line + "\n");
        }

        filewriter.close();
    }

    /**
     * Saves the task list created into duke.txt
     *
     * @param taskList Task list created by user
     */
    public static void save(TaskList taskList) {
        try {
            File directory = new File((directoryPath));
            if (!directory.isDirectory()) {
                directory.mkdir();
            }

            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }
            writeToFile(taskList);

        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}
