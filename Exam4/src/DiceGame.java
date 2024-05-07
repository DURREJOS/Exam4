import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DiceGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of sides for the die being used: ");
        int numSides = scanner.nextInt();

        System.out.println("Enter the number of players: ");
        int numPlayers = scanner.nextInt();
        scanner.nextLine();

        List<Player> players = Players(numPlayers, numSides, scanner);
        rollDice(players);
        determineWinner(players);

        scanner.close();
    }

    public static List<Player> Players(int numPlayers, int numSides, Scanner scanner) {
        List<Player> players = new ArrayList<>();

        for (int i = 1; i <= numPlayers; i++) {
            System.out.println("Enter the name for the player " + i + ": ");
            String playerName = scanner.nextLine();
            Die playerDie = new Die();
            playerDie.setNumSides(numSides);
            Player player = new Player(playerName, playerDie);
            players.add(player);
        }
        return players;
    }

    public static void rollDice(List<Player> players) {
        System.out.println("\nRolling the dice... \n");
        for (Player player : players) {
            player.rollDie();
            int value = player.getDie().getValue();
            System.out.println("Player " + player.getName() + " rolled a " + value + " on a " + player.getDie().getNumSides() + " sided die.");
        }
    }

    public static void determineWinner(List<Player> players) {
        int highestValue = Integer.MIN_VALUE;
        List<String> winners = new ArrayList<>();

        for (Player player : players) {
            int value = player.getDie().getValue();
            if (value > highestValue) {
                highestValue = value;
                winners.clear();
                winners.add(player.getName());
            } else if (value == highestValue) {
                winners.add(player.getName());
            }
        }

        if (winners.size() == 1) {
            System.out.println("\n" + winners.get(0) + " won the game!");
        } else {
            System.out.print("\n");

            for (int i = 0; i < winners.size(); i++) {
                System.out.print(winners.get(i));
                if (i < winners.size() - 2) {
                    System.out.print(", ");
                } else if (i == winners.size() - 2) {
                    System.out.print(" and ");
                }
            }
            System.out.println(" tied the game.");
        }
    }
}
