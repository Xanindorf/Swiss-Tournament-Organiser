public class Player {
	private String name;
	private int points;
	private int games;
	
	public Player(String name){
		this.name = name;
		points = 0;
	}
	
	public String toString() {
		String output = name + " " + points;
		return output;
	}
	public int compareTo(Player other) {
		int comparison = points - other.getPoints();
		if (comparison != 0)
			return comparison;
		return (other.getGames() - games);
	}
	public String getName() {
		return name;
	}
	public int getPoints() {
		return points;
	}
	public void addPoints(int points) {
		this.points += points;
	}
	public int getGames() {
		return games;
	}
	public void incrementGames(){
		games++;
	}
}
