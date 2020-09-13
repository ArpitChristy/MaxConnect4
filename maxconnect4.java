import java.io.*;
import java.util.*;

/**
            ARPIT CHRISTY
            1001672198
 *         -- for interactive mode: java MaxConnectFour interactive [ input_file
 *         ] [ computer-next / human-next ] [ search depth]
 *
 *         -- for one move mode java maxConnectFour.MaxConnectFour one-move [
 *         input_file ] [ output_file ] [ search depth]
 * 
 *         description of arguments: [ input_file ] -- the path and filename of
 *         the input file for the game
 * 
 *         [ computer-next / human-next ] -- the entity to make the next move.
 *         either computer or human. can be abbreviated to either C or H. This
 *         is only used in interactive mode
 * 
 *         [ output_file ] -- the path and filename of the output file for the
 *         game. this is only used in one-move mode
 * 
 *         [ search depth ] -- the depth of the minimax search algorithm
 *
 *         References :
 *         https://github.com/anirudha-ani/Artificial-Intelligence/tree/master/Assignment%202%20Max%20Connect%204%20Games
 *         https://www.geeksforgeeks.org/minimax-algorithm-in-game-theory-set-4-alpha-beta-pruning/
 *         https://www.javatpoint.com/ai-alpha-beta-pruning
 * 
 * 
 */

public class maxconnect4 {

  public static AiPlayer aiPlayer;
  public static Scanner scanner = null;
  public static int playColumn = 99;

  public static void main(String[] args) throws CloneNotSupportedException {

    // check for the correct number of arguments
    if (args.length != 4) {
      System.out.println("Four command-line arguments are needed:\n"
          + "Usage: java [program name] interactive [input_file] [computer-next / human-next] [depth]\n"
          + " or:  java [program name] one-move [input_file] [output_file] [depth]\n");

      exit_function(0);
    }

    // parse the input arguments
    String gameMode = args[0].toString(); // the game mode
    String input = args[1].toString(); // the input game file
    int depthLevel = Integer.parseInt(args[3]); // the depth level of the ai search

    // create and initialize the game board
    GameBoard currentGame = new GameBoard(input);

    // create the Ai Player
    aiPlayer = new AiPlayer(depthLevel);

    if (gameMode.equalsIgnoreCase("interactive")) {
      if ((args[2].toString().equalsIgnoreCase("computer-next"))) {
        // currentGame.current()
        // playInteracive(false, currentGame);
        playInteracive(false, currentGame);
      } else if (args[2].toString().equalsIgnoreCase("human-next") || args[2].toString().equalsIgnoreCase("H")) {
        // playInteracive(true, currentGame);
        playInteracive(true, currentGame);
      } else {
        System.out.println("\n" + "value for 'next turn' doesn't recognized.  \n try again. \n");
        exit_function(0);
      }

      if (currentGame.checkIfBoardFull()) {
        System.out.println("\nI can't play.\nThe Board is Full\n\nGame Over.");
        exit_function(0);
      }
    } else if (gameMode.equalsIgnoreCase("one-move")) {
      String outputFile = args[2];
      playOneMove(outputFile, currentGame);
    } else {
      System.out.println("Four command-line arguments are needed:\n"
          + "Usage: java [program name] interactive [input_file] [computer-next / human-next] [depth]\n"
          + " or:  java [program name] one-move [input_file] [output_file] [depth]\n");

      exit_function(0);
    }
    // ************************** end computer play

    return;

  } // end of main()

  private static void exit_function(int value) {
    System.out.println("exiting from maxconnect4.java!\n\n");
    System.exit(value);
  }

  // play interactive
  public static void playInteracive(boolean isHuman, GameBoard board) {
    printBoardAndScore(board);

    System.out.println(isHuman ? "\n Human's turn:\nKindly play your move here(1-7):" : "\n Computer's turn:\n");

    int playC = 99;

    if (isHuman) {
      scanner = new Scanner(System.in);
      do {
        playC = scanner.nextInt();
      } while (!validPlay(playC, board));
    } else {
      playC = aiPlayer.findBestPlay(board);

      if (playC == 99) {
        System.out.println("\nI can't play.\nThe Board is Full\n\nGame Over.");
        return;
      }
    }

    board.playPiece(isHuman ? (playC - 1) : playC);

    System.out.println(
        "move: " + board.getPieceCount() + " , Player:Computer , Column: " + ((isHuman) ? (playC) : (playC + 1)));

    board.printGameBoardToFile(isHuman ? "human_output.txt" : "computer_output.txt");

    if (board.checkIfBoardFull()) {
      printBoardAndScore(board);
      printResult(board);
    } else {
      if (isHuman)
        playInteracive(false, board);
      else
        playInteracive(true, board);
    }
  }

  // play one move
  public static void playOneMove(String outputFile, GameBoard currentGame) {
    int playC;
    System.out.print("\nMaxConnect-4 game:\n");
    System.out.print("Game state before move:\n");
    currentGame.printGameBoard();
    // print the current scores
    System.out
        .println("Score: Player-1 = " + currentGame.getScore(1) + ", Player-2 = " + currentGame.getScore(2) + "\n ");

    if (currentGame.checkIfBoardFull()) {
      System.out.println("\nI can't play.\nThe Board is Full\n\nGame Over.");
      return;
    }

    int currentPlayer = currentGame.getCurrentTurn();
    playC = aiPlayer.findBestPlay(currentGame);
    if (playC == playColumn) {
      System.out.println("\nI can't play.\nThe Board is Full\n\nGame Over.");
      return;
    }

    // play the piece
    currentGame.playPiece(playC);

    // display the current game board
    System.out
        .println("move " + currentGame.getPieceCount() + ": Player " + currentPlayer + ", column " + (playColumn + 1));

    printBoardAndScore(currentGame);
    currentGame.printGameBoardToFile(outputFile);

  }

  // print board and score
  public static void printBoardAndScore(GameBoard board) {
    System.out.print("Game state :\n");

    // print the current game board
    board.printGameBoard();

    // print the current scores
    System.out.println("Score: Player-1 = " + board.getScore(1) + ", Player-2 = " + board.getScore(2) + "\n ");
  }

  // print result
  private static void printResult(GameBoard board) {
    int human_score = board.getScore(1);
    int comp_score = board.getScore(2);

    System.out.println("\n Final Result:");
    if (human_score < comp_score) {
      System.out.println("\n Congratulations!! You won this game.");
    } else if (human_score > comp_score) {
      System.out.println("\n You lost!! Good luck for next game.");
    } else {
      System.out.println("\n Game is tie!!");
    }
  }

  // check if valid play if not then display message
  public static boolean validPlay(int col, GameBoard board) {
    if (board.isValidPlay(col - 1)) {
      return true;
    }
    System.out.println("Kindly enter column value between 1 to 7");
    return false;
  }

} // end of class connectFour
