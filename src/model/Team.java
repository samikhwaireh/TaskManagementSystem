package model;

public class Team {
	
	private int teamId;
	private String teamName;
	private String teamLeader;
	
	public Team() {
		super();
	}
	public Team(int teamId, String teamName, String teamLeader) {
		super();
		this.teamId = teamId;
		this.teamName = teamName;
		this.teamLeader = teamLeader;
	}
	
	
	public int getTeamId() {
		return teamId;
	}
	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public String getTeamLeader() {
		return teamLeader;
	}
	public void setTeamLeader(String teamLeader) {
		this.teamLeader = teamLeader;
	}
		
}
