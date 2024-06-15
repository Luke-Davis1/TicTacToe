import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        char[][] board = {
                {' ', ' ', ' '},
                {' ', ' ', ' '},
                {' ', ' ', ' '}
                };
        String winner = null;
        char playerMark = 'X';
        Scanner in = new Scanner(System.in);

        System.out.println("====== LET'S PLAY TICTACTOE ======");
        printBoard(board);
        System.out.println("X will start");
        int playerRowChoice;
        int playerColChoice;

        while(winner == null) {
            // Ask for valid input
            System.out.println("Player " + playerMark);

            System.out.print("Row: ");
            try {
                playerRowChoice = in.nextInt();
                if(playerRowChoice < 1 || playerRowChoice > 3) {
                    System.out.println("Invalid row number. Try again.");
                    continue;
                }
            } catch(InputMismatchException e) {
                System.out.println("Must enter a valid number. Try again.");
                continue;
            }

            System.out.print("Col: ");
            try {
                playerColChoice = in.nextInt();
                if(playerColChoice < 1 || playerColChoice > 3) {
                    System.out.println("Invalid col number. Try again.");
                    continue;
                }
            } catch(InputMismatchException e) {
                System.out.println("Must enter a valid number. Try again.");
                continue;
            }

            // Verify spot is not already marked on board
            if(board[playerRowChoice-1][playerColChoice-1] == 'X' || board[playerRowChoice-1][playerColChoice-1] == 'O') {
                // Invalid choice
                System.out.println("Invalid choice. Spot already marked, try again.");
                continue;
            }

            // Mark spot on board
            board[playerRowChoice-1][playerColChoice-1] = playerMark;

            // Print board
            printBoard(board);

            // Decide winner
            winner = decideWinner(board);

            // Switch players
            if(playerMark == 'X') {
                playerMark = 'O';
            } else {
                playerMark = 'X';
            }
        }

        if(!winner.equals("draw")) {
            System.out.println("Congrats player " + winner + ", you win!");
        } else {
            System.out.println("It's a draw! Play again!");
        }
        in.close();
    }
    public static void printBoard(char[][] board) {
        // Always print the top
        System.out.println("    1   2   3  ");
        System.out.println("  +---+---+---+");
        for(int row = 0; row < 3; row++) {
            System.out.print(row +1);
            // Print row name followed by data from board
            for(int col = 0; col < 3; col++) {
                System.out.print(" | " + board[row][col]);
            }
            System.out.println(" |");
            System.out.println("  +---+---+---+");
        }
    }

    public static String decideWinner(char[][] board) {
        String line = null;
        for(int wayToWin = 0; wayToWin < 8; wayToWin++) {
            switch(wayToWin) {
                case 0:
                    line = new StringBuilder().append(board[0][0]).append(board[0][1]).append(board[0][2]).toString();
                    break;
                case 1:
                    line = new StringBuilder().append(board[1][0]).append(board[1][1]).append(board[1][2]).toString();
                    break;
                case 2:
                    line = new StringBuilder().append(board[2][0]).append(board[2][1]).append(board[2][2]).toString();
                    break;
                case 3:
                    line = new StringBuilder().append(board[0][0]).append(board[1][0]).append(board[2][0]).toString();
                    break;
                case 4:
                    line = new StringBuilder().append(board[0][1]).append(board[1][1]).append(board[2][1]).toString();
                    break;
                case 5:
                    line = new StringBuilder().append(board[0][2]).append(board[1][2]).append(board[2][2]).toString();
                    break;
                case 6:
                    line = new StringBuilder().append(board[0][0]).append(board[1][1]).append(board[2][2]).toString();
                    break;
                case 7:
                    line = new StringBuilder().append(board[0][2]).append(board[1][1]).append(board[2][0]).toString();
                    break;
            }

            // Determine winner
            if(line.equals("XXX")) {
                // X won
                return "X";
            } else if(line.equals("OOO")) {
                // O won
                return "O";
            }
        }
        int currentRow = 0;
        int currentCol = 0;
        while(currentRow < 3) {
            if(board[currentRow][currentCol] != 'X' && board[currentRow][currentCol] != 'O') {
                // free space available
                break;
            }
            if(board[currentRow][currentCol+1] != 'X' && board[currentRow][currentCol+1] != 'O') {
                // free space available
                break;
            }
            if(board[currentRow][currentCol+2] != 'X' && board[currentRow][currentCol+2] != 'O') {
                // free space available
                break;
            }
            // Increment row
            currentRow++;
        }

        if(currentRow == 3) {
            // It's a draw
            return "draw";
        } else {
            return null;
        }
    }
}