import java.util.*;
import java.io.*;
import java.util.regex.*;

public class League{
	HashMap <String, Player> players = new HashMap<String, Player>();
	
	League () {
		// Add Empty Default Value
		Player newPlayer = new Player(" ", "", "", 0, 0);
		players.put(" ",newPlayer);
	}
	
	public void loadPlayers(String fileName){
		try {
			File file = new File(fileName);
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = null;
			String name = null;
			String team = null;
			String position = null;
			int points = -1;
			int cost = -1;
			
			while ((line = reader.readLine()) != null){				
				// See if the line is </player>
				Pattern p = Pattern.compile("</player>");
				Matcher m = p.matcher(line);
				if (m.find()){
					addPlayer(name,team,position,points,cost);
					name = null;
					team = null;
					position = null;
					points = -1;
					cost = -1;
				} else {
					Pattern xmlPattern = Pattern.compile("<(.*?)>(.*)<.*?>");
					Matcher xmlMatcher = xmlPattern.matcher(line);
					if (xmlMatcher.find()){
						if (xmlMatcher.group(1).equals("name")){
							name = xmlMatcher.group(2);
						}
						
						if (xmlMatcher.group(1).equals("team")){
							Pattern teamPattern = Pattern.compile("(\\S+)\\s*(\\S*)");
							Matcher teamMatcher = teamPattern.matcher(xmlMatcher.group(2));
							if (teamMatcher.find()){
								team = teamMatcher.group(1);
								position = teamMatcher.group(2);
							}
						}
						
						if (xmlMatcher.group(1).equals("points")){
							points = Integer.parseInt(xmlMatcher.group(2));
						}
						
						if (xmlMatcher.group(1).equals("cost")){
							Pattern dollarPattern = Pattern.compile("\\$(.*)");
							Matcher dollarMatcher = dollarPattern.matcher(xmlMatcher.group(2));
							if (dollarMatcher.find()){
								cost = Integer.parseInt(dollarMatcher.group(1));
							} else {
								cost = Integer.parseInt(xmlMatcher.group(2));
							}
						}
					}
				}
			}
		} catch (Exception ex){
			ex.printStackTrace();
		}
	}
	
	public Player getPlayer(String p){
		return players.get(p);
	}
	
	public String[] toArray(){
		ArrayList <Player> playerObjectList = new ArrayList <Player>(players.values());
		System.out.println("Sorting based on value");
		Collections.sort(playerObjectList);
		
		System.out.println("Building list");
		String [] playerNames = new String[playerObjectList.size()];
		
		for (int i = 0; i<playerObjectList.size(); i++){
			String str = playerObjectList.get(i).toString();
			Player player = players.get(str);
			playerNames[i] = str;
		}
		
		return playerNames;
	}
	
	public void addPlayer(String name, String team, String position, int points, int cost){
		Player p = (Player) players.get(name);
		if (p == null){
			Player newPlayer = new Player(name, team, position, points, cost);
			players.put(name,newPlayer);
		} else {
			p.updatePlayer(name,team,position,points,cost);
		}
	}
	
	
	public static void main (String args[]){
		League league = new League();
		league.loadPlayers("300_formated.xml");
		league.loadPlayers("projections_formated.xml");	
		league.go();
	}
	
	public void go(){
		ArrayList <String> playerList = new ArrayList <String>(players.keySet());
		Collections.sort(playerList);
		
		Iterator itr = playerList.iterator();
    	while(itr.hasNext()){
    		Player p = players.get(itr.next());
      		System.out.println("Player: " + p.getName() + "\t" + p.getTeam() + "\t" + p.getCost());	
		}
	}
}
		