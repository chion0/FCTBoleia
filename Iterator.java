public class Iterator {
	
	/* Variaveis de instancia */
	
	private int counter, currentIndex;
	
	/* Vetores de objetos */
	
	public InfoTrip[] trip;
	
	public User[] users;
	
	/** Construtor:
	 *   
	 * @param boleias
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
	
	public boolean hasNext() {
		return currentIndex < counter;
	}
	
	public InfoTrip nextTrip() {
		return trip[currentIndex++]; 
	}
	
	public User nextUser() {
		return users[currentIndex++]; 
	}

	public void reinitialize() {	
		counter = 0;
	}
	
}
	

