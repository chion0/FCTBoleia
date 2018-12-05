public class InfoTrip {
	
	/* Variaveis de instancia */
	
	public String origin;

	public String destination;

	public BasicDate date;
	
	public int hours;

	public int seatsFree;
	
	public int occupiedSeats;
	
	public float duration;
	
	/** Construtor:
	 * 
	 * @param origin
	 * @param destination
	 * @param date
	 * @param hours
	 * @param seatsFree
	 * @param duration
	 * 
	 */
	
	public InfoTrip(String origin, String destination, String date, int hours, int seatsFree, float duration) {
		
		BasicDate Bd1 = new BasicDate(date);
		
		this.origin = origin;
		
		this.destination = destination;
		
		occupiedSeats = 0;
		
		this.date = Bd1;
		
		this.hours = hours;
		
		this.seatsFree = seatsFree;
		
		this.duration = duration;
		
	}
	
	/* Corpo da classe */
	
	public String getOrigin() {
		return origin;
	}
	
	public String getDestination() {
		return destination;
	}
	
	public BasicDate getDate() {
		return date;
	}
	
	public int getHours() {
		return hours;
	}
	
	public int getSeatsFree() {
		return seatsFree;
	}
	
	public int getOccupiedSeats() {
		return occupiedSeats;
	}
	
	public float getDuration() {
		return duration;
	}
	
}
