import java.util.*;

public class Main {

    static ArrayList<Integer> playerPositions = new ArrayList<>();
    static ArrayList<Integer> cpuPositions = new ArrayList<>();

    public static void main(String[] args) {

        System.out.println("Welcome To Tic Tac Toe");
        // the GameBoard Layout
        char[][] gameBoard = {
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
        };

        // game Loop
        while (true) {
            Scanner scan = new Scanner(System.in);
            System.out.println("Enter you placement (1-9)");
            int playerPos = scan.nextInt();

            // check if the position is open or not. Ask player to place again on a open position
            while (playerPositions.contains(playerPos) || cpuPositions.contains(playerPos)) {
                System.out.println("Position is taken! Enter a new position");
                playerPos = scan.nextInt();
            }
            placePiece(gameBoard, playerPos, "player");

            // Check if the player have the conditions to win.
            String result = checkWinner(gameBoard);

            // it this conditions is true, the player are the winner,
            // so we need to end the while loop
            if (result.length() > 0) {
                System.out.println(result);
                break;
            }

            // CPU's Turn to place
            Random rand = new Random();
            int cpuPos = rand.nextInt(9) + 1;

            // check the position for the CPU, continue to get random number if position is taken.
            while (playerPositions.contains(cpuPos) || cpuPositions.contains(cpuPos)) {
                cpuPos = rand.nextInt(9) + 1;
            }

            placePiece(gameBoard, cpuPos, "cpu");
            printGameBoard(gameBoard);

            // check if the CPU have the conditions to win
            result = checkWinner(gameBoard);

            // it this conditions is true, the CPU are the winner,
            // so we need to end the while loop
            if (result.length() > 0) {
                System.out.println(result);
                break;
            }
        }
    }


    public static void printGameBoard(char[][] gameBoard) {

        for (char[] row : gameBoard) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public static void placePiece(char[][] gameBoard, int pos, String user) {

        char symbol = ' ';

        // check if it's players turn or not,
        // add pos to keep track of X and 0
        if (user.equals("player")) {
            symbol = 'X';
            playerPositions.add(pos);
        } else if (user.equals("cpu")) {
            symbol = '0';
            cpuPositions.add(pos);
        }
        // Convert the input to the right place in the array/gameBoard.
        switch (pos) {
            case 1:
                gameBoard[0][0] = symbol; // top left
                break;
            case 2:
                gameBoard[0][2] = symbol; // top center
                break;
            case 3:
                gameBoard[0][4] = symbol; // topRight
                break;
            case 4:
                gameBoard[2][0] = symbol; // center left
                break;
            case 5:
                gameBoard[2][2] = symbol; // center
                break;
            case 6:
                gameBoard[2][4] = symbol; // center right
                break;
            case 7:
                gameBoard[4][0] = symbol; // bottom left
                break;
            case 8:
                gameBoard[4][2] = symbol; // bottom center
                break;
            case 9:
                gameBoard[4][4] = symbol; // bottom right
                break;
            default:
                break;
        }
    }

    public static String checkWinner(char[][] gameBoard) {

        //lists of winning conditions
        List topRow = Arrays.asList(1, 2, 3);
        List midRow = Arrays.asList(4, 5, 6);
        List botRow = Arrays.asList(7, 8, 9);

        List leftCol = Arrays.asList(1, 4, 7);
        List midCol = Arrays.asList(2, 5, 8);
        List rightCol = Arrays.asList(3, 6, 9);

        List crossLeft = Arrays.asList(1, 5, 9);
        List crossRight = Arrays.asList(3, 5, 7);

        // Create a new list to contains all the winning conditions, easier to loop throw.
        List<List> winningConditions = new ArrayList<List>();

        winningConditions.add(topRow);
        winningConditions.add(midRow);
        winningConditions.add(botRow);

        winningConditions.add(leftCol);
        winningConditions.add(midCol);
        winningConditions.add(rightCol);

        winningConditions.add(crossLeft);
        winningConditions.add(crossRight);

        // loop throw all the winning conditions to check if one is true or not.
        for (List list : winningConditions) {
            if (playerPositions.containsAll(list)) {
                printGameBoard(gameBoard);
                return "Congratulations You Won!";
            } else if (cpuPositions.containsAll(list)) {
                return "CPU wins! Better luck next time.";
            } else if (playerPositions.size() + cpuPositions.size() == 9) {
                return "CAT";
            }
        }
        return "";
    }
}
