public class InfoTrip {
	
	public String origin;

	public String destiny;

	public String date;
	
	public int hours;

	public int seatsFree;
	
	public float duration;
	
	public InfoTrip(String origin, String destiny, String date, int hours, int seatsFree, float duration) {
		
		this.origin = origin;
		
		this.destiny = destiny;
		
		this.date = date;
		
		this.hours = hours;
		
		this.seatsFree = seatsFree;
		
		this.duration = duration;
		
	}
	
	public String getOrigin() {
		return origin;
	}
	
	public String getDestiny() {
		return destiny;
	}
	
	public String getDate() {
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
