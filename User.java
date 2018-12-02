public class User {
	
	/* Constante */
	
	public static final int CAP_MAX = 5;

	/* Vetor de objetos */
	
	public InfoTrip[] trip;
	
	/* Variaveis de instancia */
	
	public String email;
	
	public String name;
	
	public String password;
	
	/** Construtor:
	 * 
	 * @param email
	 * @param name
	 * @param password
	 * 
	 */

	public User(String email, String name, String password) {
		
		trip = new InfoTrip[CAP_MAX];
		
		this.email = email;
		
		this.name = name;
		
		this.password = password;
		
	}
	
	/* Corpo da classe */
	
	public String getEmail() {
		return email;
	}
	
	public String getName() {
		return name;
	}
	
	public String getPassword() {
		return password;
	}
	
}
