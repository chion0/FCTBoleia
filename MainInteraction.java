public class MainInteraction {
	
	/* Constantes */
	
	public static final int GROWTH = 2, CAP_MAX = 5;
	
	public static final int CAP_MAX_POSSIBLE_CHAR = 35;
	
	/* Vetor de objetos */
	
	public User[] user;
	
	/* Vetores */
	
	public String possibleCharacters = "abcdefghijklmnopqrstuvwxyz0123456789";
	
	/* Variavel de instancia */
	
	public int counterUser;
	
	/* Construtor */
	
	public MainInteraction() {
		
		user = new User[CAP_MAX];
		
		counterUser = 0;
		
	}

	/* Corpo da classe */
	
	public User newUser(String email, String name, String password) {
		
		return new User(email, name, password);
		
	}
		
	public String getUserEmail(User u1) {
		return u1.getEmail();
	}
	
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
	
	public int incCounterUser() {
		return counterUser++;
	}
	
	public void resizeUser() {
		
		User[] temp = new User[(user.length * GROWTH)];
		
		for(int i = 0; i < user.length; i++)
			temp[i] = user[i];
		
		user = temp;
		
	}
	
	public boolean isUserFull() {
		return counterUser == user.length;
	}
	
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
	
	public boolean isDataValid(String date, int time, int freeSeats, float duration) {
		
		BasicDate bd = new BasicDate(date);
		boolean result = false;
		
		if(bd.isValid() && 0 <= time && time <= 23 && 0 <= freeSeats && 0 < duration) {
			result = true;
		}
		
		return result;
	}
	
	public void scheduleTrip(String origin, String destination, String date, int time, int freeSeats, float duration, int loggedUser) {
		if(user[loggedUser].isTripFull())
			user[loggedUser].resizeTrip();
		
		user[loggedUser].newTrip(origin, destination, date, time, freeSeats, duration);
	}
	
	/* Se counter < 0 nao tera descolacoes registadas */
	
	public Iterator iteratorUserTrips(User currentUser) {
		
		int counter = currentUser.getCounterTrip();
		
		Iterator it1 = new Iterator(currentUser.getTrip(),counter);
		
		return it1;
		
	}
	
	public Iterator iteratorUsers() {

		Iterator it2 = new Iterator(user,counterUser);
	
		return it2;
	}
	
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
	
	
	public int getCounterUser() {
		return counterUser;
	}
	
	public BasicDate convertDate(String date) {
		
		BasicDate bd1 = new BasicDate(date);
		return bd1;
		
	}
	
	public String convertBasicDate(BasicDate bd) {
		
		String date = bd.getDay() + "-" + bd.getMonth() + "-" + bd.getYear();
		
		return date;
	}

	public boolean isDateValid(String date) {
		
		BasicDate bd1 = new BasicDate(date);
		
		return bd1.isValid();
		
	}
	
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
