/**
 * @author David Mira_d.mira
 * @author Miguel Brites_m.brites
 */

/**
*Class functionality description:
*This class defines the iterators used for the object arrays.
*It also defines the methods of a classical iterator class (hasNext() and next()).
*/

public class Iterator {
	
	/* Variaveis de instancia */
	
	private int counter, currentIndex;//Stores a counter that keeps track of an array's index -- counter.
									  //Also stores the position (index) the iterator is at -- currentIndex.
	
	/* Vetores de objetos */
	
	public InfoTrip[] trip; //Creates an array of InfoTrip objects named trip. 
	
	public User[] users; //Creates an array of User objects named users.
	
	/** Construtor:
	 * 
	 * @param trip
	 * @param counter
	 * 
	 */
	 
	public Iterator(InfoTrip[] trip, int counter) {
		
		this.counter = counter;
		
		currentIndex = 0;
		
		this.trip = trip;
		
	}
	
	/** Construtor:
	 * 
	 * @param users
	 * @param counter
	 * 
	 */
	
	public Iterator(User[] users, int counter) {
		
		this.counter = counter;
		
		currentIndex = 0;
		
		this.users = users;
		
	}
	
	public boolean hasNext() {// Verifies if an array still has something stored.
		return currentIndex < counter;
	}
	
	public InfoTrip nextTrip() {// Returns the trip object stored in the current position of the iterator.
		return trip[currentIndex++]; 
	}
	
	public User nextUser() {// Returns the user object stored in the current position of the iterator.
		return users[currentIndex++]; 
	}

	public void reinitialize() {// Resets the position of the iterator.	
		currentIndex = 0;
	}
	
}
	

