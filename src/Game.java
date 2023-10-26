import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Game {
        private char[][] board;
        private boolean[][]  checkOnce;     // to check the values in the board only enter once not able to overwrite
        // false means the value already entered
        private int input = 0;              // no. of input entered

        private int n;                      // row size
//    private final char[][] board = new char[][]{{
//            'o', '#', 'o'},
//            {'x', '#', 'o'},
//            {'#', 'x', 'o'}
//    };

    Game(int n) {
        this.n=n;
        board = new char[n][n];
        checkOnce = new boolean[n][n];
        for(char[] row : board){
            Arrays.fill(row,'#');
        }
        for(boolean[] row : checkOnce){
            Arrays.fill(row,true);
        }
    }

    public void show() {
        for (char[] row : board) {
            for (char a : row) {
                System.out.printf(a + " ");

            }
            System.out.println();
        }
        System.out.println();
    }

    public void put(char a) {
        try{
            System.out.print(a +" turn enter the coordinates:");    // get the coordinates
            Scanner sc = new Scanner(System.in);
            int x=sc.nextInt();
            int y=sc.nextInt();
            System.out.println();
            if(checkOnce[x][y]){                                    // check the value already entered or not
                board[x][y] = a;
                checkOnce[x][y]= false;
                input++;
            }
            else{
                System.out.println("Error:Cannot replace value");
                System.out.println();
                put(a);//recursion
            }
        }
        catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Error Input is out of the range");
            System.out.println();
            put(a);
        }
        catch (InputMismatchException e){
            System.out.println("Error: Enter two numbers");
            System.out.println();
            put(a);
        }


    }

    private char check() {
        //row check
        for (int i = 0; i < n; i++) {                       // for each row
            boolean flag1 = true;
            if (board[i][0] == '#') {
                continue;
            }
            for (int j = 1; j < n; j++) {                   // to check all elements in row
                if (board[i][j - 1] != board[i][j]) {
                    flag1 = false;
                    break;
                }
            }
            if (flag1) {
                return board[i][0];
            }
        }


        //column check
        for (int i = 0; i < n; i++) {                       // for each column
            boolean flag2 = true;
            if (board[0][i] == '#') {
                continue;
            }
            for (int j = 1; j < n; j++) {                   // to check all elements in column
                if (board[j - 1][i] != board[j][i]) {
                    flag2 = false;
                    break;
                }
            }
            if (flag2) {

                return board[0][i];
            }
        }



        // diagonal left to right
        boolean flag3 = true;
        for (int i = 1; i < n; i++) {
            if (board[i - 1][i - 1] != board[i][i]) {
                flag3 = false;
                break;
            }
        }
        if (flag3) {
            return board[0][0];
        }

        // diagonal right to left
        char temp = board[0][n - 1];
        for (int i = 1; i < n; i++) {
            if (temp == board[i][n - i - 1]) {
                temp = board[i][n - i - 1];
            } else {
                return '#';                         // if all the above case fails no winner
            }
        }
        return temp;
    }


    public char findWinner(){                       // run the check() and print the winner or return no winner

        char ans = check();
        if(ans == 'X'){
            System.out.println("!!!!!!!!!!!");
            System.out.println("X is the winner");
            return ans;
        }
        else if(ans=='O'){
            System.out.println("!!!!!!!!!!!!");
            System.out.println("O is the winner");
            return ans;
        }
        if(input== n*n){
            return 'n';
        }
        return ans;
    }
}

