package TicTacToe;

import java.util.Arrays;

public class Board {
    private static String[][] Board = { {" ", " ", " "},
                                        {" ", " ", " "},
                                        {" ", " ", " "}};

    //prints the board
    public static void printBoard() {
        for (String[] strings : Board) {
            System.out.println(Arrays.toString(strings));
        }
    }
    //takes the inputted coordinates and makes it an X
    public static void makeX(int row, int col){
        Board[row][col]="X";
    }
    //takes the inputted coordinates and makes it an O
    public static void makeO(int row, int col){
        Board[row][col]="O";
    }
    public static void makeEmpty(int row, int col){
        Board[row][col] = " ";
    }
    //checks if a player won
    public static String checkWin(){
        //checks rows
        for (String[] strings : Board) {
            if ((!strings[0].equals(" ")) && (strings[0].equals("X") || strings[0].equals("O")) && (strings[0].equals(strings[1]) && strings[0].equals(strings[2]))) {
                return strings[0];
            }
        }
        //checks columns
        for(int col = 0; col<3; col++){
            if((!Board[0][col].equals(" ")) && (Board[0][col].equals("X") || Board[0][col].equals("O")) && (Board[0][col].equals(Board[1][col]) &&  Board[0][col].equals(Board[2][col]))){
                System.out.println(Board[0][col] + " wins!");
                return Board[0][col];
            }
        }
        //checks  bottom right diagonals
        if(!Board[0][0].equals(" ") && (Board[0][0].equals("X") || (Board[0][0].equals("O"))) && Board[1][1].equals(Board[0][0]) && Board[2][2].equals(Board[0][0])){
            System.out.println(Board[0][0] + " wins!");
            return Board[0][0];
        }
        //checks bottom left diagonals
        if(!Board[0][2].equals(" ") && (Board[0][2].equals("X") || (Board[0][2].equals("O"))) && Board[1][1].equals(Board[0][2]) && Board[2][0].equals(Board[0][2])){
            System.out.println(Board[0][2] + " wins!");
            return Board[0][2];
        }

        return null;
    }
    //checks if the end result is a draw
    public static boolean checkDraw(){
        int ans = 0;
        for (String[] strings : Board) {
            for (int col = 0; col < strings.length; col++) {
                if (!strings[col].equals(" ")) {
                    ans++;
                }
            }
        }
        if(ans==9){
            System.out.println("No winner, draw");
            return true;
        }
        return false;
    }
    //checks if the current spot is already taken by something
    public static boolean checkIfTaken(int row, int col){
        return !Board[row][col].equals(" ");
    }
}


