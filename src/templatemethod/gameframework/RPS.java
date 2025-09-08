package templatemethod.gameframework;

import java.util.Random;
import java.util.Scanner;

public class RPS extends Game {
    private final Scanner in = new Scanner(System.in);
    private final Random rng = new Random();

    private int p1Wins, p2Wins;     // GAME = best of 3
    private String player1 = "You", player2 = "CPU";
    private Move move1, move2;

    private enum Move { ROCK, PAPER, SCISSORS }

    @Override
    public void initializeGame(int n) {
        p1Wins = p2Wins = 0;
        move1 = move2 = null;
        System.out.println("Rock–Paper–Scissors (best of 3). First to 2 wins.");
    }

    @Override
    public boolean endOfGame() { return p1Wins == 2 || p2Wins == 2; }

    @Override
    public void playSingleTurn(int player) {
        // Player
        if (player == 0) {
            if (move1 == null) {
                System.out.print("[r]ock, [p]aper, [s]cissors: ");
                String s = in.nextLine().trim().toLowerCase();
                move1 = s.startsWith("r") ? Move.ROCK :
                        s.startsWith("p") ? Move.PAPER :
                                s.startsWith("s") ? Move.SCISSORS : null;
                if (move1 == null) {
                    System.out.println("Invalid. Try again.");
                    return; // keep waiting; CPU won't move until m1 != null
                }
            }
        }

        // CPU —> only choose after player has a valid move
        if (player == 1) {
            if (move1 == null) return;                  // wait for valid human input
            if (move2 == null) move2 = Move.values()[rng.nextInt(3)];
        }

        // Resolve round only when both moves are ready
        if (move1 != null && move2 != null) {
            System.out.println(player1 + ": " + move1 + "  |  " + player2 + ": " + move2);
            if (move1 == move2) {
                System.out.println("Tie.");
            } else if ((move1 == Move.ROCK && move2 == Move.SCISSORS) ||
                    (move1 == Move.PAPER && move2 == Move.ROCK) ||
                    (move1 == Move.SCISSORS && move2 == Move.PAPER)) {
                p1Wins++; System.out.println(player1 + " wins the round (" + p1Wins + "-" + p2Wins + ")");
            } else {
                p2Wins++; System.out.println(player2 + " wins the round (" + p1Wins + "-" + p2Wins + ")");
            }
            move1 = move2 = null; // reset for next round
            System.out.println();
        }
    }

    @Override
    public void displayWinner() {
        System.out.println("Winner: " + (p1Wins > p2Wins ? player1 : player2) +
                "  Final: " + p1Wins + "-" + p2Wins);
    }
}
