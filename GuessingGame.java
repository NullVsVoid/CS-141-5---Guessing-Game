
/*
 * Name: Caiden Sanders
 * Class: CS 145
 * Lab: Lab 1 - Guessing Game
 * Date: January 25, 2023
 * Purpose: This program is a game where the user guesses a number between 0 and
 * a constant. If the guess is correct on the first try, they win immediately.
 * Otherwise, they're informed if the guess is too high or too low, and their
 * score increases with each incorrect guess. The game ends when the correct
 * number is guessed, and the user is told how many guesses it took.
 */
import java.util.Scanner;
import java.util.Random;

public class GuessingGame {
  private static final int MIN_NUMBER = 1;
  private static final int MAX_NUMBER = 100;
  private static final Random numberGenerator = new Random();
  private static int TARGET = numberGenerator.nextInt(
      MAX_NUMBER - MIN_NUMBER + 1) + MIN_NUMBER;

  /*
   * introduceGame()
   * 
   * Introduces the game, teaching the player how to play, and explaining the
   * rules and scoring mechanism.
   */
  private static void introduceGame() {
    String welcomeMessage = "| Welcome to Lab 1! The Guessing Game |\n"
        + "| ------------------------------------- |\n"
        + "| Guess a number between " + MIN_NUMBER + " and " + MAX_NUMBER + ".|\n"
        + "| If your guess is higher or lower, we'll let you know.|\n"
        + "| Aim to guess the number in the fewest tries possible. |";
    System.out.println(welcomeMessage);
  }

  /*
   * playRound()
   * 
   * Conducts a round of guessing, compares the guess to the target, and
   * informs the user if the guess is higher or lower.
   */
  private static boolean playRound(Scanner scanner) {
    System.out.println("| What's your guess? |");
    int guessedNumber = scanner.nextInt();
    boolean correct = guessedNumber == TARGET;
    if (correct) {
      return true;
    } else {
      System.out.println("| The number is " +
          (guessedNumber < TARGET ? "HIGHER" : "LOWER") + ". |");
      return false;
    }
  }

  /*
   * displayResults()
   * 
   * Prints the results of the game, including total games played, total
   * guesses, and the best game.
   */
  private static void displayResults(int totalGamesPlayed, int totalGuessesMade,
      int bestGame) {
    System.out.println("\n| Your game results: |");
    System.out.println("| ------------------------------------- |");
    System.out.println("| Games played: " + totalGamesPlayed + " |");
    System.out.println("| Total guesses: " + totalGuessesMade + " |");
    System.out.println("| Average guesses/game: " +
        ((double) Math.round((double) totalGuessesMade / totalGamesPlayed * 10) / 10)
        + " |");
    System.out.println("| Best game: " + bestGame + " guesses. |");
    System.out.println("\nThank you for playing!");
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    introduceGame();
    System.out.println("| ------------------------------------- |");

    int gamesPlayed = 0;
    int cumGuessesMade = 0;
    int curGuessesMade = 0;
    int bestGame = Integer.MAX_VALUE;

    boolean game = true;
    while (game) {
      boolean win = playRound(scanner);
      curGuessesMade += 1;

      if (win) {
        gamesPlayed += 1;
        cumGuessesMade += curGuessesMade;
        if (curGuessesMade < bestGame)
          bestGame = curGuessesMade;
        resetTarget();
        System.out.println("| Correct in " + curGuessesMade + " guess(es)! |");

        System.out.println("\nPlay again? (Y/N): ");
        char playAgain = scanner.next().toLowerCase().charAt(0);
        if (playAgain == 'n') {
          displayResults(gamesPlayed, cumGuessesMade, bestGame);
          return;
        }
        curGuessesMade = 0;
      }
    }
  }

  /*
   * resetTarget()
   * 
   * Resets the target number, abstracting the RNG logic from main.
   */
  private static void resetTarget() {
    TARGET = numberGenerator.nextInt(MAX_NUMBER - MIN_NUMBER + 1) + MIN_NUMBER;
  }
}