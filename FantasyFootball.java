import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;

public class FantasyFootball {
	private League league = new League();
	private JPanel panel;
	private ArrayList <PlayerPanel> playerPanels = new ArrayList <PlayerPanel>();
	private JTextField totalPoints = new JTextField(15);
	private JTextField totalCost = new JTextField(15);
	private JFrame frame;

	public static void main(String[] args){
		FantasyFootball ff = new FantasyFootball();
		ff.go();
	}
	
	public class PlayerPanel{
		private JPanel panel;
		private JComboBox pList;
		private JTextField pTeam;
		private JTextField pPosition;
		private JTextField pPoints;
		private JTextField pCost;
		
		PlayerPanel(){
			panel = new JPanel();
		
			pList = new JComboBox(league.toArray());
			pList.setSelectedIndex(0);
		
			pTeam = new JTextField(4);
			pPosition = new JTextField(3);
			pPoints = new JTextField(5);
			pCost   = new JTextField(5);
		
			panel.add(pList);
			panel.add(pTeam);
			panel.add(pPosition);
			panel.add(pPoints);
			panel.add(pCost);
			setValues();
		
			pList.addItemListener(new ItemListener(){
 				public void itemStateChanged(ItemEvent ie){
					setValues();
  				}
  				public void itemSelected(ItemEvent ie){
  					System.out.println("Item selected");
  				}
  			});
  			
		}
		
		public void setValues(){
		  	String str = (String)pList.getSelectedItem();
  			Player p = league.getPlayer(str);
  			pTeam.setText(p.getTeam());
  			pPosition.setText(p.getPosition());
  			pPoints.setText(Integer.toString(p.getPoints()));
  			pCost.setText(Integer.toString(p.getCost()));
			p.use();
  			setTotals();
		}
		
		public JPanel getPanel(){
			return panel;
		}
		
		public String getPlayer(){
			return (String)pList.getSelectedItem();
		}
	
	}
	
	public void setTotals(){
		int cost = 0;
		int points = 0;
		Iterator itr = playerPanels.iterator();
    	while(itr.hasNext()){
    		PlayerPanel playerPanel = (PlayerPanel) itr.next();
    		Player player = league.getPlayer(playerPanel.getPlayer());
	    	cost += player.getCost();
	    	points += player.getPoints();
	    }
	    System.out.println();
		totalCost.setText("Cost: " + Integer.toString(cost));
		totalPoints.setText("Points: " + Integer.toString(points));
	}
	
	public void go(){
		System.out.println("Loading Players...");
		league.loadPlayers("300_formated.xml");
		league.loadPlayers("projections_formated.xml");	
		System.out.println("LOADED");
		
		System.out.println("Building GUI....");
		frame = new JFrame("Fantasy Football Builder");
		
		playerPanels.add(new PlayerPanel());
		playerPanels.add(new PlayerPanel());
		playerPanels.add(new PlayerPanel());
		playerPanels.add(new PlayerPanel());
		playerPanels.add(new PlayerPanel());
		playerPanels.add(new PlayerPanel());
		playerPanels.add(new PlayerPanel());		
  		
  	 	panel = new JPanel();
  		Iterator itr = playerPanels.iterator();
    	while(itr.hasNext()){
    		PlayerPanel playerPanel = (PlayerPanel) itr.next();
    		panel.add(playerPanel.getPanel());
		}
		
		panel.add(totalPoints);
		panel.add(totalCost);
		setTotals();
		
		frame.add(panel);
		
		frame.setSize(600,400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		System.out.println("BUILT");
	}
}