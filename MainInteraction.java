public class MainInteraction {
	
	/* Constantes */
	
	public static final int GROWTH = 2, CAP_MAX = 5;
	
	/* Vetor de objetos */
	
	public User[] user;
	
	/* Variavel de instancia */
	
	public int counterUser;
	
	/* Construtor */
	
	public MainInteraction() {
		
		user = new User[CAP_MAX];
		
		counterUser = 0;
		
	}

	/* Corpo da classe */
	
	public String getUserEmail(User u1) {
		return u1.getEmail();
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
