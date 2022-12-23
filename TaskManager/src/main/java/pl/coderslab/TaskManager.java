package pl.coderslab;

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
        switch (input()) {
            case "add":
                addTask();
                break;
            case "list":
                listTasks();
                break;
            default:
                System.out.println("Please select a correct option.");
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
        return scanner.next();
    }

    public static void addTask() {

    }

    public static void listTasks() {
        for (int i = 0; i < tasks.length; i++) {
            System.out.print(Integer.toString(i) + " : ");
            for (int j = 0; j < 3; j++) {
                System.out.print(tasks[i][j] + " ");
            }
            System.out.println();
        }
    }
}
