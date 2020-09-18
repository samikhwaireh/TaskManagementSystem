package model;


public class User {
	
	
	public static final String ROLE_MANAGER = "Manager";
	public static final String ROLE_TEAM_LEADER = "TeamLeader";
	public static final String ROLE_DEVELOPER = "Developer";
	
	private int userId; 
	private String firstName;
	private String lastName;
    private String username;
    private String email;
    private String gender;
    private String password;
    private String role;
    private String teamName;
    private int teamId;
        
    public User(int userId, String firstName, String lastName, String username, String email,
			String gender, String role, int teamId) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.email = email;
		this.gender = gender;
		this.role = role;
		this.teamId = teamId;
	}
    
    public User(int userId, String firstName, String lastName, String username, String email,
			String gender, String role, String teamName) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.email = email;
		this.gender = gender;
		this.role = role;
		this.teamName = teamName;
	}
    
    public User( String firstName, String lastName, String username, String email,
			String gender, String role, int teamId) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.email = email;
		this.gender = gender;
		this.role = role;
		this.teamId = teamId;
	}
    
    protected User(){
    	
    }
    
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
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
	
	
}
