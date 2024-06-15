import java.util.Scanner;

public class TicTacToe {

    private static char[][] board = new char[3][3];
    private static char currentPlayer = 'X';

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String playAgain;

        do {
            initializeBoard();
            boolean gameWon = false;
            boolean draw = false;

            while (!gameWon && !draw) {
                printBoard();
                playerMove(scanner);
                gameWon = checkWin();
                draw = checkDraw();

                if (!gameWon && !draw) {
                    switchPlayer();
                }
            }

            printBoard();

            if (gameWon) {
                System.out.println("Player " + currentPlayer + " wins!");
            } else if (draw) {
                System.out.println("The game is a draw!");
            }

            System.out.print("Would you like to play again? (y/n): ");
            playAgain = scanner.nextLine();
        } while (playAgain.equalsIgnoreCase("y"));

        System.out.println("Thank you for playing this game!");
        scanner.close();
    }

    private static void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }

    private static void printBoard() {
        System.out.println("Current board:");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void playerMove(Scanner scanner) {
        int row, col;
        boolean validMove;

        do {
            System.out.println("Player " + currentPlayer + ", enter your move (row column): ");
            row = scanner.nextInt() - 1;
            col = scanner.nextInt() - 1;
            scanner.nextLine();

            validMove = isValidMove(row, col);

            if (!validMove) {
                System.out.println("This move is not valid. Try again.");
            }
        } while (!validMove);

        board[row][col] = currentPlayer;
    }

    private static boolean isValidMove(int row, int col) {
        return row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == '-';
    }

    private static void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    private static boolean checkWin() {
        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) ||
                    (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer)) {
                return true;
            }
        }
        // Check diagonals
        if ((board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) ||
                (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer)) {
            return true;
        }
        return false;
    }

    private static boolean checkDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }
}
