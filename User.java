public class User {
	
	/* Constante */
	
	public static final int CAP_MAX = 5;

	/* Vetor de objetos */
	
	public InfoTrip[] trip;
	
	/* Variaveis de instancia */
	
	public String email;
	
	public String username;
	
	public String password;
	
	/** Construtor:
	 * 
	 * @param email
	 * @param username
	 * @param password
	 * 
	 */

	public User(String email, String username, String password) {
		
		trip = new InfoTrip[CAP_MAX];
		
		this.email = email;
		
		this.username = username;
		
		this.password = password;
		
	}
	
	/* Corpo da classe */
	
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
