/**
 * @author David Mira_d.mira
 * @author Miguel Brites_m.brites
 */

/**
*Class functionality description:
*This class defines an object trip, used in other classes.
*/

public class InfoTrip {
	
	/* Variaveis de instancia */
	
	public String origin; // Stores the place of departure.

	public String destination; // Stores the destination.

	public BasicDate date; // Stores the date in which the trip will occur.
	
	public int hours; // Stores the time at which the trip will occur.

	public int seatsFree; // Stores how many seats are free for use.
	
	public int occupiedSeats; // Stores how many seats are occupied.
	
	public float duration; // Stores the duration of the trip.
	
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
	
	public String getOrigin() { // Getter for the duration of the trip.
		return origin;
	}
	
	public String getDestination() { // Getter for the destination.
		return destination;
	}
	
	public BasicDate getDate() { // Getter for the date of the trip.
		return date;
	}
	
	public int getHours() { // Getter for the time of departure.
		return hours;
	}
	
	public int getSeatsFree() { // Getter for how many seats are available for "boleias".
		return seatsFree;
	}
	
	public int getOccupiedSeats() { // Getter for how many occupied seats there are.
		return occupiedSeats;
	}

	public int getUnoccupiedSeats() { // Getter for how many occupied seats there are.
		return (seatsFree - occupiedSeats);
	}
	
	public float getDuration() { // Getter for the duration of the trip.
		return duration;
	}
	
	
	public int incOccupiedSeats() { // Increases the number of occupied seats.
		occupiedSeats++;
		
		return occupiedSeats;
	}
	
}
