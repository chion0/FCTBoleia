public class InfoTrip {
	
	/* Variaveis de instancia */
	
	public String origin;

	public String destiny;

	public BasicDate date;
	
	public int hours;

	public int seatsFree;
	
	public float duration;
	
	/** Construtor:
	 * 
	 * @param origin
	 * @param destiny
	 * @param date
	 * @param hours
	 * @param seatsFree
	 * @param duration
	 * 
	 */
	
	public InfoTrip(String origin, String destiny, String date, int hours, int seatsFree, float duration) {
		
		BasicDate Bd1 = new BasicDate(date);
		
		this.origin = origin;
		
		this.destiny = destiny;
		
		this.date = Bd1;
		
		this.hours = hours;
		
		this.seatsFree = seatsFree;
		
		this.duration = duration;
		
	}
	
	/* Corpo da classe */
	
	public String getOrigin() {
		return origin;
	}
	
	public String getDestiny() {
		return destiny;
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
	
	public float getDuration() {
		return duration;
	}
	
}
