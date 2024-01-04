import java.util.Arrays;
import java.util.Scanner;

public class TicTacToe {
    static Scanner input;
    static String[] board;
    static String turn;

    public static void main(String[] args) {
        input = new Scanner(System.in);
        board = new String[9];
        turn = "O";
        String winner = null;
        createEmptyBoardWithAddedNum();

        System.out.println("---2-Player Tic Tac Toe---");
        System.out.println("***************************");
        printBoard();
        System.out.println("--'O' will play first--");

        // Main game loop
        while (winner == null) {
            int numInput;

            // Get user input for the next move
            System.out.print(turn + "'s turn, Enter a slot number to place " + turn + " in the grid:");
            if (input.hasNextInt()) {
                numInput = input.nextInt();

                // Validate user input
                if (numInput > 0 && numInput <= 9) {
                    if (board[numInput - 1].equals(String.valueOf(numInput))) {
                        // Update the board with the current player's move
                        board[numInput - 1] = turn;

                        // Switch player turns
                        turn = (turn.equals("O")) ? "X" : "O";

                        // Print the updated board
                        printBoard();

                        // Check for a winner
                        winner = checkWinner();
                    } else {
                        System.out.println("\nSlot already taken! Re-enter slot number");
                    }
                } else {
                    System.out.println("\nINVALID input! Re-enter slot number");
                    input.nextLine(); // Consume invalid input
                }
            } else { //If anything other than numbers are entered
                System.out.println("\nINVALID input! Re-enter slot number");
                input.nextLine(); // Consume invalid input
            }
        }

        // Display the game result
        if (winner.equalsIgnoreCase("draw")) {
            System.out.println("It's a draw! Thanks for playing.");
        } else {
            System.out.println("Congratulations! " + winner + "has have won! Thanks for playing.");
        }

        // Close the Scanner to prevent resource leaks
        input.close();
    }

    // Method to check for a winner or draw
    static String checkWinner() {
        for (int a = 0; a < 8; a++) {
            String line = null;
            switch (a) {
                case 0:
                    line = board[0] + board[1] + board[2];
                    break;
                case 1:
                    line = board[3] + board[4] + board[5];
                    break;
                case 2:
                    line = board[6] + board[7] + board[8];
                    break;
                case 3:
                    line = board[0] + board[3] + board[6];
                    break;
                case 4:
                    line = board[1] + board[4] + board[7];
                    break;
                case 5:
                    line = board[2] + board[5] + board[8];
                    break;
                case 6:
                    line = board[0] + board[4] + board[8];
                    break;
                case 7:
                    line = board[2] + board[4] + board[6];
                    break;
            }

            // Check if either player has won
            if (line.equals("XXX")) { //i.e. X-X-X (Horizontal, Vertical, or Diagonal)
                return "X";
            } else if (line.equals("OOO")) {
                return "O";
            }
        }

        // Check for a draw
        for (int a = 0; a < 9; a++) {
            if (Arrays.asList(board).contains(String.valueOf(a + 1))) {
                break;
            } else if (a == 8) {
                return "draw";
            }
        }

        // Prompt for the next player's turn
        System.out.println(turn + "'s turn; enter a slot number to place " + turn + " in:");
        return null;
    }

    // Method to print the current state of the board
    static void printBoard() {
        System.out.println();
        System.out.println("  " + board[0] + " | " + board[1] + " | " + board[2] + "  ");
        System.out.println(" ---+---+--- ");
        System.out.println("  " + board[3] + " | " + board[4] + " | " + board[5] + "  ");
        System.out.println(" ---+---+--- ");
        System.out.println("  " + board[6] + " | " + board[7] + " | " + board[8] + "  ");
        System.out.println();
    }

    // Method to initialize an empty board
    static void createEmptyBoardWithAddedNum() {
        for (int a = 0; a < 9; a++) {
            board[a] = String.valueOf(a + 1);
        }
    }
}
