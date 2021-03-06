import java.util.Scanner;

/**
 * @author David Mira_d.mira
 * @author Miguel Brites_m.brites
 */

/**
*Class functionality description:
*This class interprets the user's commands, inputed on the console, and processes them through a series of methods
*in order to return the correct answer, this answer is displayed on the console. 
*/

public class Main {

	/* Constantes */

	//Defines the options the user can use. 
	private static final String AJUDA = "ajuda", TERMINA = "termina", REGISTA = "regista", ENTRADA = "entrada";
	private static final String SAI = "sai", NOVA = "nova", LISTA = "lista", BOLEIA = "boleia", CONSULTA = "consulta",
			REMOVE = "remove";


	//Defines the maximum number of tries that the user has to correctly insert the password.
	private static final int MAX_TRIES = 2;

	//Defines the minimum and maximum number of characters that the password can have.
	private static final int MIN_LENGTH = 3, MAX_LENGTH = 5;

	/* Variaveis de Instancia */

	private static String prompt = "> "; // Stores the initial prompt appearance.
	private static int loggedUser;  //  Stores which user is logged in.
	private static boolean termina = false; // Stores a boolean value that allows for the termination of the program's execution. 
	private static SessionState St1; // Stores information about the state of the session (activated or deactivated). 

	/* Corpo da Classe */

	/* Metodos Auxiliares */

	/** Determines if the session is active or not (if someone's logged in or not) */
	private static boolean isSessionActive() {

		boolean cond = false;

		if (St1.isSessionActive())
			cond = true;

		return cond;

	}

	/** Changes the way the prompt is displayed according to who is logged in */
	private static void changePrompt(MainInteraction mi1) {

		if (isSessionActive())
			System.out.print(mi1.user[loggedUser].getEmail() + " " + prompt);

		else
			System.out.print(prompt);

	}

	/** Writes an error message */
	private static void unknownCommand() {
		System.out.println("Comando inexistente.");
	}

	/* Metodos Principais */

	/** Gives information about every command available to the user at the time:
	 *
	 * @param: mi1
	 */

	private static void processAjuda(MainInteraction mi1) {
		if (!isSessionActive()) {

			System.out.println("ajuda - Mostra os comandos existentes");
			System.out.println("termina - Termina a execucao do programa");
			System.out.println("regista - Regista um novo utilizador no programa");
			System.out.println("entrada - Permite a entrada (\"login\") dum utilizador no programa");

		} else {

			System.out.println("ajuda - Mostra os comandos existentes");
			System.out.println("sai - Termina a sessao deste utilizador no programa");
			System.out.println("nova - Regista uma nova deslocacao");
			System.out.println("lista - Lista todas ou algumas deslocacoes registadas");
			System.out.println("boleia - Regista uma boleia para uma dada deslocacao");
			System.out.println("consulta - Lista a informacao de uma dada deslocacao");
			System.out.println("remove - Retira uma dada deslocacao");

		}
	}

	/** Terminates the program, this method is only available if no one is logged in */

	private static void processTermina() {

		if (!isSessionActive()) {

			termina = true;

			System.out.println("Obrigado. Ate a proxima.");

		}

		else
			unknownCommand();

	}

	/**
	 * Signs up a new user:
	 * 
	 * @param in
	 * @param mi1
	 * 
	 */

	private static void processRegista(Scanner in, MainInteraction mi1) {

		if (mi1.isUserFull())
			mi1.resizeUser();

		if (!isSessionActive()) {

			String email = in.nextLine().trim();

			if (mi1.searchIndex(email) >= 0) {

				System.out.println("Utilizador ja existente.");
				System.out.println("Registo nao efetuado.");

			}

			else {
				System.out.print("nome (maximo 50 caracteres): ");

				String name = in.nextLine().trim();

				int g = 0;

				for (int i = 0; i <= MAX_TRIES; i++) {

					System.out.print("password (entre 3 e 5 caracteres - digitos e letras): ");

					String password = in.next();

					in.nextLine();

					if (password.length() >= MIN_LENGTH && password.length() <= MAX_LENGTH
							&& mi1.countChar(password) == password.length()) {

						System.out.println("Registo efetuado.");

						mi1.incCounterUser();
						mi1.user[mi1.counterUser - 1] = mi1.newUser(email, name, password);

						break;

					}

					else {

						System.out.println("Password incorrecta.");

						g++;

						if (g == 3)
							System.out.println("Registo nao efetuado.");

					}

				}

			}

		}

		else
			unknownCommand();

	}

	/** 
	 * Allows the login of a user:
	 * 
	 * @param in
	 * @param mi1
	 * 
	 */

	private static void processEntrada(Scanner in, MainInteraction mi1) {

		if (!isSessionActive()) {

			String email = in.nextLine().trim();

			if (mi1.searchIndex(email) >= 0) {
				int user = mi1.searchIndex(email);

				int tries = 0;

				while (tries <= MAX_TRIES) {

					System.out.print("password: ");

					String password = in.next();
					in.nextLine();

					if (mi1.user[user].getPassword().equals(password)) {

						loggedUser = user;
						St1.sessionOn();
						break;

					}

					else {
						System.out.println("Password incorrecta.");
						tries++;
					}
				}
			}

			else
				System.out.println("Utilizador nao existente.");
		}

		else
			unknownCommand();

	}

	/** Signs off a user that is currently logged in */
	
	private static void processSai() {

		if (isSessionActive()) {

			St1.sessionOff();

			System.out.println("Obrigado. Ate a proxima.");

		}

		else
			unknownCommand();

	}

	/**
	 * Arranges a new trip with the provided data:
	 * 
	 * @param in
	 * @param mi1
	 * 
	 */

	private static void processNova(Scanner in, MainInteraction mi1) {

		if (isSessionActive()) {

			in.nextLine();
			String origin = in.nextLine();
			String destination = in.nextLine();
			String date = in.next();
			date = date.replaceAll("\\s+", "");
			int time = in.nextInt();
			float duration = in.nextFloat();
			int freeSeats = in.nextInt();
			in.nextLine();

			if (mi1.isDataValid(date, time, freeSeats, duration)) {

				if (!mi1.isTripScheduled(date, mi1.user[loggedUser])) {

					mi1.scheduleTrip(origin, destination, date, time, freeSeats, duration, loggedUser);
					System.out.println("Deslocacao registada. Obrigado " + mi1.user[loggedUser].getName() + ".");
				}

				else {

					System.out.println(mi1.user[loggedUser].getName() + " ja tem uma deslocacao registada nesta data.");
					System.out.println("Deslocacao nao registada.");

				}

			}

			else {
				System.out.println("Dados invalidos.");
				System.out.println("Deslocacao nao registada.");
			}
		}

		else
			unknownCommand();

	}

	/** 
	 * Lists all the trips the current user has arranged or all the trips some users have on a certain date, depends on the input: 
	 * 
	 * @param in
	 * @param mi1
	 * 
	 */
	
	private static void processLista(Scanner in, MainInteraction mi1) {

		if (isSessionActive()) {

			String date = in.nextLine();
			date = date.replaceAll("\\s+", "");

			if (date.isEmpty()) {

				int counterTrip1 = mi1.user[loggedUser].getCounterTrip();

				if (counterTrip1 > 0) {

					mi1.orderDate(mi1.user[loggedUser]);

					Iterator itTrips1 = mi1.iteratorUserTrips(mi1.user[loggedUser]);
					itTrips1.reinitialize();
					
					/**
					 * @pre: itTrips1.hasNext();
					 */

					while (itTrips1.hasNext()) {

						InfoTrip currentTrip1 = itTrips1.nextTrip();

						System.out.println(currentTrip1.getOrigin());
						System.out.println(currentTrip1.getDestination());
						System.out.println(mi1.convertBasicDate(currentTrip1.getDate()) + " " + currentTrip1.getHours()
								+ " " + currentTrip1.getDuration() + " " + currentTrip1.getSeatsFree());
						System.out.println("Boleias registadas: " + currentTrip1.getOccupiedSeats() + "\n");
					}

				}

				else
					System.out.println(mi1.user[loggedUser].getName() + " nao tem deslocacoes registadas.");

			}

			else if (!date.isEmpty()) {
				
				MainInteraction mi2 = new MainInteraction();
				
				mi2.importUsers(mi1);
				
				mi2.orderEmail();		
				
				Iterator itUsers = mi2.iteratorUsers();
				itUsers.reinitialize();
				boolean noUserTrips = true;
				
				/**
			 	 * @pre: itUsers.hasNext();
			 	 */

				while (itUsers.hasNext()) {
					
					if(!mi2.isDateValid(date)) 	{
						System.out.println("Data invalida.");	
						break;
						
					}
					
					User currentUser = itUsers.nextUser();

					Iterator itTrips2 = mi2.iteratorUserTrips(currentUser);

					itTrips2.reinitialize();

					if (mi1.isTripScheduled(date, currentUser)) {
						

						/**
			 			 * @pre: itTrips2.hasNext();
			 			 */

						while (itTrips2.hasNext()) {

							InfoTrip currentTrip2 = itTrips2.nextTrip();

							String tripDate = mi1.convertBasicDate(currentTrip2.getDate());

							if (tripDate.equals(date)) {

								noUserTrips = false;

								System.out.println(currentUser.getEmail());
								System.out.println(currentTrip2.getOrigin());
								System.out.println(currentTrip2.getDestination());
								System.out.println(
										mi2.convertBasicDate(currentTrip2.getDate()) + " " + currentTrip2.getHours()
												+ " " + currentTrip2.getDuration() + " " + currentTrip2.getSeatsFree());
								System.out.println("Boleias registadas: " + currentTrip2.getOccupiedSeats() + "\n");
							}
						}
					}
				}
				if (noUserTrips && mi2.isDateValid(date))
					System.out.println(
							mi1.user[loggedUser].getName() + " nao existem deslocacoes registadas para " + date + ".");
			}

		}

		else
			unknownCommand();

	}

	/** 
	 * Lists the information about a specific trip the user has arranged on a specific date on the console: 
	 * 
	 * @param in
	 * @param mi1
	 * 
	 */

	private static void processConsulta(Scanner in, MainInteraction mi1) {

		if (isSessionActive()) {

			String email = in.next();

			String date = in.nextLine();
			date = date.replaceAll("\\s+", "");

			Iterator itUsers = mi1.iteratorUsers();

			boolean userFound = false;

			itUsers.reinitialize();

			while (itUsers.hasNext() && !userFound) {
				User currentUser = itUsers.nextUser();
				
				if (mi1.isTripScheduled(date, currentUser)) {
					
					if (currentUser.getEmail().equals(email)) {
						
						userFound = true;
						
						if (mi1.isDateValid(date)) {
							Iterator itTrips = mi1.iteratorUserTrips(currentUser);
							
							itTrips.reinitialize();
							
							while (itTrips.hasNext()) {
								
								InfoTrip currentTrip = itTrips.nextTrip();
								String tripDate = mi1.convertBasicDate(currentTrip.getDate());
								
								if (tripDate.equals(date)) {
									
									System.out.println(currentTrip.getOrigin());
									System.out.println(currentTrip.getDestination());
									System.out.println(
											mi1.convertBasicDate(currentTrip.getDate()) + " " + currentTrip.getHours() + " "
													+ currentTrip.getDuration() + " " + currentTrip.getSeatsFree());
									System.out.println("Lugares vagos: " + currentTrip.getUnoccupiedSeats());
								}
								
							}
							
						}
						else {
							System.out.println("Data invalida.");
							break;
						}
					}
					else if(!itUsers.hasNext()){
						System.out.println("Utilizador inexistente.");
					}
				}
				else {
					if(!itUsers.hasNext()) {
						System.out.println("Deslocacao nao existe.");
					}
					else if(itUsers.hasNext() && currentUser.getEmail().equals(email)) {
						System.out.println("Deslocacao nao existe.");
						break;
					}
				}
			}
		}
}

	/** 
	 * Registers a "boleia" on a specific trip, occupying a free seat:
	 *
	 * @param in
	 * @param mi1
	 *
	 */

	private static void processBoleia(Scanner in, MainInteraction mi1) {

		if (isSessionActive()) {

			String email = in.next();

			String date = in.nextLine();
			date = date.replaceAll("\\s+", "");

			Iterator itUsers = mi1.iteratorUsers();

			boolean userFound = false;
			
			boolean stop = false;
			
			while(itUsers.hasNext() && !userFound) {
				User currentUser = itUsers.nextUser();
				
				if(mi1.isTripScheduled(date, currentUser) && currentUser.getEmail().equals(email)) {
					
					Iterator itUserTrips = mi1.iteratorUserTrips(currentUser);

					itUserTrips.reinitialize();

					while (itUserTrips.hasNext()) {
						InfoTrip currentTrip = itUserTrips.nextTrip();
						String tripDate = mi1.convertBasicDate(currentTrip.getDate());
					
						if (currentTrip.getUnoccupiedSeats() > 0){
						
							if(!mi1.user[loggedUser].getEmail().equals(currentUser.getEmail())) {
									
									userFound = true;
									
									if (mi1.isDateValid(date)) {
										
										if (tripDate.equals(date)) {
											
											mi1.newBoleia(currentTrip);
											System.out.println("Boleia registada.");
										}
									}
									else {
										System.out.println("Data invalida.");
										break;
									}
							}
							else if(mi1.user[loggedUser].getEmail().equals(currentUser.getEmail()) && currentUser.getEmail().equals(email)){
								System.out.println(mi1.user[loggedUser].getName() + " nao pode dar boleia a si propria. Boleia nao registada.");
								userFound = true;
								break;
							}
							else if(!currentUser.getEmail().equals(email) && itUsers.hasNext()) {
								break;
							}
							else if(!currentUser.getEmail().equals(email) && !itUsers.hasNext()) {
								System.out.println("Utilizador inexistente.");
								break;
							}
						}
						else if(currentUser.getEmail().equals(email) && tripDate.equals(date)){
							System.out.println(mi1.user[loggedUser].getName() + " nao existe lugar. Boleia nao registada.");
							stop = true;
							break;
						}
					}
				}
				else if(!itUsers.hasNext() && !stop) {
					System.out.println("Deslocacao nao existe.");
				}
			}
		}
		else
			unknownCommand();
	}


	/** 
	 * Removes a certain trip from the logged in user: 
	 * 
	 * @param in
	 * @param mi1
	 * 
	 */

	private static void processRemove(Scanner in, MainInteraction mi1) {

		if (isSessionActive()) {
			String dateToRemove = in.nextLine();
			dateToRemove = dateToRemove.replaceAll("\\s+", "");
			if (mi1.isDateValid(dateToRemove)) {
				BasicDate date = mi1.convertDate(dateToRemove);
				User user = mi1.user[loggedUser];

				if (mi1.isTripScheduled(dateToRemove, user)) {

					Iterator itUserTrips = mi1.iteratorUserTrips(user);

					itUserTrips.reinitialize();
					
					/**
					 * @pre: itUserTrips.hasNext();
					 */

					while (itUserTrips.hasNext()) {
						InfoTrip currentTrip = itUserTrips.nextTrip();
						String tripDate = mi1.convertBasicDate(currentTrip.getDate());
						if (tripDate.equals(mi1.convertBasicDate(date)) && currentTrip.getOccupiedSeats() == 0) {
							
							mi1.deleteTrip(date, user);
							System.out.println("Deslocacao removida.");
							break;
							
						}
						
						else if(tripDate.equals(mi1.convertBasicDate(date)) && currentTrip.getOccupiedSeats() > 0) {
							System.out.println(user.getName() + " ja nao pode eliminar esta deslocacao.");
							break;
						}
					}

				} else
					System.out.println("Deslocacao nao existe.");
			} else
				System.out.println("Data invalida.");
		} else
			unknownCommand();
	}
	
	/** 
	 * Read the option selected by the user
	 * 
	 * @param in
	 * @param mi1
	 * 
	 */

	private static String readOption(Scanner in, MainInteraction mi1) {

		changePrompt(mi1);

		String opt = in.next().toLowerCase();

		return opt;
	}
	
	/** Interprets the selected option and processes it */

	private static void executeOption(String opt, MainInteraction mi1, Scanner in) {
		if (!isSessionActive()) {
			switch (opt) {
			case AJUDA:
				processAjuda(mi1);
				break;

			case TERMINA:
				processTermina();
				break;

			case REGISTA:
				processRegista(in, mi1);
				break;

			case ENTRADA:
				processEntrada(in, mi1);
				break;

			default:
				unknownCommand();
				in.nextLine();
				break;
			}
			
		} 
		
		else {
			switch (opt) {
			case AJUDA:
				processAjuda(mi1);
				break;

			case SAI:
				processSai();
				break;

			case NOVA:
				processNova(in, mi1);
				break;

			case LISTA:
				processLista(in, mi1);
				break;

			case BOLEIA:
				processBoleia(in, mi1);
				break;

			case CONSULTA:
				processConsulta(in, mi1);
				break;

			case REMOVE:
				processRemove(in, mi1);
				break;

			default:
				unknownCommand();
				in.nextLine();
				break;
			}
		}
	}
	
	/** Main method, initiates the input scanner and allows for user interaction */

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);

		St1 = new SessionState();

		MainInteraction mi1 = new MainInteraction();

		String opt = "";
		
		/**
		 * @pre: !processTermina();
		 */

		do {
			opt = readOption(in, mi1);

			executeOption(opt, mi1, in);
		} while (!termina);

		in.close();
		return;
	}

}
