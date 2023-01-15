package cinema;

public class Grid {
    protected int rows;
    protected int seats;


    public Grid(int rows, int seats) {
        this.rows = rows;
        this.seats = seats;
    }
    void createGrid() {
        System.out.println("\nCinema:");
        for (int i = 0; i < rows + 1; i++) {
            for (int j = 0; j < seats + 1; j++) {
                if (i == 0) {
                    if (j == 0) {
                        System.out.print("  ");
                    } else {
                        System.out.print(j + " ");
                    }
                } else if (j == 0) {
                    System.out.print("\n" + i);
                } else {
                    System.out.print(" S");
                }
            }
        }
    }
    void createGrid2(int[][] array) {
        System.out.println("\nCinema:");
        for (int i = 0; i < rows + 1; i++) {
            for (int j = 0; j < seats + 1; j++) {
                if (i == 0) {
                    if (j == 0) {
                        System.out.print("  ");
                    } else {
                        System.out.print(j + " ");
                    }
                } else if (j == 0) {
                    System.out.print("\n" + i);
                } else if (array[i][j] == 1) {
                    System.out.print(" B");
                } else {
                    System.out.print(" S");
                }
            }
        }
    }
}
