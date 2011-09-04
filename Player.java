public class Player {
	private String 	name;
	private String  team;
	private String  position;
	private int    	points;
	private int		cost;
	private boolean in_use;
	
	Player(String n, String t, String p, int pts, int c){
		setName(n);
		setTeam(t);
		setPosition(p);
		setPoints(pts);
		setCost(c);
		in_use = false;
	}
	
	public void updatePlayer(String n, String t, String p, int pts, int c){
		if (n != null){
			setName(n);
		}
		
		if (t != null){
			setTeam(t);
		}
		
		if (p != null){
			setPosition(p);
		}
		
		if (pts != -1){
			setPoints(pts);
		}
		
		if (c != -1){
			setCost(c);
		}
	}
	
	public String getName(){
		return name;
	}
	
	public String getTeam(){
		return team;
	}
	
	public String getPosition(){
		return position;
	}
	
	public int getPoints(){
		return points;
	}
	
	public int getCost(){
		return cost;
	}
	
	public void setName(String n){
		name = n;
	}
	
	public void setTeam(String t){
		team = t;
	}
	
	public void setPosition(String p){
		position = p;
	}
	
	public void setPoints(int p){
		points = p;
	}
	
	public void setCost(int c){
		cost = c;
	}
	
	public void use(){
		System.out.println("Player: " + name + " in use");
		in_use = true;
	}
	
	public void free(){
		in_use = false;
	}
	
	public static void main(String args[]){
		System.out.println("Player Class");
	}
	
}