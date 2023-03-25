package machine;

import java.util.Scanner;

import static machine.Main.mainMenu;

public class CoffeeMachine {
    private int water;
    private int milk;
    private int beans;
    private int cups;
    private int money;
    private Scanner scanner = new Scanner(System.in);

    public CoffeeMachine(int water, int milk, int beans, int cups, int money) {
        this.water = water;
        this.milk = milk;
        this.beans = beans;
        this.cups = cups;
        this.money = money;
    }

    public void buyCoffee() {
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
        String choice = scanner.next();

        if (choice.equals("1")) {
            if (water > 250 && beans > 16 && cups > 1) {
                System.out.println("I have enough resources, making you a coffee!");
                water-=250;
                beans-=16;
                money+=4;
                cups-=1;
            } else if (water < 250 || beans < 16 || cups < 1) {
                String missing = water < 250? "water" : beans < 16? "beans" : cups < 1? "cups" : "everything";
                System.out.println("Sorry, not enough " + missing + "!");
            }
        } else if (choice.equals("2")) {
            if (water > 350 && milk > 75 && beans > 20 && cups > 1) {
                System.out.println("I have enough resources, making you a coffee!");
                water = water - 350;
                milk = milk - 75;
                beans = beans - 20;
                money = money + 7;
                cups = cups - 1;
            } else if (water < 250 || beans < 16 || cups < 1) {
                String missing = water < 250? "water" : beans < 16? "beans" : cups < 1? "cups" : "everything";
                System.out.println("Sorry, not enough " + missing + "!");
            }
        } else if (choice.equals("3")) {
            if (water > 200 && milk > 100 && beans > 12 && cups > 1) {
                System.out.println("I have enough resources, making you a coffee!");
                water = water - 200;
                milk = milk - 100;
                beans = beans - 12;
                money = money + 6;
                cups = cups - 1;
            } else if (water < 250 || beans < 16 || cups < 1) {
                String missing = water < 250? "water" : beans < 16? "beans" : cups < 1? "cups" : "everything";
                System.out.println("Sorry, not enough " + missing + "!");
            }
        } else if (choice.equals("back")) {
            mainMenu();
        }

    }

    public void overall() {
        System.out.println("The coffee machine has: ");
        System.out.println(water + " ml of water  ");
        System.out.println(milk + " ml of milk  ");
        System.out.println(beans + " g of coffee beans  ");
        System.out.println(cups + " disposable cups  ");
        System.out.println("$" + money + " of money  ");
    }
    public void fillCoffee() {
        System.out.println("Write how many ml of water you want to add:");
        water = water + scanner.nextInt();
        System.out.println("Write how many ml of milk you want to add:");
        milk = milk + scanner.nextInt();
        System.out.println("Write how many grams of coffee beans you want to add:");
        beans = beans + scanner.nextInt();
        System.out.println("Write how many disposable cups you want to add:");
        cups = cups + scanner.nextInt();
    }

    public void takeMoney(){
        System.out.println("I gave you $" + money);
        money = 0;
    }


}