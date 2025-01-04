package TicTacToe;
import java.util.Scanner;
public class gameRobot {
    static Board Board = new Board();
    static char playerTurn = 'X';
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Board.printBoard();
        int turns = 0;
        System.out.println("X's turn");
        while (in.hasNextLine()) {
            try {
                String[] parts = in.nextLine().split("\\s+", 2);
                int col = Integer.parseInt(parts[0]);
                int row = Integer.parseInt(parts[1]);
                if (!Board.checkIfTaken(row - 1, col - 1)) {
                    if (playerTurn == 'X') {
                        String winner = Board.checkWin();
                        playerMove(row, col);
                        if (winner != null) {
                            if (winner.equals("X")) {
                                System.out.println(winner + " wins!");
                                break;
                            }
                            if (winner.equals("O")) {
                                System.out.println(winner + " wins!");
                                break;
                            }
                        }
                        if (Board.checkDraw()){
                            System.out.println("No winner, draw");
                            break;
                        }
                        System.out.println(playerTurn + "'s turn");
                    }
                    if (playerTurn == 'O') {
                        String winner = Board.checkWin();
                        robotMove(row, col);
                        if (winner != null) {
                            if (winner.equals("X")) {
                                System.out.println("X wins!");
                                break;
                            }
                            if (winner.equals("O")) {
                                System.out.println("O wins!");
                                break;
                            }
                        }
                        if (Board.checkDraw()) {
                            System.out.println("No winner, draw");
                            break;
                        }
                        System.out.println("X's turn");
                    }
                } else {
                    System.out.println("Spot is already taken, please choose an empty spot");
                    Board.printBoard();
                    System.out.println(playerTurn + "'s turn");
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Please input a proper coordinate");
                if (playerTurn == 'X') {
                    Board.printBoard();
                    System.out.println(playerTurn + "'s turn");
                }
                if (playerTurn == 'O') {
                    Board.printBoard();
                    System.out.println(playerTurn + "'s turn");
                }

            }
        }
    }
    public static void playerMove(int row, int col){
        Board.makeX(row - 1, col - 1);
        Board.printBoard();
        playerTurn = 'O';
    }
    public static void robotMove(int row, int col){
        double bestScore = Double.NEGATIVE_INFINITY;
        int[] move = new int[2];
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(!Board.checkIfTaken(i , j)){
                    Board.makeO(i, j);
                    double score = minimax(0, false);
                    Board.makeEmpty(i, j);
                    if(score > bestScore){
                        bestScore = score;
                        move = new int[]{i, j};
                    }
                }
            }
        }
        Board.makeO(move[0], move[1]);
        Board.printBoard();
        playerTurn = 'X';
    }
    public static double minimax(int depth, boolean isMaximizing){
        String winner = Board.checkWin();
        if(winner != null){
            return winner.equals("O") ? 1 : -1;  // "O" wins => 1, "X" wins => -1
        }
        if(Board.checkDraw()){
            return 0;  // Draw => 0
        }
        double bestScore;
        if(isMaximizing){
            bestScore = Double.NEGATIVE_INFINITY;
            for(int row = 0; row < 3; row++){
                for(int col = 0; col < 3; col++){
                    if(!Board.checkIfTaken(row, col)){
                        Board.makeO(row, col);
                        double score = minimax(depth + 1, false);
                        Board.makeEmpty(row, col);
                        bestScore = Math.max(score, bestScore);
                    }
                }
            }
        } else {
            bestScore = Double.POSITIVE_INFINITY;
            for(int row = 0; row < 3; row++){
                for(int col = 0; col < 3; col++){
                    if(!Board.checkIfTaken(row, col)){
                        Board.makeX(row, col);
                        double score = minimax(depth + 1, true);
                        Board.makeEmpty(row, col);
                        bestScore = Math.min(score, bestScore);
                    }
                }
            }
        }
        return bestScore;
    }

}
