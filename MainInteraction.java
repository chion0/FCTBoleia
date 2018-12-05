public class MainInteraction {
	
	/* Constantes */
	
	public static final int GROWTH = 2, CAP_MAX = 5;
	
	public static final int CAP_MAX_POSSIBLE_CHAR = 36;
	
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
	
	public InfoTrip[] getTrip(User u1) {		
		return u1.trip;	
	}
	
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
		
		else
			counterUser++;
			
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
	        
	  }      
	        
	    return countChar;
	    
	}
	
	public void resizeUser() {
		
		User[] temp = new User[(user.length * GROWTH)];
		
		for(int i = 0; i <= user.length; i++)
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
		
		while(it1.hasNext()) {
			
			it1.reinitialize();
			
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
	
	public Iterator iteratorCurrentUser(User currentUser) {
		
		int counter = currentUser.getCounterTrip();
		
		Iterator it1 = new Iterator(currentUser.getTrip(),counter);
		
		return it1;
		
	}
	
	public Iterator iteratorAllUsers() {

		Iterator it2 = new Iterator(user,counterUser);
	
		return it2;
	}
	
	private void orderDates(int loggedUser, String date) {
		
		BasicDate bd = new BasicDate(date);
		
		int counter = user[loggedUser].getCounterTrip();
		
		InfoTrip temp[] = user[loggedUser].getTrip();
		
		Iterator it1 = new Iterator(temp, counter);
		
		while(it1.hasNext()) {
			
			it1.reinitialize();
			
			InfoTrip trip = it1.nextTrip();
			
			if(bd.getYear() == trip.getDate().getYear()) {
				
				if(bd.getMonth() == trip.getDate().getMonth()) {
					
					if(bd.getDay() < trip.getDate().getDay()) {
						
						// Definir vetor temporario para dar storage aos valores de getDay()
						
					}
					
					else if(bd.getDay() > trip.getDate().getDay()) {
						
						// Same shit
						
					}
					
				}
				
				else if(bd.getMonth() < trip.getDate().getMonth()) {
					
					// Vetor temp
					
				}
				
				else if(bd.getMonth() > trip.getDate().getMonth()) {
					
					// Vetor temp
					
				}
				
			}
			
			else if(bd.getYear() < trip.getDate().getYear()) {
				
				// vetor temp
				
			}
			
			else if(bd.getYear() > trip.getDate().getYear()) {
				
				// Vetor temp
				
			}
			
		}
		
	}
	
	public int getCounterUser() {
		return counterUser;
	}

}
