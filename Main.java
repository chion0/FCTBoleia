import java.util.Scanner;

public class Main {

	/* Constante */

	private static final String AJUDA = "ajuda", TERMINA = "termina", REGISTA = "regista", ENTRADA = "entrada";
	private static final String SAI = "sai", NOVA = "nova", LISTA = "lista", BOLEIA = "boleia", CONSULTA = "consulta",
			REMOVE = "remove";

	private static String prompt = "> ";

	private static final int MAX_TRIES = 3;

	private static final int MIN_LENGTH = 3, MAX_LENGTH = 5;

	/* Variaveis de Instancia */

	private static int loggedUser;
	private static boolean termina = false;
	private static SessionState St1;

	/* Corpo da Classe */

	/* Metodos Auxiliares */

	private static boolean isSessionActive() {

		boolean cond = false;

		if (St1.isSessionActive())
			cond = true;

		return cond;

	}

	private static void changePrompt(MainInteraction mi1) {

		if (isSessionActive())
			System.out.print(mi1.user[loggedUser].getEmail() + prompt);

		else
			System.out.print(prompt);

	}

	private static void unknownCommand() {
		System.out.println("Comando inexistente.");
	}

	/* Metodos Principais */

	/** Informa sobre os comandos disponiveis no programa */

	private static void processAjuda(MainInteraction mi1) {
		if (!isSessionActive()) {

			System.out.println("ajuda - Mostra os comandos existentes");
			System.out.println("termina - Termina a execucao do programa");
			System.out.println("regista - Regista um novo utilizador no programa");
			System.out.println("Permite a entrada (\"login\") dum utilizador no programa");

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

	/** Termina a execucao do programa */

	private static void processTermina() {

		if (!isSessionActive()) {

			termina = true;

			System.out.println("Obrigado. Ate a proxima.");

		}

		else
			unknownCommand();

	}

	/**
	 * Regista um novo utilizador no programa:
	 * 
	 * @param in
	 * @param mi1
	 * 
	 */

	private static void processRegista(Scanner in, MainInteraction mi1) {

		if (mi1.isUserFull())
			mi1.resizeUser();

		if (!isSessionActive()) {

			String email = in.next();

			if (mi1.searchIndex(email) >= 0) {

				System.out.println("Utilizador ja existente.");
				System.out.println("Registo nao efetuado.");

			}

			else {
				System.out.print("nome (maximo 50 caracteres): ");

				String name = in.next();

				int g = 0;

				for (int i = 0; i < MAX_TRIES; i++) {

					System.out.print("password (entre 3 e 5 caracteres - digitos e letras): ");

					String password = in.next();

					in.nextLine();

					if (password.length() >= MIN_LENGTH && password.length() <= MAX_LENGTH
							&& mi1.countChar(password) == password.length()) {

						System.out.println("Registo efetuado.");

						mi1.user[mi1.counterUser - 1] = mi1.newUser(email, name, password);

						break;

					}

					else {

						System.out.println("Password incorreta.");

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
	 * Permite a entrada ("login") dum utilizador no programa:
	 * 
	 * @param in
	 * @param mi1
	 * 
	 */

	private static void processEntrada(Scanner in, MainInteraction mi1) {

		if (!isSessionActive()) {

			String email = in.next();
			in.nextLine();

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
						System.out.println("Password incorreta.");
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

	private static void processSai() {

		if (isSessionActive()) {

			St1.sessionOff();

			System.out.println("Obrigado. Ate a proxima.");

		}

		else
			unknownCommand();

	}

	/**
	 * Regista uma nova descolacao:
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

	/** Lista todas ou algumas deslocacoes registadas: 
	 * 
	 * @param in
	 * @param mi1
	 * 
	 */

	private static void processLista(Scanner in, MainInteraction mi1) {

		if (isSessionActive()) {

			String date = in.nextLine();

			if (date.isEmpty()) {
				
				int counterTrip1 = mi1.user[loggedUser].getCounterTrip();

				if (counterTrip1 > 0) {

					mi1.orderDate(mi1.user[loggedUser]);
					
					Iterator itTrips1 = mi1.iteratorCurrentUser(mi1.user[loggedUser]);
					
					while(itTrips1.hasNext()) {
						InfoTrip currentTrip1 = itTrips1.nextTrip();
						
						System.out.println(currentTrip1.getOrigin());
						System.out.println(currentTrip1.getDestination());
						System.out.println(currentTrip1.getDate().getDay() + "-" + currentTrip1.getDate().getMonth() + "-" + currentTrip1.getDate().getYear() + " " + currentTrip1.getHours() + " " + currentTrip1.getDuration() + " " + currentTrip1.getSeatsFree());
						System.out.println("Boleias registadas: " + currentTrip1.getOccupiedSeats());
					}
					

				}

				else
					System.out.println(mi1.user[loggedUser].getName() + " nao tem deslocacoes registadas.");

			}

			else if (!date.isEmpty()) {
					Iterator itUsers = mi1.iteratorAllUsers();
					boolean noUserTrips = true;
					
						while(itUsers.hasNext()) {
							User currentUser = itUsers.nextUser();
							
							Iterator itTrips2 = mi1.iteratorCurrentUser(currentUser);
							
								
							if(mi1.isTripScheduled(date, currentUser)) {
								
								mi1.orderEmail();
								
								currentUser.getEmail();
								
								while(itTrips2.hasNext()) {
									
								InfoTrip currentTrip2 = itTrips2.nextTrip();
								
								noUserTrips = false;
							
								System.out.println(currentUser.getEmail());
								System.out.println(currentTrip2.getOrigin());
								System.out.println(currentTrip2.getDestination());
								System.out.println(currentTrip2.getDate().getDay() + "-" + currentTrip2.getDate().getMonth() + "-" + currentTrip2.getDate().getYear() + " " + currentTrip2.getHours() + " " + currentTrip2.getDuration() + " " + currentTrip2.getSeatsFree());
								System.out.println("Boleias registadas: " + currentTrip2.getOccupiedSeats());								
								}
							}
						}
						if(noUserTrips)
					System.out.println(mi1.user[loggedUser].getName() + " nao tem deslocacoes registadas para " + date);	
				}

		}
	

		else
			unknownCommand();

	}
	
	/** Lista a informacao de uma dada deslocacao: 
	 * 
	 * @param in
	 * @param mi1
	 * 
	 */
	
	private static void processConsulta(Scanner in, MainInteraction mi1) {

		if (isSessionActive()) {
			
			String email = in.next();

			String date = in.nextLine();
			
			Iterator itUsers = mi1.iteratorAllUsers();
			
			User currentUser = itUsers.nextUser();
			
			int i = 0;
			
			if(i <= mi1.user.length) {
				while(i < mi1.user.length) {
					
					if(!mi1.isValid(date)) {
						
						System.out.println("Data invalida.");
						break;
						
					}
				
					if(!currentUser.getEmail().equals(email)) {
						currentUser = itUsers.nextUser();
						i++;
					}
						
					else {
							break;
				}
			}
		}
			else {
				System.out.println("Utilizador nao existente.");
			}
			
			if(mi1.isTripScheduled(date, currentUser)) {
					
					Iterator itTrips = mi1.iteratorCurrentUser(currentUser);
					
					while(itTrips.hasNext()) {
						
						if(!mi1.isValid(date)) {
							
							System.out.println("Data invalida.");
							break;
						}
					
						InfoTrip currentTrip = itTrips.nextTrip();
						
						if(!currentTrip.getDate().equals(mi1.turnDateIntoBasicDate(date))) {
							currentTrip = itTrips.nextTrip();
							break;
						}
						
						else {
							
							System.out.println(currentTrip.getOrigin());
							System.out.println(currentTrip.getDestination());
							System.out.println(currentTrip.getDate() + " " + currentTrip.getHours() + " " + currentTrip.getDuration() + " " + currentTrip.getSeatsFree());
							
						}
						
				}
			}
					
					else if(mi1.isValid(date)) { 
						
						System.out.println("Deslocacao nao existe.");
						
					}
					
				}
		
			}
	

	/** Regista uma boleia para uma dada deslocacao: */

	private static void processBoleia(Scanner in, MainInteraction mi1) {

		if (isSessionActive()) {
			
			String email = in.next();
			
			String date = in.nextLine();
			
			if(mi1.isTripScheduled(date, currentUser)) {
				
				
				
			}
			
			else if(mi1.isTripScheduled(date, currentUser) && mi1.)) {
				
				
				
			}
			
			else if(/* deslocacao foi registada pelo proprio utilizador */) {
				
				
				
			}
			


		}

		else
			unknownCommand();

	} 


	/** Lista a informacao de uma dada deslocacao: */

	
	/** Retira uma dada deslocacao: */

	private static void processRemove(Scanner in,MainInteraction mi1) {

		if (isSessionActive()) {
			String dateToRemove = in.next();
			User user = mi1.user[loggedUser];
			Iterator itLoggedUserTrips = mi1.iteratorCurrentUser(user);
			
			while(itLoggedUserTrips.hasNext()) {
				if(mi1.isTripScheduled(dateToRemove, user)) {
					
				}
			}
		}

		else
			unknownCommand();

	}
	private static String readOption(Scanner in, MainInteraction mi1) {

		changePrompt(mi1);

		String opt = in.next().toLowerCase();

		return opt;
	}

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
				break;
			}
		} else {
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

			// case BOLEIA: processBoleia();
			// break;

			case CONSULTA: 
				processConsulta(in, mi1);
				break;

			// case REMOVE: processRemove();
			// break;

			default:
				unknownCommand();
				break;
			}
		}
	}

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);

		St1 = new SessionState();

		MainInteraction mi1 = new MainInteraction();

		String opt = "";

		do {
			opt = readOption(in, mi1);

			executeOption(opt, mi1, in);
		} while (!termina);
	}

}
