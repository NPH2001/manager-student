import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        StudentManager manager = new StudentManager(students, scanner);
        int choice;

        do {
            System.out.println("\n--- Manager Student ---");
            System.out.println("1. Add student");
            System.out.println("2. Edit student");
            System.out.println("3. Delete student");
            System.out.println("4. Show student list");
            System.out.println("5. Search student");
            System.out.println("6. Load data from file");
            System.out.println("7. Save data to file");
            System.out.println("0. Exit");
            System.out.println("Choose function: ");
            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    manager.addStudent();
                    break;
                case 2:
                    manager.editStudent();
                    break;
                case 3:
                    manager.deleteSutdent();
                    break;
                case 4:
                    manager.displayStudents();
                    break;
                case 5:
                    manager.searchStudent();
                    break;
                case 6:
                    System.out.println("Enter file name to load data: ");
                    String loadFile = scanner.nextLine();
                    manager.loadFromFile(loadFile);
                    break;
                case 7:
                    System.out.println("Enter file name to save data: ");
                    String nameFile = scanner.nextLine();

                    LocalTime time = LocalTime.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH-mm-ss");
                    String currentTimeFormat = formatter.format(time);
                    String saveFile = currentTimeFormat + "_" + nameFile;

                    System.out.println("saveFile: " + saveFile);
                    try {
                        manager.saveToFile(saveFile);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case 0:
                    System.out.println("Exit!");
                    break;
                default:
                    System.out.println("Invalid choose!.");
            }
        } while (choice != 0);
        scanner.close();
    }
}