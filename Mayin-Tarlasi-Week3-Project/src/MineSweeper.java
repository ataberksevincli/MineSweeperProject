import java.util.Random;
import java.util.Scanner;


// Evaluation form 5. question (The project was designed in the MineSweeper class)
public class MineSweeper {
    int rowNumber;
    int colNumber;
    char[][] userMatris;
    char[][] adminMatris;

    int winCount;


    public MineSweeper(int rowNumber, int colNumber) {
        this.rowNumber = rowNumber;
        this.colNumber = colNumber;
        while (true) {

            // Evaluation form 7. Question (We take the game board values from the user.)

            Scanner input = new Scanner(System.in);
            System.out.print("Enter the number of rows: ");
            this.rowNumber = input.nextInt();
            System.out.print("Enter the number of columns: ");
            this.colNumber = input.nextInt();

            // We look at the suitability of the size of the board according to the given requirements and print the mines at the beginning of the game on the screen so that the admin can test the project.
            if (isValidBoardSize(this.rowNumber, this.colNumber)) {
                System.out.println("\nAdmin Control Panel:\n");
                boardwithMine();
                System.out.println("\nGame is starting!\n");
                break;

                // If the values are invalid, we ask the user to enter the values again.

            } else {
                System.out.println("\nInvalid values! Please try again.\n");
            }
        }
    }


    // The mine-free method by which the user will play the game
    public void boardwithoutMine() {
        this.userMatris = new char[this.colNumber][this.rowNumber];
        for (int i = 0; i < this.colNumber; i++) {
            for (int j = 0; j < this.rowNumber; j++) {
                this.userMatris[i][j] = '-';
            }
        }

        for (int i = 0; i < this.colNumber; i++) {
            for (int j = 0; j < this.rowNumber; j++) {
                System.out.print(this.userMatris[i][j] + " ");
            }
            System.out.println();
        }
    }

    // A method in which the values of mines are set and if there are mines in the same place, it is necessary to put the required number of mines in different places.
    public void boardwithMine() {
        this.adminMatris = new char[this.colNumber][this.rowNumber];
        for (int i = 0; i < this.colNumber; i++) {
            for (int j = 0; j < this.rowNumber; j++) {
                this.adminMatris[i][j] = '-';
            }
        }

        // Evaluation form 8. Question (the appropriate number of mines was placed)
        int totalCells = this.colNumber * this.rowNumber;
        int numberOfMines = totalCells / 4;

        Random random = new Random();
        int minesPlaced = 0;

        while (minesPlaced < numberOfMines) {
            int x = random.nextInt(this.colNumber);
            int y = random.nextInt(this.rowNumber);

            // We provide conditions for not pressing mines in the same place.

            if (this.adminMatris[x][y] != '*') {
                this.adminMatris[x][y] = '*';
                minesPlaced++;
            }
        }

        for (int i = 0; i < this.colNumber; i++) {
            for (int j = 0; j < this.rowNumber; j++) {
                System.out.print(this.adminMatris[i][j] + " ");
            }
            System.out.println();
        }

    }

    // Evaluation form 6. The question (the method by which we started the game)
    public void run() {
        boardwithoutMine();
        while (true) {
            int totalCells = this.colNumber * this.rowNumber;
            int winCount = 0;

            // Evaluation form 9. Question (Takes the user's selected point and the checking phase begins.)

            Scanner input = new Scanner(System.in);
            System.out.print("\nPlease enter a ROW number : ");
            int selectedRow = input.nextInt();

            System.out.print("Please enter a COL number : ");
            int selectedCol = input.nextInt();

            if (isValidMove(selectedRow, selectedCol)) {

                // Evaluation form 13. and 15.question (If the user marks the area with mines, he loses the game and sees the output of where the mines are actually located.)
                if (this.adminMatris[selectedRow][selectedCol] == '*') {
                    System.out.println("\nYou lost! There was a mine in the selected cell.\n");
                    printUpdatedadminMatris();
                    break;

                    // If the user selects a point that is not a mine, the points around the entered point are checked using the checknumberofMines method and the userMatris is updated.
                } else if (this.userMatris[selectedRow][selectedCol] == '-') {
                    this.winCount++;
                    checknumberofMines(selectedRow, selectedCol);
                    printUpdateduserMatris();

                    // Evaluation form 14. and 15. Question If the user marks each zone without stepping on the mine, control is provided and the following output is given
                    if (this.winCount >= (totalCells - (totalCells / 4))) {
                        System.out.println("\nCongratulations! You won the game.");
                        break;
                    }
                    // If the user tries to select the same point more than once, the following output is given
                } else {
                    System.out.println("\nSorry, you can't select the same cell again.");
                }
                // If the user enters an invalid value, the following output is given.
            } else {
                System.out.println("\nPlease enter valid row and column numbers.");
            }
        }
    }

    // Evaluation form 10. question (Is it checked whether the user's selected point is within the limits of the directory, if not, a warning message is given and re-entry is requested)
    public boolean isValidMove(int row, int col) {
        return row >= 0 && row < this.colNumber && col >= 0 && col < this.rowNumber;
    }

    // Evaluation form 7. question (Method in which the array size is determined by the user and the size is questioned whether it is valid)
    public boolean isValidBoardSize(int rows, int cols) {
        return rows >= 2 && cols >= 2;
    }

    // The method by which we check if there are mines nearby.
    public void checknumberofMines(int row, int col) {
        int mineCount = 0;

        // We check the perimeter of the selected point with the loop.
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (i >= 0 && i < this.colNumber && j >= 0 && j < this.rowNumber) {

                    // If any nearby value is '*', the mineCount is increased.
                    if (this.adminMatris[i][j] == '*') {
                        mineCount++;
                    }
                }
            }
        }

        // Evaluation form 12. Question (If the mineCount is greater than 0, the value of the mineCount is printed instead of ROW and COL)
        if (mineCount > 0) {
            this.userMatris[row][col] = (char) (mineCount + '0');

            // Evaluation form 12. Question (If the mineCount is 0, the specified point becomes 0.)
        } else {
            this.userMatris[row][col] = '0';
        }
    }

    // Evaluation form 11. Question (The method that updates the screen that the user will see after each operation.)
    public void printUpdateduserMatris() {
        for (int i = 0; i < this.colNumber; i++) {
            for (int j = 0; j < this.rowNumber; j++) {
                System.out.print(this.userMatris[i][j] + " ");
            }
            System.out.println();
        }
    }

    // The method that the admin who controls the project will see.
    public void printUpdatedadminMatris() {
        for (int i = 0; i < this.colNumber; i++) {
            for (int j = 0; j < this.rowNumber; j++) {
                System.out.print(this.adminMatris[i][j] + " ");
            }
            System.out.println();
        }
    }
}

