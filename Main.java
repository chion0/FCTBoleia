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

			String email = in.nextLine();

			if (mi1.searchIndex(email) >= 0) {

				System.out.println("Utilizador ja existente.");
				System.out.println("Registo nao efetuado.");

			}

			else {
				System.out.print("nome (maximo 50 caracteres): ");

				String name = in.nextLine();

				int g = 0;

				for (int i = 0; i < MAX_TRIES; i++) {

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

	/** Lista todas ou algumas deslocacoes registadas: 
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
					
					while(itTrips1.hasNext()) {
						
						
						InfoTrip currentTrip1 = itTrips1.nextTrip();
						
						System.out.println(currentTrip1.getOrigin());
						System.out.println(currentTrip1.getDestination());
						System.out.println(mi1.convertBasicDate(currentTrip1.getDate()) + " " + currentTrip1.getHours() + " " + currentTrip1.getDuration() + " " + currentTrip1.getSeatsFree());
						System.out.println("Boleias registadas: " + currentTrip1.getOccupiedSeats()+ "\n");
					}
					

				}

				else
					System.out.println(mi1.user[loggedUser].getName() + " nao tem deslocacoes registadas.");

			}

			else if (!date.isEmpty()) {
					Iterator itUsers = mi1.iteratorUsers();
					itUsers.reinitialize();
					boolean noUserTrips = true;
					
						while(itUsers.hasNext()) {
							User currentUser = itUsers.nextUser();
							
							Iterator itTrips2 = mi1.iteratorUserTrips(currentUser);
							
							itTrips2.reinitialize();
							
							if(mi1.isTripScheduled(date, currentUser)) {
								
								mi1.orderEmail();
								
								currentUser.getEmail();
								
								while(itTrips2.hasNext()) {
									
								InfoTrip currentTrip2 = itTrips2.nextTrip();

								String tripDate = mi1.convertBasicDate(currentTrip2.getDate());
								
								if(tripDate.equals(date)) {
								
									noUserTrips = false;
								
									System.out.println(currentUser.getEmail());
									System.out.println(currentTrip2.getOrigin());
									System.out.println(currentTrip2.getDestination());
									System.out.println(mi1.convertBasicDate(currentTrip2.getDate()) + " " + currentTrip2.getHours() + " " + currentTrip2.getDuration() + " " + currentTrip2.getSeatsFree());
									System.out.println("Boleias registadas: " + currentTrip2.getOccupiedSeats() + "\n");
								}
							}
						}
					}
					if(noUserTrips)
						System.out.println(mi1.user[loggedUser].getName() + " nao tem deslocacoes registadas para " + date + ".");	
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
			date = date.replaceAll("\\s+", "");
			
			Iterator itUsers = mi1.iteratorUsers();
			
			boolean userExists = false;
			
			itUsers.reinitialize();
			
				while(itUsers.hasNext() && !userExists) {
					User currentUser = itUsers.nextUser();
					
					if(currentUser.getEmail().equals(email)) {
					
						userExists = true;
						
						if(!mi1.isDateValid(date)) 	{
							System.out.println("Data invalida.");	
							break;
							
						}
						
						else if(mi1.isTripScheduled(date, currentUser)) {
							
							Iterator itTrips = mi1.iteratorUserTrips(currentUser);
							
							while(itTrips.hasNext()) {
								
								itTrips.reinitialize();
								
								InfoTrip currentTrip = itTrips.nextTrip();
								String tripDate = mi1.convertBasicDate(currentTrip.getDate());
								
								if(tripDate.equals(date)) {
						
									System.out.println(currentTrip.getOrigin());
									System.out.println(currentTrip.getDestination());
									System.out.println(mi1.convertBasicDate(currentTrip.getDate()) + " " + currentTrip.getHours() + " " + currentTrip.getDuration() + " " + currentTrip.getSeatsFree());
									System.out.println("Lugares vagos: " + currentTrip.getUnoccupiedSeats());
								
							}
				
					}
							
			}
						else  
							System.out.println("Deslocacao nao existe.");
						
			}
					
					else {
						
						currentUser = itUsers.nextUser();
						
					}
						
				}
					
				if(!userExists)
					System.out.println("Utilizador nao existente.");
					
				}
		
			}
	

	/** Regista uma boleia para uma dada deslocacao: */

	private static void processBoleia(Scanner in, MainInteraction mi1) {

		if (isSessionActive()) {
			
			String email = in.next();
			
			String date = in.nextLine();
			date = date.replaceAll("\\s+", "");
			
			Iterator itUsers = mi1.iteratorUsers();
			
			boolean userExists = false;
			
			if(!mi1.user[loggedUser].getEmail().equals(email)) {
				
				itUsers.reinitialize();
				
				while(itUsers.hasNext() && !userExists) {
					User currentUser = itUsers.nextUser();
					
					if(currentUser.getEmail().equals(email)) {
						userExists = true;
						
						if(mi1.isDateValid(date)) {
							if(mi1.isTripScheduled(date, currentUser)) {
								Iterator itUserTrips = mi1.iteratorUserTrips(currentUser);
								
								itUserTrips.reinitialize();
								
								while(itUserTrips.hasNext()) {
									InfoTrip currentTrip = itUserTrips.nextTrip();
									String tripDate = mi1.convertBasicDate(currentTrip.getDate());
									
									if(currentTrip.getOccupiedSeats() < currentTrip.getSeatsFree()) {
										if(tripDate.equals(date)) {
											mi1.newBoleia(currentTrip);
											
											System.out.println("Boleia registada.");
										}
									}
									else
										System.out.println(currentUser.getName() + " nao existe lugar. Boleia nao registada.");
								}
							}
							else
								System.out.println("Deslocacao nao existe.");
						}
						else
							System.out.println("Data invalida.");
					}
				}
			
				if(!userExists)
					System.out.println("Utilizador inexistente.");				
			}
			else 
				System.out.println(mi1.user[loggedUser].getName() + " nao pode dar boleia a si propria. Boleia nao registada.");

		}
		else
			unknownCommand();

	} 


	/** Lista a informacao de uma dada deslocacao: */

	
	/** Retira uma dada deslocacao: */

	private static void processRemove(Scanner in,MainInteraction mi1) {

		if (isSessionActive()) {
			String dateToRemove = in.next();
			dateToRemove = dateToRemove.replaceAll("\\s+", "");
			if(mi1.isDateValid(dateToRemove)) {
				BasicDate date = mi1.convertDate(dateToRemove);
				User user = mi1.user[loggedUser];
			
				if(mi1.isTripScheduled(dateToRemove, user)) {
					
					Iterator itUserTrips = mi1.iteratorUserTrips(user);
					
					itUserTrips.reinitialize();
					
					while(itUserTrips.hasNext()){
						InfoTrip currentTrip = itUserTrips.nextTrip();
						String tripDate = mi1.convertBasicDate(currentTrip.getDate());
						if(tripDate.equals(mi1.convertBasicDate(date)) && currentTrip.getOccupiedSeats() == 0) {
							mi1.deleteTrip(date, user);
							System.out.println("Deslocacao removida.");
							break;
						}
						else {
							System.out.println(user.getName() + " ja nao pode eliminar esta deslocacao.");
							break;
						}
					}
						
				}
				else 
					System.out.println("Deslocacao nao existe.");
			}
			else
				System.out.println("Data invalida.");
		}
		else
			unknownCommand();
}
	
	private static String readOption(Scanner in, MainInteraction mi1) {

		String opt = in.next().toLowerCase();

		return opt;
	}

	private static void executeOption(String opt, MainInteraction mi1, Scanner in) {
		if (!isSessionActive()) {
			switch (opt) {
			case AJUDA:
				changePrompt(mi1);
				processAjuda(mi1);
				break;

			case TERMINA:
				changePrompt(mi1);
				processTermina();
				break;

			case REGISTA:
				changePrompt(mi1);
				processRegista(in, mi1);
				break;

			case ENTRADA:
				changePrompt(mi1);
				processEntrada(in, mi1);
				break;

			default:
				changePrompt(mi1);
				unknownCommand();
				in.nextLine();
				break;
			}
		} else {
			switch (opt) {
			case AJUDA:
				changePrompt(mi1);
				processAjuda(mi1);
				break;

			case SAI:
				changePrompt(mi1);
				processSai();
				break;

			case NOVA:
				changePrompt(mi1);
				processNova(in, mi1);
				break;

			case LISTA: 
				changePrompt(mi1);
				processLista(in, mi1);
				break;

			case BOLEIA:
				changePrompt(mi1);
				processBoleia(in, mi1);
				break;

			case CONSULTA: 
				changePrompt(mi1);
				processConsulta(in, mi1);
				break;

			case REMOVE:
				changePrompt(mi1);
				processRemove(in, mi1);
				break;

			default:
				changePrompt(mi1);
				unknownCommand();
				in.nextLine();
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
		
		in.close();
	}

}
