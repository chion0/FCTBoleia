public class User {
	
	public static final int CAP_MAX = 5;

	public InfoTrip[] trip;
	
	public String email;
	
	public String username;
	
	public String password;

	public User(String email, String username, String password) {
		
		trip = new InfoTrip[CAP_MAX];
		
		this.email = email;
		
		this.username = username;
		
		this.password = password;
		
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
}
