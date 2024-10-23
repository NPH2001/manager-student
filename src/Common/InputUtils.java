package Common;

import java.util.Scanner;

public class InputUtils {
    public static int getIntInput(Scanner scanner) {
        int input = -1;
        while (true) {
            if (scanner.hasNextInt()) {
                input = scanner.nextInt();
                scanner.nextLine();
                break;
            } else {
                System.out.println("Invalid input! Please enter an integer");
                scanner.nextLine();
            }
        }
        return input;
    }
}
