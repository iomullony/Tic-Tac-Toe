import java.util.Scanner;

public class TicTacToe {
    
    private String[][] grid = new String[51][51];
    private int size = 0;

    public void getBoardSize() {
        Scanner sc = new Scanner(System.in); // System.in is a standard input stream
        System.out.print("Enter board size: ");  
        String str= sc.nextLine(); // Reads string

        // make sure the input is an integer
        try {
            size = Integer.parseInt(str);
            // make sure the board size is in our limits
            if (size < 2 || size > 50) {
                System.out.println("The size has to be between 2 and 50. Try again.");
                getBoardSize();
            }
            printGrid();
        } catch (Exception e) {
            System.out.println("Invalid input. Try again.");
            getBoardSize();
        }
    }

    public void printGrid() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (grid[i][j] != "X" && grid[i][j] != "O") {
                    grid[i][j] = "-";
                }
                System.out.print(grid[i][j] + " ");
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }

    public void getCoordinates(int player) {
        int x = 0;
        int y = 0;
        String[] coordinates;

        Scanner sc = new Scanner(System.in);
        System.out.print("Player " + player + " - Place mark: ");
        String str = sc.nextLine();
        coordinates = str.split(",");
        
        try {
            x = Integer.parseInt(coordinates[0]);
            y = Integer.parseInt(coordinates[1]);

            if ((x > size) || (x < 0) || (y > size) || (y < 0)) {
                System.out.println("Invalid input. Try again.");
                getCoordinates(player);
            }
    
            if (grid[x-1][y-1] == "-") {
                if (player == 1) grid[x-1][y-1] = "X";
                else if (player == 2) grid[x-1][y-1] = "O";
                printGrid();
            } else {
                System.out.println("Invalid input. Try again.");
                getCoordinates(player);
            }
        } catch (Exception e) {
            System.out.println("Invalid input. Try again.");
            getCoordinates(player);
        }
    }

    public int rowCovered(int player) {
        int result = 0;
        int count1 = 0;
        int count2 = 0;
        int countd = 0;

        // check rows
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                // checks if player 1 has a row
                if ((grid[i][j] == grid[i][j+1]) && (grid[i][j] == "X")) count1++;
                else count1=0;
                
                // checks if player 2 has a row
                if ((grid[i][j] == grid[i][j+1]) && (grid[i][j] == "O")) count2++;
                else count2=0;

                if (count1 == size - 1)  return 1;
                else if (count2 == size - 1) return 2;
            }        
        }

        // check columns
        for (int j = 0; j < size; j++) {
            for (int i = 0; i < size; i++) {
                // checks if player 1 has a columnn
                if ((grid[i][j] == grid[i+1][j]) && (grid[i][j] == "X")) count1++;
                else count1=0;
                
                // checks if player 2 has a column
                if ((grid[i][j] == grid[i+1][j]) && (grid[i][j] == "O")) count2++;
                else count2=0;

                if (count1 == size - 1) return 1;
                else if (count2 == size - 1) return 2;
            }        
        }
        
        // checks diagonals to the right
        for (int i = 0; i < size; i++) {
            // checks if player 1 has a diagonal to the right
            if ((grid[i][i] == grid[i+1][i+1]) && (grid[i][i] == "X")) count1++; 
            else count1 = 0;

            // checks if player 2 has a diagonal to the right
            if ((grid[i][i] == grid[i+1][i+1]) && (grid[i][i] == "O")) count2++; 
            else count2 = 0;

            if (count1 == size - 1) return 1;
            else if (count2 == size - 1) return 2;
        }   

        // checks diagonals to the left
        for (int i = 0; i < size - 1; i++) {
            // checks if player 1 has a diagonal to the left
            if ((grid[i][size-i-1] == grid[i+1][size-i-2]) && (grid[i][size-i-1] == "X")) count1++; 
            else count1=0;

            // checks if player 2 has a diagonal to the left
            if ((grid[i][size-i-1] == grid[i+1][size-i-2]) && (grid[i][size-i-1] == "O")) count2++;
            else count2=0;

            if (count1 == size - 1) return 1;
            else if (count2 == size - 1) return 2; 
        }  

        //checks for a draw
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if ((grid[i][j]!= "-")) countd++;
                else countd=0;
                
                if (countd==size*size) return 3;
            }        
        }

        return result; // when there's nothing
    }

    public void game() {
        int player = 1;
        boolean play = true;
        
        System.out.println("WELCOME!\n"); 
        getBoardSize();

        while (play) {
            getCoordinates(player);

            if (rowCovered(player) == 1) {
                System.out.println("Player 1 wins!\n");
                play = false;
            } else if (rowCovered(player) == 2) {
                System.out.println("Player 2 wins!\n");
                play = false;
            } else if (rowCovered(player) == 3) {
                System.out.println("It's a draw!\n");
                play = false;
            }

            // to change turns
            if (player == 1) player++;
            else player--;
        }
    }

    public static void main(String[] args) throws Exception {
        TicTacToe ttt = new TicTacToe();
        ttt.game();
    }
}