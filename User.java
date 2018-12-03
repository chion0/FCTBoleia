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
	
	public boolean isTripFull() {
		return counterTrip == trip.length;
	}
	
	public void resizeTrip() {
		
		InfoTrip[] temp = new InfoTrip[(trip.length * GROWTH)];
		
		for(int i = 0; i <= trip.length; i++)
			temp[i] = trip[i];
		
		trip = temp;
	}	
	
	public void newTrip(String origin, String destination, String date, int time, int freeSeats, float duration) {
		trip[counterTrip++] = new InfoTrip(origin, destination, date, time, freeSeats, duration);
	}
}
