public class TripsCollection {
	
	public static final int CAP_MAX = 5, GROWTH = 2;
	
	public int counterTrip;

	public TripsCollection(User u1) {
		
		counterTrip = 0;

	}
	
	public boolean isTripFull(User u1) {
		return counterTrip == u1.trip.length;
	}
	
	/*public boolean isUserFull() {
		return counterUser == user.length;
	}*/
	
	public void resizeTrip(User u1) {
		
		InfoTrip[] temp = new InfoTrip[(u1.trip.length * GROWTH)];
		
		for(int i = 0; i <= u1.trip.length; i++)
			temp[i] = u1.trip[i];
		
		u1.trip = temp;
		
	}
	
	/*public void resizeUser() {
		
		User[] temp = new User[(user.length * GROWTH)];
		
		for(int i = 0; i <= user.length; i++)
			temp[i] = user[i];
		
		user = temp;
		
	}*/
	
}
