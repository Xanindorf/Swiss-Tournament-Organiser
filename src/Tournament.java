import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * @author Arian Jafari - aria-jaf@dsv.su.se
 */

public class Tournament {
	private Set<Player> players = new TreeSet<Player>();
	private Map<Player, List<Player>> games = new TreeMap<Player, List<Player>>();
	
	private Scanner scan = new Scanner(System.in);
	private Random random = new Random();
	
	private int rounds;
	private int played = 0;
	private static final int WIN = 3, DRAW = 1;
	
	private void setup() {
		System.out.print("Number of players: ");
		int number = Integer.parseInt(scan.nextLine());
		
		for (int i = 0; i < number; i++) {
			System.out.print("Player " + (i+1) + ": ");
			addPlayer(scan.nextLine());
		}
		
		System.out.print("\nNumber of rounds: ");
		rounds = Integer.parseInt(scan.nextLine());
	}
	
	private void pairings() {
		ArrayList<Player> temp = new ArrayList<Player>();
		if (played == 0) {
			for (Player p : players) {
				temp.add(p);
			}
			while (temp.size() > 1) {
				Player p1 = temp.get(random.nextInt(temp.size()-1));
				temp.remove(p1);
				Player p2 = temp.get(random.nextInt(temp.size()-1));
				temp.remove(p2);
			}
		}
	}
	
	private void addPlayer(String name) {
		Player temp = new Player(name);
		players.add(temp);
		games.put(temp, new ArrayList<Player>());
	}
	
	private void playGame(Player winner, Player loser) {
		loser.incrementGames();
		winner.incrementGames();
		winner.addPoints(WIN);
		
		games.get(winner).add(loser);
		games.get(loser).add(winner);
	}
	
	private void drawGame(Player p1, Player p2) {
		p1.incrementGames();
		p1.addPoints(DRAW);
		p2.incrementGames();
		p2.addPoints(DRAW);
		
		games.get(p1).add(p2);
		games.get(p2).add(p1);
	}
	
	private Player getPlayer(String name) {
		for (Player p : players) {
			if (p.getName().equals(name))
				return p;
		}
		throw new NoSuchElementException("No such player");
	}
	
	private void printStandings() {
		for (Player p : players) {
			System.out.println(p);
		}
	}
	
	private boolean hasPlayed(Player p1, Player p2) {
		return games.get(p1).contains(p2);
	}
	
	public static void main(String[] args) {
		Tournament t = new Tournament();
	}
}
