public class User {
	
	/* Constante */
	
	public static final int CAP_MAX = 5, GROWTH = 2;

	/* Vetor de objetos */
	
	public InfoTrip[] trip;
	
	/* Variaveis de instancia */
	
	public String email;
	
	public String name;
	
	public String password;
	
	public int counterTrip;
	
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
		
		counterTrip = 0;
		
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
	
	public InfoTrip[] getTrip() {
		return trip;		
	}
	
	public int getCounterTrip() {
		return counterTrip;
	}
	
	public boolean isTripFull(User u1) {
		return counterTrip == u1.trip.length;
	}
	
	public void resizeTrip(User u1) {
		
		InfoTrip[] temp = new InfoTrip[(u1.trip.length * GROWTH)];
		
		for(int i = 0; i <= u1.trip.length; i++)
			temp[i] = u1.trip[i];
		
		u1.trip = temp;
		
	}
	
}
