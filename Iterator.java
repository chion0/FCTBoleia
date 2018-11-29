public class Iterator {
	
private int counter, currentIndex;
	
	public InfoTrip[] boleias;
	
	public User[] users;
  
	public Iterator(InfoTrip[] presentes, int contador) {
		
		this.counter = contador;
		
		currentIndex = 0;
		
		this.boleias = presentes;
		
	}
	
	public Iterator(User[] users, int contador) {
		
		this.counter = contador;
		
		currentIndex = 0;
		
		this.users = users;
		
	}
	
	public boolean hasNext() {
		return currentIndex < counter;
	}
	
	public InfoTrip nextTrip() {
		return boleias[currentIndex++]; 
	}
	
	public User nextUser() {
		return users[currentIndex++]; 
	}

	public void reinitialize() {	
		counter = 0;
	}
	
}
	

