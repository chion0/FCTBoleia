public class MainInteraction {
	
	/* Constantes */
	
	public static final int GROWTH = 2, CAP_MAX = 5;
	
	public static final int CAP_MAX_NUMBERS = 10, CAP_MAX_ALPHABET = 26;
	
	/* Vetor de objetos */
	
	public User[] user;
	
	/* Vetores */
	
	public String[] alphabet = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
	
	public int []numbers = {0,1,2,3,4,5,6,7,8,9};
	
	/* Variavel de instancia */
	
	public int counterUser;
	
	/* Construtor */
	
	public MainInteraction() {
		
		user = new User[CAP_MAX];
		
		alphabet = new String[CAP_MAX_ALPHABET];
		
		numbers = new int[CAP_MAX_NUMBERS];
		
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
		
		else
			counterUser++;
			
		return result;
		
	}
	
	public int countChar(String password) {
		
		String temp = alphabet.toString();
		
		int countChar = 0;
		
		for(int i = 0; i < CAP_MAX_NUMBERS; i++) {
			
			for(int j = 0; j < password.length(); j++) {
				
				if(password.compareTo(temp) > 0)
					countChar++;

			}
		}
		
		return countChar;
	}
	
	public int countNumbers(String password) {
		
		int countNumber = 0;
		
		for(int i = 0; i < CAP_MAX_NUMBERS; i++) {
			
			for(int j = 0; j < password.length(); j++) {
				
				Character c = password.charAt(j);
				
				if(Character.isDigit(c))
					countNumber++;
			}
			
		}
		
		return countNumber++;
		
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
	
	
	public void nova(String origin, String destiny, String date, int hours, int seatsFree, float duration, User u1) {
		
		TripsCollection t1 = new TripsCollection(u1);
		
		if(t1.isTripFull(u1))
			t1.resizeTrip(u1);

		u1.trip[t1.counterTrip++] = new InfoTrip(origin, destiny, date, hours, seatsFree, duration);
		
	}
	
}
