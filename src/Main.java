import java.util.Scanner;

public class Main
{
    private static final int ROW = 3;
    private static final int COL = 3;
    private static final String board [][] = new String[ROW][COL];

    public static void main(String[] args) {
        boolean playAgain = false;
        do // this loop starts a new game
        {
            int moveCnt = 0;
            int row;
            int col;
            String player = "";
            boolean continueGame = true;

            Scanner in = new Scanner(System.in);
            clearBoard(); // call method to clear the board for new game
            showBoard(); // call method to display the board

            do // loop for game play
            {
                player = "X"; // set player to X
                do {
                    System.out.println();
                    row = SafeInput.getRangedInt(in, "Player X, enter the row: ", 1, 3); // get input using Safe Input method. Asks for a row 1-3
                    col = SafeInput.getRangedInt(in, "Player X, enter the column: ", 1, 3); // get input using Safe Input method. Asks for a column 1-3
                    row = row - 1; // subtract 1 from user input to get index location
                    col = col - 1; // subtract 1 from user input to get index location
                    if (!isValidMove(row, col))
                    {
                        System.out.println("That space is already taken. Please select an empty cell"); // output to tell user that they've selected a cell that is already filled
                    }
                } while (!isValidMove(row, col)); // input step will loop until player chooses an empty cell

                board[row][col] = player; // mark the chosen location with X
                showBoard(); // call method to display the board
                moveCnt = moveCnt + 1; // increment the move counter

                if (moveCnt > 4) // check for a win after move 5
                {
                    if (isWin(player)) // call method to check for a winner
                    {
                        System.out.println("Player " + player + " wins!"); // output to state the winner
                        continueGame = false; // since there is a winner, the game will not continue
                        break;
                    }
                }
                if (moveCnt > 7) // check for a tie after 7 moves
                {
                    if (isTie(player)) // call method to check for a tie
                    {
                        System.out.println("Game ends in a tie!"); // output to state that game has ended with a tie
                        continueGame = false; // since the game ended in a tie, the game will not continue
                        break;
                    }
                }

                player = "O"; // set player to O

                do {
                    System.out.println();
                    row = SafeInput.getRangedInt(in, "Player O, enter the row: ", 1, 3); // get input using Safe Input method. Asks for a row 1-3
                    col = SafeInput.getRangedInt(in, "Player O, enter the column: ", 1, 3); // get input using Safe Input method. Asks for a column 1-3
                    row = row - 1; // subtract 1 from user input to get index location
                    col = col - 1; // subtract 1 from user input to get index location
                    if (!isValidMove(row, col))
                    {
                        System.out.println("That space is already taken. Please select an empty cell"); // output to tell user that they've selected a cell that is already filled
                    }
                } while (!isValidMove(row, col)); // input step will loop until player chooses an empty cell
                board[row][col] = player; // mark the chosen location with O
                showBoard(); // call method to display the board
                moveCnt = moveCnt + 1; // increment the move counter

                if (moveCnt > 5) // check for a win after move 5
                {
                    if (isWin(player)) // call method to check for a winner
                    {
                        System.out.println("Player " + player + " wins!"); // output to state the winner
                        continueGame = false; // since there is a winner, the game will not continue
                    }
                }
                if (moveCnt > 7) // check for a tie after 7 moves
                {
                    if (isTie(player))
                    {
                        System.out.println("Game ends in a tie!"); // output to state that game has ended with a tie
                        continueGame = false; // since there is a tie, the game will not continue
                    }
                }
            } while (continueGame); // if there is not a winner or a tie, the game continues

            playAgain = SafeInput.getYNConfirm(in, "Would you like to play again?"); // call SafeInput method to prompt user to play again

        }while(playAgain); // game will restart if user says they'd like to play again

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
                if (col == COL - 1)
                {
                    System.out.print(board[row][col]); // prints the value stored in each cell
                }
                else
                {
                    System.out.print(board[row][col] + "|"); // prints value in each cell and puts | after the first and second column
                }

            }
            System.out.println(""); // go to next line
        }
    }

    private static boolean isValidMove(int row, int col) // determines if move is valid (i.e. the space is empty)
    {
        boolean retVal = false;
        if (board[row][col].equals(" ")) // is the space empty?
        {
            retVal = true; // the space is empty and move is valid
        } return retVal;
    }

    private static boolean isWin(String player) // determines if there is a win
    {
        if (isColWin(player) || isRowWin(player) || isDiagonalWin(player))
        {
            return true; // there is a win
        }
        return false; // no win
    }

    private static boolean isRowWin(String player) // checks for a row win
    {
        for (int row = 0; row <ROW; row++)
        {
            if (board[row][0].equals(player) && board[row][1].equals(player) && board[row][2].equals(player)) // one row contains either X or O in each column to form a row
            {
                return true; // there is a row win
            }
        }
        return false; // no row win
    }

    private static boolean isColWin(String player) // checks for a column win
    {
        for (int col = 0; col <COL; col++)
        {
            if (board[0][col].equals(player) && board[1][col].equals(player) && board[2][col].equals(player)) // one column contains either X or O in each row to form a column
            {
                return true; // there is a column win
            }
        }
        return false; // no col win
    }

    private static boolean isDiagonalWin(String player) // checks for a diagonal win
    {
        if (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player) || (board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player))) // checks to see if the same value appears across either diagonal
        {
            return true; // there is a diagonal win
        }
        return false; // no diagonal win
    }

    private static boolean isTie(String player) // checks for a tie
    {
        // check row vectors
        int rowCount = 0;
        boolean rowTie = false;
        for (int row = 0; row <ROW; row++) // checks each row
        {
            if (board[row][0].equals("X") || board[row][1].equals("X") || board[row][2].equals("X")) // determines if there is an X in each row
            {
                if(board[row][0].equals("O") || board[row][1].equals("O") || board[row][2].equals("O")) // determines if there is also an O in that row
                {
                    rowCount = rowCount + 1; // if there is an X and O in that row, the rowCount is incremented by 1
                }
            }
            if (rowCount == 3) // if all 3 rows contain an X and O, the row vectors are eliminated
            {
                rowTie = true; // rowTie = there cannot be a row win
            }
        }

        // check column vectors
        int colCount = 0;
        boolean colTie = false;
        for (int col = 0; col <COL; col++) // checks each column
        {
            if (board[0][col].equals("X") || board[1][col].equals("X") || board[2][col].equals("X")) // determines if there is an X in each col
            {
                if (board[0][col].equals("O") || board[1][col].equals("O") || board[2][col].equals("O")) // determines if there is an O in each col
                {
                    colCount = colCount + 1; // if col contains both an X and a O, colCount is incremented by 1
                }
            }
            if (colCount == 3) // if all 3 columns contain an X and O, the column vectors are eliminated
            {
                colTie = true; // colTie = there cannot be a column win
            }

        }

        // check L to R diagonal vectors
        boolean diagLRTie = false;
        if (board[0][0].equals("X") || board[1][1].equals("X") || board[2][2].equals("X")) // checks to see if the left to right diagonal contains an X
        {
            if (board[0][0].equals("O") || board[1][1].equals("O") || board[2][2].equals("O")) // checks to see if the left to right diagonal also contains an O
            {
                diagLRTie = true; // if the left to right diagonal contains an X and an O, this vector is eliminated
            }
        }

        // check R to L diagonal vectors
        boolean diagRTTie = false;
        if (board[0][2].equals("X") || board[1][1].equals("X") || board[2][0].equals("X")) // checks to see if the right to left diagonal contains an X
        {
           if (board[0][2].equals("O") || board[1][1].equals("O") || board[2][0].equals("O")) // checks to see if the right to left diagonal also contains an O
           {
               diagRTTie = true; // if the right to left diagonal contains an X and an O, this vector is eliminated
           }
        }

        //check to see if all vectors have been eliminated
        if (rowTie && colTie && diagLRTie && diagRTTie)
        {
            return true; // if all vectors have been eliminated, we have a tie!
        }

        return false; // no tie because at least one vector has not been eliminated


    }


}
