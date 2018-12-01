public class TripsCollection {
	
	/* Constantes */
	
	public static final int CAP_MAX = 5, GROWTH = 2;
	
	/* Variavel de instancia */
	
	public int counterTrip;
	
	/** Construtor:
	 * 
	 * @param u1
	 * 
	 */
	 
	public TripsCollection(User u1) {
		
		counterTrip = 0;

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
