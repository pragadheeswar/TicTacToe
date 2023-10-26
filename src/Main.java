import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        System.out.print("Enter the no of rows:");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Game g = new Game(n);
        char ans;
        g.show();
        while (true){
            g.put('X');                                    // get input from the g.put()
            g.show();
            ans = g.findWinner();
            if(ans != '#'){                                   // # means no winner
                if(ans == 'n'){
                    System.out.println("Input Full!!!!!!!!");
                    System.out.println("Game Over");
                }
                return;
            }
            g.put('O');
            g.show();
            ans = g.findWinner();
            if(ans != '#'){
                if(ans == 'n'){
                    System.out.println("Input Full!!!!!!!!");
                    System.out.println("Game Over");
                }
                return;
            }


        }
    }
}
