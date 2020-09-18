package dao;

import java.util.List;

import model.Team;
import model.User;

/*
* @author Sami Khwaireh
*/
public interface TeamDao {
	
	List<Team> getTeams();
	
	List<User> getTeamDevelopers(int search);
	User getTeamLeader(int teamId);

}
