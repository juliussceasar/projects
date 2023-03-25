package cinema;

public class Statistics {
     static void getStats(int[][] array, BuyTicket buyTicket) {
        System.out.printf(
                """

                        Number of purchased tickets:%d\s
                        Percentage: %.2f%%
                        Current income: $%d
                        Total income: $%d""", calculateNumber(array),
                calculatePercentage(array),
                calculateCurrIncome(buyTicket),
                calculateTotalIncome(buyTicket));
    }

    static int calculateNumber(int[][] array) {
        int counter = 0;
        for (int i = 0; i <= array.length - 1; i++) {
            for (int j = 0; j <= array.length - 1; j++) {
                if (array[i][j] == 1) {
                    counter++;
                }
            }
        }
        return counter;
    }


     static float calculatePercentage(int[][] array) {
        int counter = 0;
        int overall = 0;
        for (int i = 1; i <= array.length - 1; i++) {
            for (int j = 1; j <= array.length - 1; j++) {
                if (array[i][j] == 1) {
                    counter++;
                }
                overall++;
            }
        }
        return (float) (counter * 100.00 / overall);
    }

     static int calculateCurrIncome(BuyTicket buyTicket) {
        return buyTicket.getSum();
    }

     static int calculateTotalIncome(BuyTicket buyTicket) {
        return buyTicket.allTicketsSold();
    }
}
