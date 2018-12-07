/**
 * @author David Mira_d.mira
 * @author Miguel Brites_m.brites
 */

/**
*Class functionality description:
*This class interacts with the class Main.
*/

public class MainInteraction {
	
	/* Constantes */
	
	public static final int GROWTH = 2, CAP_MAX = 5;
	
	public static final int CAP_MAX_POSSIBLE_CHAR = 35;
	
	/* Vetor de objetos */
	
	public User[] user;
	
	/* Variavel de instancia */
	
	public int counterUser;
	
	public String possibleCharacters = "abcdefghijklmnopqrstuvwxyz0123456789";
	
	/* Construtor */
	
	public MainInteraction() {
		
		user = new User[CAP_MAX];
		
		counterUser = 0;
		
	}

	/** Creates a new user and returns it 
	 * 
	 * @param email
	 * @param name
	 * @param password
	 * @return
	 */
	
	public User newUser(String email, String name, String password) {
		
		return new User(email, name, password);
		
	}
		
	/** Getter for a certain user's email
	 * 
	 * @param u1
	 * @return
	 */
	
	public String getUserEmail(User u1) {
		return u1.getEmail();
	}
	
	/** Searches the users array for a user with a specific email
	 * then returns that user's location on the array or -1 if the user doesn't exist.
	 * 
	 * @param email
	 * @return
	 */
	
	public int searchIndex(String email) {
		
		int i = 0;
		
		int result = -1;
		
		boolean found = false;
		
		while((i < counterUser) && (!found)) {
			
			if(user[i].getEmail().equals(email)) 
				found = true;
				
			else i++;
			
		}
		
		if(found)
			result = i;
		
		return result;
		
	}
	
	/** Counts how many valid characters are in the password and returns the amount 
	 * 
	 * @param password
	 * @return
	 */
	
	public int countChar(String password) {
	    
		int countChar = 0;
	
	    for(int i = 0; i < password.length(); i++) {
	        char c = Character.toLowerCase(password.charAt(i)); 
	        
	        int j = 0;
	    
	        while(j <= CAP_MAX_POSSIBLE_CHAR) {

	        	char b = possibleCharacters.charAt(j);
	   	        
		        if (c == b) { 
		            countChar ++;	
		            break;
	        }
		        
		        else j++;
		        
	    }  
	        
	        if(j > CAP_MAX_POSSIBLE_CHAR) 
	        	break;
	        
	  }      
	        
	    return countChar;
	    
	}
	
	public int incCounterUser() { // Increases the counter that indicates the number of users that are registered.
		return counterUser++;
	}
	
	/** Resizes the users array
	 *
	 * @pre: isUserFull();
	 */
	
	public void resizeUser() {
		
		User[] temp = new User[(user.length * GROWTH)];
		
		for(int i = 0; i < user.length; i++)
			temp[i] = user[i];
		
		user = temp;
		
	}
	
	/** Checks if the user array is full, returns true if it is 
	 * 
	 * @return
	 */
	
	public boolean isUserFull() {
		return counterUser == user.length;
	}
	
	 /** Searches the trips array of a specific user and checks if there is a trip arranged for a specific date,
	  * returns true if there is
	  * 
	  * @param date
	  * @param currentUser
	  * @return
	  */
	
	public boolean isTripScheduled(String date, User currentUser) {
		
		BasicDate bd = new BasicDate(date);
		
		InfoTrip temp[] = currentUser.getTrip();
		
		int counter = currentUser.getCounterTrip();
		
		Iterator it1 = new Iterator(temp, counter);
		
		boolean result = false;
		
		it1.reinitialize();
		
		while(it1.hasNext()) {
					
			InfoTrip trip = it1.nextTrip();
			
			if(bd.getDay() == trip.getDate().getDay()) {
				
				if(bd.getMonth() == trip.getDate().getMonth()) {
					
					if(bd.getYear() == trip.getDate().getYear()) {
						
						result = true;
						
						break;
						
					}
					
				}
				
			}
			
		}
		
		return result;
		
	}
	
	/** Checks if the data introduced for a new trip is valid, returns true if it is 
	 * 
	 * @param date
	 * @param time
	 * @param freeSeats
	 * @param duration
	 * @return
	 */
	
	public boolean isDataValid(String date, int time, int freeSeats, float duration) {
		
		BasicDate bd = new BasicDate(date);
		boolean result = false;
		
		if(bd.isValid() && 0 <= time && time <= 23 && 0 <= freeSeats && 0 < duration) {
			result = true;
		}
		
		return result;
	}
	
	/** Creates a new trip and adds it to the logged in user's trips array:
	 * 
	 * @param origin
	 * @param destination
	 * @param date
	 * @param time
	 * @param freeSeats
	 * @param duration
	 * @param loggedUser
	 */
	
	public void scheduleTrip(String origin, String destination, String date, int time, int freeSeats, float duration, int loggedUser) {
		if(user[loggedUser].isTripFull())
			user[loggedUser].resizeTrip();
		
		user[loggedUser].newTrip(origin, destination, date, time, freeSeats, duration);
	}
	
	/** Creates an iterator object for the user's trips array
	 * 
	 * @return
	 */
	
	public Iterator iteratorUserTrips(User currentUser) {
		
		int counter = currentUser.getCounterTrip();
		
		Iterator it1 = new Iterator(currentUser.getTrip(),counter);
		
		return it1;
		
	}
	
	/** Creates an iterator object for the users array
	 * 
	 * @return
	 */
	
	public Iterator iteratorUsers() {

		Iterator it2 = new Iterator(user,counterUser);
	
		return it2;
	}
	
	/** Algorithm that defines which one of the selected dates is inferior:
	 * 
	 * @param bd1
	 * @param bd2
	 * @return
	 */
	
	public boolean isDateInferior(BasicDate bd1,BasicDate bd2) {

		boolean result = false;
		
		if(bd1.getYear() < bd2.getYear()) {
			return result = true;
		}
		
		else if(bd1.getYear() > bd2.getYear()) {
			return result = false;
		}
		
			else if(bd1.getMonth() < bd2.getMonth()) {
				return result = true;
			}
		
			else if(bd1.getMonth() > bd2.getMonth()) {
				return result = false;
			}
		
				else if(bd1.getDay() < bd2.getDay()) {
					return result = true;
				}
		
				else if(bd1.getDay() > bd2.getDay()) {
					return result = false;
				}
		
		return result;
		
}
	
	/** Orders dates using the algorithm made in the method above:
	 * 
	 * @param currentUser
	 */

	public void orderDate(User currentUser) {
		
		int counter = currentUser.getCounterTrip();
		
		InfoTrip[] v = currentUser.trip;
		
		for(int i = 0; i < counter; i++) {
			for(int j = counter - 1; j > i; j--) {
				
				if(!isDateInferior(v[j-1].getDate(),v[j].getDate())) {
					
					 InfoTrip temp = v[j-1];
					 
					 v[j-1] = v[j];
					 
					 v[j] = temp;
					
				}
				
			}
		
		}
	}
	
	/* Orders emails in an alphabetic way */
	
	public void orderEmail() {	
		
		for(int i = 0; i < getCounterUser(); i++) {
			for(int j = getCounterUser() - 1; j > i; j--) {
				
				if(user[j-1].getEmail().compareTo(user[j].getEmail()) > 0) {
					
					 User temp = user[j-1];
					 
					 user[j-1] = user[j];
					 
					 user[j] = temp;
					 
				}
				
				}
			
			}
		
	}
	
	// Getter for the ammount of users in the user array.
	
	public int getCounterUser() {
		return counterUser;
	}
	
	/** Converts a String date into a BasicDate date:
	 * 
	 * @param date
	 * @return
	 */
	
	public BasicDate convertDate(String date) {
		
		BasicDate bd1 = new BasicDate(date);
		return bd1;
		
	}
	
	/** Converts a BasicDate date into a String date:
	 * 
	 * @param bd
	 * @return
	 */
	
	public String convertBasicDate(BasicDate bd) {
		
		String date = bd.getDay() + "-" + bd.getMonth() + "-" + bd.getYear();
		
		return date;
	}

	public boolean isDateValid(String date) {
		
		BasicDate bd1 = new BasicDate(date);
		
		return bd1.isValid();
		
	}
	
	/** Deletes a trip from the trips array:
	 * 
	 * @param bd
	 * @param user
	 * @return
	 */
	
	public InfoTrip[] deleteTrip(BasicDate bd, User user) {
		InfoTrip[] userTrips = user.getTrip(); 
		int counterUserTrips = user.getCounterTrip();
		int i = 0;
		int result = -1;
		InfoTrip[] updatedUserTrips = this.user[searchIndex(user.getEmail())].getTrip();
		
		while(i < counterUser && result == -1) {
			String date = convertBasicDate(bd);
			String tripDate = convertBasicDate(userTrips[i].getDate());
			
			if(tripDate.equals(date)) {
				result = i;
				userTrips[i] = userTrips[counterUserTrips - 1];
				counterUserTrips--;
				updatedUserTrips = userTrips;
				this.user[searchIndex(user.getEmail())].decCounterTrip();
			}
			else i++;
		}
		
		return updatedUserTrips;
	}

	public void newBoleia(InfoTrip currentTrip) {
		currentTrip.incOccupiedSeats();
	}
}
