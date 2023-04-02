import java.util.Scanner;

public class Main
{
    private static final int ROW = 3;
    private static final int COL = 3;
    private static final String board [][] = new String[ROW][COL];

    public static void main(String[] args) {
        boolean playAgain = false;
        do {
            int moveCnt = 0;
            int row;
            int col;
            String player = "";
            boolean continueGame = true;

            Scanner in = new Scanner(System.in);
            clearBoard(); // call method to clear the board
            showBoard(); // call method to display the board

            do // loop for game play
            {
                player = "X"; // set player to X
                do {
                    row = SafeInput.getRangedInt(in, "Player 1, enter the row: ", 1, 3);
                    col = SafeInput.getRangedInt(in, "Player 1, enter the column: ", 1, 3);
                    row = row - 1;
                    col = col - 1;
                } while (!isValidMove(row, col));
                board[row][col] = player;
                showBoard(); // call method to display the board
                moveCnt = moveCnt + 1;

                if (moveCnt > 4 && moveCnt < 9) {
                    if (isWin(player)) {
                        continueGame = false;
                        System.out.println("Player " + player + " wins!");
                        break;
                    }
                } else if (moveCnt > 9) {
                    continueGame = false;
                    System.out.println("Game ends in a tie!");
                    break;
                }

                player = "O"; // set player to O

                do {
                    row = SafeInput.getRangedInt(in, "Player 2, enter the row: ", 1, 3);
                    col = SafeInput.getRangedInt(in, "Player 2, enter the column: ", 1, 3);
                    row = row - 1;
                    col = col - 1;
                } while (!isValidMove(row, col));
                board[row][col] = player;
                showBoard(); // call method to display the board
                moveCnt = moveCnt + 1;

                if (moveCnt > 5 && moveCnt < 10) {
                    if (isWin(player)) {
                        continueGame = false;
                        System.out.println("Player " + player + " wins!");
                        break;
                    }
                } else if (moveCnt > 10) {
                    continueGame = false;
                    System.out.println("Game ends in a tie!");
                    break;
                }

            } while (continueGame);

            playAgain = SafeInput.getYNConfirm(in, "Would you like to play again?");

        }while(playAgain);

    }
    // Methods
    private static void clearBoard() // sets all elements to a space
    {
        for (int row = 0; row < ROW; row++)
        {
            for(int col = 0; col < COL; col++)
            {
                board[row][col] = " "; // make this cell a space (empty)
            }
        }
    }

    private static void showBoard() // displays the board
    {
        for (int row = 0; row < ROW; row++)
        {
            for(int col = 0; col < COL; col++)
            {
                System.out.print(board[row][col] + "|"); // display the value stored in each cell
            }
            System.out.println(""); // go to next line
        }
    }

    private static boolean isValidMove(int row, int col) // determines if move is valid (i.e. the space is empty)
    {
        boolean retVal = false;
        if (board[row][col].equals(" ")) // is the space empty?
        {
            retVal = true;
        } return retVal;
    }

    private static boolean isWin(String player) // determines if there is a win
    {
        if (isColWin(player) || isRowWin(player) || isDiagonalWin(player))
        {
            return true;
        }
        return false;
    }

    private static boolean isRowWin(String player) // checks for a row win
    {
        for (int row = 0; row <ROW; row++)
        {
            if (board[row][0].equals(player) && board[row][1].equals(player) && board[row][2].equals(player))
            {
                return true;
            }
        }
        return false; // no row win
    }

    private static boolean isColWin(String player) // checks for a column win
    {
        for (int col = 0; col <COL; col++)
        {
            if (board[0][col].equals(player) && board[1][col].equals(player) && board[2][col].equals(player))
            {
                return true;
            }
        }
        return false; // no col win
    }

    private static boolean isDiagonalWin(String player) // checks for a diagonal win
    {
        if (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player) || (board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player)))
        {
            return true;
        }
        return false; // no diagonal win
    }

    private static boolean isTie(String player) // checks for a tie
    {

        // check row vectors
        for (int row = 0; row <ROW; row++)
        {
            if (board[row][0].equals("X"))
            {
                if(board[row][1].equals("O") || board[row][2].equals("O"))
                {
                   // if either of these, row vector is eliminated
                }
            }
            if (board[row][0].equals("O"))
            {
                if(board[row][1].equals("X") || board[row][2].equals("X"))
                {
                    // if either of these, row vector is eliminated
                }
            }
        }

        // check column vectors
        for (int col = 0; col <COL; col++)
        {
            if (board[0][col].equals("X"))
            {
                if (board[1][col].equals("O") || board[2][col].equals("O"))
                {
                    // if either of these, col vector is eliminated
                }
            }
            if (board[0][col].equals("O"))
            {
                if (board[1][col].equals("X") || board[2][col].equals("X"))
                {
                    // if either of these, col vector is eliminated
                }
            }
        }
        // check L to R diagonal vectors
        if (board[0][0].equals("X")) //left to right diagonal starting with X
        {
            if (board[1][1].equals("O") || board[2][2].equals("O"))
            {
                // if either of these, L to R diag vector is eliminated
            }
        }
        if (board[0][0].equals("O")) // left to right diagonal starting with O
        {
            if (board[1][1].equals("X") || board[2][2].equals("X"))
            {
                // if either of these, L to R diag vector is eliminated
            }
        }
        // check R to L diagonal vectors
        if (board[0][2].equals("X"))
        {
           if (board[1][1].equals("O") || board[2][0].equals("O"))
           {
               // if either of these, R to L diag vector is eliminated
           }
        }
        if (board[0][2].equals("O"))
        {
            if (board[1][1].equals("X") || board[2][0].equals("X"))
            {
                // if either of these, R to L diag vector is eliminated
            }
        }


        return false; // no tie
    }


}
