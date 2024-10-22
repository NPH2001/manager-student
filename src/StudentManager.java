import java.io.*;
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
                choice = Integer.parseInt(scanner.nextLine());
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
            choice = Integer.parseInt(scanner.nextLine());

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

    public void saveToFile(String fileName) {
        if (!students.isEmpty()) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))){
                for (Student student: students) {
                    String line = student.getStudentId() + ", " + student.getName() + ", " +
                            student.getAge() + ", " + student.getClassName();

                    bw.write(line);
                    bw.newLine();
                }
                System.out.println("Data saved successfully!!");
            } catch (IOException e) {
                System.out.println("Data saved failed!");
            }
        } else {
            System.out.println("Empty student list!");
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
