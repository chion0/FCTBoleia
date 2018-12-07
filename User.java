/**
 * @author David Mira_d.mira
 * @author Miguel Brites_m.brites
 */

/**
*Class functionality description:
*This class defines an object user, used in other classes.
*/

public class User {
	
	/* Constante */
	
	// Defines the initial capacity of the trip array and it's multiplier when the method resizeTrip is used.
	public static final int CAP_MAX = 5, GROWTH = 2;

	/* Vetor de objetos */
	
	public InfoTrip[] trip; // Stores all the trips.
	
	/* Variaveis de instancia */
	
	public String email; // Stores the email of each user.
	
	public String name; // Stores the name of each user.
	
	public String password; // Stores the password of each user.
	
	public int counterTrip; // Stores the ammount of trips in the trip's array.
	
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
	
	public String getEmail() { // Getter for the email of each user.
		return email;
	}
	
	public String getName() { // Getter for the name of each user.
		return name;
	}
	
	public String getPassword() { // Getter for the password of each user.
		return password;
	}
	
	public InfoTrip[] getTrip() { // Getter for the array trip.
		return trip;		
	}
	
	public int getCounterTrip() { // Getter for the ammount of trips in the trip's array.
		return counterTrip;
	}
	
	public int decCounterTrip() { // Decreases the number of trips in the trip's array.
		return counterTrip--;
		
	}
	/** Checks if the trip array is full, returns true if it is.
	 * 
	 * @return
	 */
	
	public boolean isTripFull() {
		return counterTrip == trip.length;
	}
	
	/** Resizes the trip array
	 *
	 * @pre: isTripFull();
	 */
	
	public void resizeTrip() {
		
		InfoTrip[] temp = new InfoTrip[(trip.length * GROWTH)];
		
		for(int i = 0; i < trip.length; i++)
			temp[i] = trip[i];
		
		trip = temp;
		
	}
	
	/* Creates a new trip and adds it to the logged in user's trips array */
	
	public void newTrip(String origin, String destination, String date, int time, int freeSeats, float duration) {
		trip[counterTrip++] = new InfoTrip(origin, destination, date, time, freeSeats, duration);
	}
	
}
