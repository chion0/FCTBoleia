public class MainInteraction {
	
	public User[] users;

	public String getUserEmail(User u1) {
		return u1.getEmail();
}	
	
	public void nova(String origin, String destiny, String date, int hours, int seatsFree, float duration, User u1) {
		
		TripsCollection t1 = new TripsCollection(u1);
		
		if(t1.isTripFull(u1))
			t1.resizeTrip(u1);

		u1.trip[t1.counterTrip++] = new InfoTrip(origin, destiny, date, hours, seatsFree, duration);
		
	}
	
	
	
}
