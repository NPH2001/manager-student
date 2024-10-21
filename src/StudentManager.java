import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentManager {
    private List<Student> students = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public void addStudent() {
        System.out.println("Nhap ma sinh vien");
        String id = scanner.nextLine();
        System.out.println("Nhap ten sinh vien");
        String name = scanner.nextLine();
        System.out.println("Nhap tuoi sinh vien");
        int age = Integer.parseInt(scanner.nextLine());
        System.out.println("Nhap ten lop");
        String className = scanner.nextLine();

        students.add(new Student(id, name, age, className));
        System.out.println("Add student success!");
    }

    public void editStudent() {
        System.out.println("Nhap ma sinh vien can sua");
        String id = scanner.nextLine();
        Student student = findStudentById(id);
        if (student != null) {
            System.out.println("Nhap ten moi");
            student.setName(scanner.nextLine());
            System.out.println("Nhap tuoi moi");
            student.setAge(Integer.parseInt(scanner.nextLine()));
            System.out.println("Nhap ten lop moi");
            student.setClassName(scanner.nextLine());
            System.out.println("Cap nhat thong tin thanh cong");
        } else {
            System.out.println("Khong tim thay sinh vien voi ID nay");
        }
    }

    public void deleteSutdent() {
        System.out.println("Nhap ma sinh vien can xoa");
        String id = scanner.nextLine();
        Student student = findStudentById(id);
        if (student != null) {
            students.remove(student);
            System.out.println("Xoa sinh vien thanh cong");
        } else {
            System.out.println("Khong tim thay sinh vien voi ma nay");
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
            System.out.println("Danh sach sinh vien trong");
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
                System.out.println("Luu du lieu thanh cong!");
            } catch (IOException e) {
                System.out.println("Luu du lieu bi loi!");
            }
        } else {
            System.out.println("Danh sach sinh vien rong");
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
            System.out.println("Nap du lieu tu file " + fileName + " thanh cong!" );
        } catch (IOException e) {
            System.out.println("Nap du lieu loi!");
        }
    }
}
