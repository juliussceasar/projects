package cinema;
public class BuyTicket {
    final Grid cinema;
    private int sum;
    public BuyTicket(Grid cinema) {
        this.cinema = cinema;
    }
    void buyTicket(int row_num, int seat_num, int[][] array) {
        array[row_num][seat_num] = 1;
        int price = cinema.rows * cinema.seats < 60 ? 10 : cinema.rows / 2 >= row_num ? 10 : 8;
        sum += price;
        System.out.println("\nTicket price: $" + price);
    }

     int allTicketsSold() {
        int price = cinema.rows * cinema.seats < 60? cinema.rows * cinema.seats * 10 : (cinema.rows * cinema.seats / 2 * 10) + (cinema.rows * cinema.seats / 2 * 8);
        return price;
    }

     int getSum() {
        return sum;
    }

}

