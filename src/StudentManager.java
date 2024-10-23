import Common.InputUtils;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class StudentManager {
    private List<Student> students;
    private Scanner scanner;

    public StudentManager(List<Student> students, Scanner scanner) {
        this.students = students;
        this.scanner = scanner;
    }

    public void addStudent() {
        System.out.println("Enter student Id: ");
        String id = scanner.nextLine();
        System.out.println("Enter student name: ");
        String name = scanner.nextLine();
        System.out.println("Enter student age: ");
        int age = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter class name: ");
        String className = scanner.nextLine();

        students.add(new Student(id, name, age, className));
        System.out.println("Add student success!");
    }

    public void editStudent() {
        System.out.println("Enter student id to exit: ");
        String id = scanner.nextLine();
        Student student = findStudentById(id);
        if (student != null) {
            int choice;
            do {
                System.out.println("\n Select the item you want to edit: ");
                System.out.println("1. Edit name");
                System.out.println("2. Edit age");
                System.out.println("3. Edit className");
                System.out.println("0. Exit editing");
                System.out.println("Choice: ");
                choice = InputUtils.getIntInput(scanner);

                switch (choice) {
                    case 1:
                        System.out.println("Enter new name: ");
                        student.setName(scanner.nextLine());
                        System.out.println("Name has been updated.");
                        break;
                    case 2:
                        System.out.println("Enter new age: ");
                        student.setAge(Integer.parseInt(scanner.nextLine()));
                        System.out.println("Age has been updated.");
                        break;
                    case 3:
                        System.out.println("Enter new className: ");
                        student.setClassName(scanner.nextLine());
                        System.out.println("ClassName has been updated.");
                        break;
                    case 0:
                        System.out.println("Exit successfully");
                        break;
                    default:
                        System.out.println("Invaild choice. Please choose again.");
                }
            } while (choice != 0);
        } else {
            System.out.println("No student found with this Id!");
        }
    }

    public void deleteSutdent() {
        System.out.println("Enter student Id to delete");
        String id = scanner.nextLine();
        Student student = findStudentById(id);
        if (student != null) {
            students.remove(student);
            System.out.println("Student deleted successfully!");
        } else {
            System.out.println("No student found with this Id!");
        }
    }

    private Student findStudentById(String id) {
        for (Student student : students) {
            if (student.getStudentId().equals(id)) {
                return student;
            }
        }
        return null;
    }

    public void searchStudent() {
        int choice;
        int count = 0;

        do {
            System.out.println("\n Select the item you want to edit:");
            System.out.println("1. Search Id");
            System.out.println("2. Search name");
            System.out.println("3. Search age");
            System.out.println("4. Search className");
            System.out.println("0. Exit search");
            System.out.println("Choice: ");

            choice = InputUtils.getIntInput(scanner);

            switch (choice) {
                case 1:
                    System.out.println("Enter Id: ");
                    String id = scanner.nextLine();
                    if (findStudentById(id) == null) {
                        System.out.println("No student with that Id was found");
                    } else {
                        System.out.println(findStudentById(id));
                    }
                    break;
                case 2:
                    System.out.println("Enter name: ");
                    String name = scanner.nextLine();
                    for (Student student : students) {
                        if (student.getName().equals(name)) {
                            System.out.println(student);
                            count += 1;
                        }
                    }
                    if (count == 0) {
                        System.out.println("No student with that name was found");
                    }
                    count = 0;
                    break;
                case 3:
                    System.out.println("Enter age: ");
                    int age = Integer.parseInt(scanner.nextLine());
                    for (Student student : students) {
                        if (student.getAge() == age) {
                            System.out.println(student);
                            count += 1;
                        }
                    }
                    if (count == 0) {
                        System.out.println("No student with that age was found");
                    }
                    count = 0;
                    break;
                case 4:
                    System.out.println("Enter className: ");
                    String className = scanner.nextLine();
                    for (Student student : students) {
                        if (student.getClassName().equals(className)) {
                            System.out.println(student);
                            count += 1;
                        }
                    }
                    if (count == 0) {
                        System.out.println("No student with that class name was found");
                    }
                    count = 0;
                    break;
                case 0:
                    System.out.println("Exit successfully");
                    break;
                default:
                    System.out.println("Invaild choice. Please choose again.");
            }
        } while (choice != 0);
    }

    public void displayStudents() {
        if (students.isEmpty()) {
            System.out.println("Empty student list!");
        } else {
            for (Student student : students) {
                System.out.println(student);
            }
        }
    }

    public void saveToFile(String fileName) throws IOException {
        int fileSize = 5;
        int totalThreads = (int) Math.ceil((double) students.size() / fileSize);

        LocalDate currentDate = LocalDate.now();
        String dateFolder = currentDate.toString();

        Path directoryPath = Paths.get(dateFolder);

        if (!students.isEmpty()) {
            try {
                if (!Files.exists(directoryPath)) {
                    Files.createDirectories(directoryPath);
                    System.out.println("Create directory: " + directoryPath);
                }

                for (int i = 0; i < totalThreads; i++) {
                    int start = i * fileSize;
                    int end = Math.min(start + fileSize, students.size());
                    List<Student> studentFile = students.subList(start, end);

                    String file = directoryPath + "/" + "file" +(i + 1) + "_" + fileName;

                    Thread thread = new Thread(new FileWriterTask(file, studentFile));
                    thread.start();
                    System.out.println("Thread run: " + thread);
                }
            } catch (Exception e) {
                System.out.println("An error occurred while saving the files.");
            }
        } else {
            System.out.println("Empty student list!");
        }
    }

    private class FileWriterTask implements Runnable {
        private String fileName;
        private List<Student> studentFile;

        public FileWriterTask(String fileName, List<Student> studentFile) {
            this.fileName = fileName;
            this.studentFile = studentFile;
        }

        @Override
        public void run() {
            File file = new File(fileName);
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
                if (!file.exists()) {
                    file.createNewFile();
                    System.out.println("Create file: " + file);
                }

                for (Student student : studentFile) {
                    String line = student.getStudentId() + ", " + student.getName() + ", " +
                            student.getAge() + ", " + student.getClassName();

                    bw.write(line);
                    bw.newLine();
                }
            } catch (Exception e) {
                System.out.println("An error occurred while writing to file" + fileName);
            }
        }
    }

    public void loadFromFile(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(", ");
                String id = data[0];
                String name = data[1];
                int age = Integer.parseInt(data[2]);
                String className = data[3];
                students.add(new Student(id, name, age, className));
            }
            System.out.println("Load data from file: " + fileName + " successfully!" );
        } catch (IOException e) {
            System.out.println("Load data failed!");
        }
    }
}
