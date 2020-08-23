import java.util.Scanner;

public class Duke {
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
            if (!line.equals("bye")) {
                System.out.println(line);
            }
        }

        System.out.println("Bye see you SOON!");
    }
}
