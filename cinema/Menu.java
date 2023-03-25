package cinema;

import java.util.Scanner;

public class Menu {
    static int createMenuNumber(Scanner scanner) {
        System.out.println("""


                1. Show the seats
                2. Buy a ticket
                3. Statistics
                0. Exit""");
        //Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        return switch (number) {
            case 1 -> 1;
            case 2 -> 2;
            case 3 -> 3;
            default -> 0;
        };
    }
}
