import java.util.*;

public class TicTacToeGame{
    static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
    static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();

    public static void main(String[] args){
        // Create gameBoard
        char[][] gameBoard = {{' ', '|',' ','|',' '},
                              {'-', '+','-','+','-'},
                              {' ', '|',' ','|',' '},
                              {'-', '+','-','+','-'},
                              {' ', '|',' ','|',' '}};

        printGameBoard(gameBoard);

        //Input statements and checks for remaining positions

        while (true) {
            Scanner scan = new Scanner(System.in);
            System.out.println("Print your placement (1-9): ");
            int playerPos = scan.nextInt();

            while(playerPositions.contains(playerPos) || cpuPositions.contains(playerPos)){
                System.out.println("Position taken, Enter a correct position again");
                playerPos = scan.nextInt();
            }

            placePiece(gameBoard,playerPos,"player");

            String result = checkWinner();

            Random rand = new Random();
            int cpuPos = rand.nextInt(9) + 1;
            while(playerPositions.contains(cpuPos) || cpuPositions.contains(cpuPos)){
                cpuPos = rand.nextInt(9) + 1;
            }
            placePiece(gameBoard,cpuPos,"cpu");

            printGameBoard(gameBoard);

            result = checkWinner();
            if(result.length() > 0){
                System.out.println(result);
                break;
            }
        }
    }
    //Checks the winner using the position of the player/cpu and compares it to the Lists
    public static String checkWinner(){

        List topRow = Arrays.asList(1,2,3);
        List midRow = Arrays.asList(4,5,6);
        List botRow = Arrays.asList(7,8,9);
        List leftCollumn = Arrays.asList(1,4,7);
        List rightCollumn = Arrays.asList(3,6,9);
        List midCollumn = Arrays.asList(2,5,8);
        List cross1 = Arrays.asList(1,5,9);
        List cross2 = Arrays.asList(7,5,3);


        List<List> winningConditions = new ArrayList<List>();
        winningConditions.add(topRow);
        winningConditions.add(midRow);
        winningConditions.add(botRow);
        winningConditions.add(leftCollumn);
        winningConditions.add(rightCollumn);
        winningConditions.add(midCollumn);
        winningConditions.add(cross1);
        winningConditions.add(cross2);

        //Checks winning conditions

        for(List l : winningConditions){
            if(playerPositions.containsAll(l)){
                return "Congratulations you have won the game!";
            }else if(cpuPositions.containsAll(l)){
                return "CPU Wins! Sorry :(";
            } else if(playerPositions.size() + cpuPositions.size() == 9){
                return "CAT!";
            }
        }

        return "";
    }

    public static void printGameBoard(char[][] gameBoard){

        for(char[] row: gameBoard){
            for(char c: row){
                System.out.print(c);
            }
            System.out.println();
        }
    }
    public static void placePiece(char[][] gameBoard,int pos, String user){

        char symbol = ' ';

        if(user.equalsIgnoreCase("player")){
            symbol = 'X';
            playerPositions.add(pos);
        }else if (user.equalsIgnoreCase("cpu")){
            symbol = '0';
            cpuPositions.add(pos);
        }

        //Adds X or O to the position in the board

        switch(pos){

            case 1:
                gameBoard[0][0] = symbol;
                break;
            case 2:
                gameBoard[0][2] = symbol;
                break;
            case 3:
                gameBoard[0][4] = symbol;
                break;
            case 4:
                gameBoard[2][0] = symbol;
                break;
            case 5:
                gameBoard[2][2] = symbol;
                break;
            case 6:
                gameBoard[2][4] = symbol;
                break;
            case 7:
                gameBoard[4][0] = symbol;
                break;
            case 8:
                gameBoard[4][2] = symbol;
                break;
            case 9:
                gameBoard[4][4] = symbol;
                break;
            default:
                break;
        }
    }
}
