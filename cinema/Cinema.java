package cinema;

//package cinema;

import java.util.Scanner;

class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int rows = createRows(scanner);
        int seats = createSeats(scanner);

        Grid grid = new Grid(rows, seats);
        grid.createGrid();
        BuyTicket buyTicket = new BuyTicket(grid);



        int count = 0;
        int[][] array = new int[rows + 1][seats + 1];
        while (true) {
            int result = Menu.createMenuNumber(scanner);
            if (result == 1) {
                if (count > 0) {
                    grid.createGrid2(array);
                } else {
                    grid.createGrid();
                }
            } else if (result == 2) {
                count++;

                int row_num;
                int seat_num;
                while (true) {
                    System.out.println("\nEnter a row number:");
                    row_num = scanner.nextInt();
                    System.out.println("\nEnter a seat number in that row:");
                    seat_num = scanner.nextInt();

                    if (row_num > rows || seat_num > seats) {
                        System.out.println("\nWrong input!");
                    } else {
                        if (array[row_num][seat_num] == 1) {
                            System.out.println("That ticket has already been purchased!");
                        } else {
                            buyTicket.buyTicket(row_num, seat_num, array);
                            break;
                        }
                    }
                }

            } else if (result == 3) {
                Statistics.getStats(array, buyTicket);
            } else if (result == 0) {
                return;
            }
        }


    }

    public static int createRows(Scanner scanner) {
        System.out.println("\nEnter the number of rows:");
        return scanner.nextInt();
    }

    public static int createSeats(Scanner scanner) {
        System.out.println("Enter the number of seats in each row:");
        return scanner.nextInt();
    }

}