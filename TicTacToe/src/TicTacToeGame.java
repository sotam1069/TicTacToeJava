import java.sql.Array;
import java.util.*;

public class TicTacToeGame {


    public static final int X = 1, O = -1;
    public static final int EMPTY  = 0;

    private static Integer[][] board = new Integer[3][3];
    private int playerTurn = 0;
    private static String player = "player";
    private static String cpu = "cpu";

    static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
    static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();


    public TicTacToeGame() { clearBoard(); }

    public void clearBoard(){
        ;
        for(int x=0; x < 3; x++){
            for(int y=0; y < 3; y++){
                board[x][y] = EMPTY;
            }
        }
        playerTurn = X;
    }

    public static void putMark(int pos, String user){

        int symbol = EMPTY;

        if(user.equalsIgnoreCase(player)){
            symbol = X;
            playerPositions.add(pos);
        }
        else if (user.equalsIgnoreCase(cpu)){
            symbol = O;
            cpuPositions.add(pos);
        }

        switch(pos){

            case 1:
                board[0][0] = symbol;
                break;
            case 2:
                board[0][1] = symbol;
                break;
            case 3:
                board[0][2] = symbol;
                break;
            case 4:
                board[1][0] = symbol;
                break;
            case 5:
                board[1][1] = symbol;
                break;
            case 6:
                board[1][2] = symbol;
                break;
            case 7:
                board[2][0] = symbol;
                break;
            case 8:
                board[2][1] = symbol;
                break;
            case 9:
                board[2][2] = symbol;
                break;
            default:
                break;
        }
    }

    public static boolean isWin(int mark){


        return ((board[0][0] + board[0][1] + board[0][2] == mark*3)
                || (board[1][0] + board[1][1] + board[1][2] == mark*3)
                || (board[2][0] + board[2][1] + board[2][2] == mark*3)
                || (board[0][0] + board[1][0] + board[2][0] == mark*3)
                || (board[0][1] + board[1][1] + board[2][1] == mark*3)
                || (board[0][2] + board[1][2] + board[2][2] == mark*3)
                || (board[0][0] + board[1][1] + board[2][2] == mark*3)
                || (board[2][0] + board[1][1] + board[0][2] == mark*3));
    }

    public static int winner(){
        if (isWin(X)){
            return(X);
        }
        else if(isWin(O)){
            return(O);
        }
        else{
            return 0;
        }
    }

    public String toString(){

        StringBuilder sb = new StringBuilder();


        for(int x=0; x < 3; x++){
            for(int y=0; y < 3; y++){
                switch(board[x][y]){
                    case X:
                        sb.append("X");
                        break;
                    case O:
                        sb.append("O");
                        break;
                    case EMPTY:
                        sb.append(" ");
                        break;
                }

                if (y < 2) { sb.append("|"); }

            }
            if(x < 2) { sb.append("\n----- \n"); }
        }
        return sb.toString();
    }

    public static void main(String[] args){

        TicTacToeGame game = new TicTacToeGame();
        System.out.println(game);

        while(true){
            Scanner scan = new Scanner(System.in);
            System.out.println("Print your placement on board (1-9): ");
            int playerPos = scan.nextInt();
            while(playerPos <= 0 || playerPos > 9){
                System.out.println("Incorrect position please try again: ");
                playerPos = scan.nextInt();
            }
            while(playerPositions.contains(playerPos) || cpuPositions.contains(playerPos)){
                System.out.println("Position taken, Enter a correct position again");
                playerPos = scan.nextInt();
            }
            putMark(playerPos,player);

            int result = winner();

            Random rand = new Random();
            int cpuPos = rand.nextInt(9) + 1;
            while(playerPositions.contains(cpuPos) || cpuPositions.contains(cpuPos)){
                cpuPos = rand.nextInt(9) + 1;
            }
            putMark(cpuPos,cpu);
            System.out.println(game);

            result = winner();
            String[]outcome={"Cpu wins the game!","It's a tie!","You win the game!"};
            if(result == X || result == O){
                System.out.println(outcome[1 + result]);
                break;
            }

        }
    }

}