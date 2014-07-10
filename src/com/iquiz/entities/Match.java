package com.iquiz.entities;

public class Match {
	
	public Player player1,player2;
	public String id;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Match(String player1, String player2,String id) {
		super();
		this.player1 = new Player(player1);
		this.player2 = new Player(player2);
		this.id = id;
	}
	
	public Player getPlayerByName(String name){
		if(player1.getName().equals(name))
			return player1;
		return player2;
	}

	public boolean canBeStarted(){
		if(player1.isReady() && player2.isReady())
			return true;
		return false;
	}

	
	public Player getOponentPlayer(String name){
		if(player1.getName().equals(name))
			return player2;
		return player1;
	}
	
}
