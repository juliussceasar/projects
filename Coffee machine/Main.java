package machine;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        mainMenu();
    }

    public static void mainMenu() {
        CoffeeMachine machine = new CoffeeMachine(400, 540, 120, 9, 550);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Write action (buy, fill, take, remaining, exit):");
            String input = scanner.next();
            if (input.equals("buy")) {
                machine.buyCoffee();
            } else if (input.equals("fill")) {
                machine.fillCoffee();
            } else if (input.equals("take")) {
                machine.takeMoney();
            } else if (input.equals("remaining")) {
                machine.overall();
            } else if (input.equals("exit")) {
                System.exit(0);
            }
        }
    }
}
