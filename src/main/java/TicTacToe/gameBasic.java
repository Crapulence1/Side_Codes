package TicTacToe;

import java.util.Scanner;

public class gameBasic {
    public static void main(String[] args) {
        Board Board = new Board();
        Scanner in = new Scanner(System.in);
        Board.printBoard();
        int turns = 0;
        char playerTurn = 'X';
        System.out.println("X's turn");
        while(in.hasNextLine() && turns<9) {
            try{
                String[] parts = in.nextLine().split("\\s+", 2);
                int col = Integer.parseInt(parts[0]);
                int row = Integer.parseInt(parts[1]);
                if(!Board.checkIfTaken(row-1, col-1)) {
                    if (turns % 2 == 0) {
                        Board.makeX(row - 1, col - 1);
                        Board.printBoard();
                        if (Board.checkWin()!= null) {
                            if(Board.checkWin().equals("X")){
                                System.out.println("X wins!");
                                break;
                            }
                            if (Board.checkWin().equals("O")){
                                System.out.println("O wins!");
                                break;
                            }
                        }
                        System.out.println("O's turn");
                        playerTurn = 'O';
                        if (Board.checkDraw()) break;
                    }
                    if (turns % 2 == 1) {
                        Board.makeO(row - 1, col - 1);
                        Board.printBoard();
                        if (Board.checkWin()!= null) break;
                        System.out.println("X's turn");
                        playerTurn = 'X';
                        if (Board.checkDraw()) break;
                    }
                    turns++;
                }
                else {
                    System.out.println("Spot is already taken, please choose an empty spot");
                    Board.printBoard();
                    System.out.println( playerTurn + "'s turn");
                }
            } catch (IndexOutOfBoundsException e){
                System.out.println("Please input a proper coordinate");
                if(playerTurn=='X'){
                    Board.printBoard();
                    System.out.println("X's turn");
                }
                if(playerTurn=='O'){
                    Board.printBoard();
                    System.out.println("O's turn");
                }
            }
        }
    }
}
