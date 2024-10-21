import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StudentManager manager = new StudentManager();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Quan ly sinh vien ---");
            System.out.println("1. Them sinh vien");
            System.out.println("2. Sua sinh vien");
            System.out.println("3. Xoa sinh vien");
            System.out.println("4. Hien thi danh sach sinh vien");
            System.out.println("5. Nap du lieu tu file");
            System.out.println("6. Xuat du lieu tu file");
            System.out.println("0. Thoat");
            System.out.println("Chon chuc nang");
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
                    System.out.println("Nhap ten file de nap du lieu");
                    String loadFile = scanner.nextLine();
                    manager.loadFromFile(loadFile);
                    break;
                case 6:
                    System.out.println("Nhap ten file de luu du lieu");
                    String saveFile = scanner.nextLine();
                    manager.saveToFile(saveFile);
                    break;
                case 0:
                    System.out.println("Thoat chuong trinh.");
                    break;
                default:
                    System.out.println("Lua chon khong hop le.");
            }
        } while (choice != 0);
        scanner.close();
    }
}