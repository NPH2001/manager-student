import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentManager {
    private List<Student> students = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

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
            System.out.println("Enter new student name: ");
            student.setName(scanner.nextLine());
            System.out.println("Enter new student age: ");
            student.setAge(Integer.parseInt(scanner.nextLine()));
            System.out.println("Enter new class name: ");
            student.setClassName(scanner.nextLine());
            System.out.println("Student information updated successfully!");
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
