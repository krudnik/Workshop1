package pl.coderslab;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

public class TaskManager {
    public static final String PATH = "/home/krs/Desktop/Coders Lab/repozytorium git/Workshop1/TaskManager/tasks.csv";
    static String[][] tasks;


    public static void main(String[] args) {
        tasks = fileToString();
        menu();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            switch (input) {
                case "add":
                    addTask();
                    break;
                case "list":
                    listTasks();
                    break;
                case "remove":
                    removeTask(tasks, getTheNumber());
                    System.out.println("Value was successfully deleted");
                    break;
                case "exit":
                    stringToFile();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Please select a correct option.");
            }
            menu();
        }
    }


    public static void menu() {
        System.out.println(ConsoleColors.BLUE + "Please select an option:" + ConsoleColors.RESET);
        String[] menu = {"add", "remove", "list", "exit"};
        for (String s : menu) {
            System.out.println(s);
        }
    }

    public static String[][] fileToString() {
        Path path = Paths.get(PATH);
        int rowCount = 0;
        try {
            for (String line : Files.readAllLines(path)) {
                rowCount++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String tab[][] = new String[rowCount][3];
        int i = 0;
        try {
            for (String line : Files.readAllLines(path)) {
                tab[i] = line.split(",");
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tab;
    }

    public static String input() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            if (scanner.hasNextLine()) {
                return scanner.nextLine();
            }
        }
    }

    public static void addTask() {
        StringBuilder stringBuilder = new StringBuilder();
        System.out.println("Please add task description");
        stringBuilder.append(input()).append(" ");
        System.out.println("Please add task due date in format YYYY-MM-DD");
        stringBuilder.append(input()).append(" ");
        System.out.println("Is your task important: true/false");
        stringBuilder.append(input());
        String[] newTask = stringBuilder.toString().split(" ");
        tasks = Arrays.copyOf(tasks, tasks.length + 1);
        tasks[tasks.length - 1] = newTask;
    }

    public static void listTasks() {
        for (int i = 0; i < tasks.length; i++) {
            System.out.print(i + " : ");
            for (int j = 0; j < 3; j++) {
                System.out.print(tasks[i][j] + " ");
            }
            System.out.println();
        }
    }
    public static boolean isNumberGreaterEqualZero(String input) {
        if (NumberUtils.isParsable(input)) {
            return Integer.parseInt(input) >= 0;
        }
        return false;
    }

    public static int getTheNumber() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please select number to remove.");
        String n = scanner.nextLine();
        while (!isNumberGreaterEqualZero(n)) {
            System.out.println("Incorrect argument passed. Please give number greater or equal 0");
            scanner.nextLine();
        }
        return Integer.parseInt(n);
    }

    public static void removeTask(String[][] tab, int index) {
        try {
            if (index < tab.length){
                tasks = ArrayUtils.remove(tab, index);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Element not exist in tab");
        }
    }

    public static void stringToFile() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < tasks.length; i++) {
            for (int j = 0; j < 3; j++) {
                if (j != 2) {
                    stringBuilder.append(tasks[i][j]).append(", ");
                } else {
                    stringBuilder.append(tasks[i][j]).append("\n");
                }
            }
        }
        try (FileWriter fileWriter = new FileWriter(PATH, false)) {
            String tasksToFile = stringBuilder.toString();
            fileWriter.append(tasksToFile);
        } catch (IOException ex) {
            System.out.println("Błąd zapisu do pliku.");
        }
        System.out.println(ConsoleColors.RED + " Bye, bye!");
    }
}
