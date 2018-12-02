import java.util.Scanner;

public class Main {
	
	/*Constante*/
	
	private static final String AJUDA = "ajuda", TERMINA = "termina", REGISTA = "regista", ENTRADA = "entrada";
	private static final String SAI = "sai", NOVA = "nova", LISTA = "lista", BOLEIA = "boleia", CONSULTA = "consulta", REMOVE = "remove";
	
	private static String prompt = "> ";
	
	/*Variaveis de Instancia*/
	
	private static boolean termina = false;
	
		/*Corpo da Classe*/
	
	/*Metodos Auxiliares*/

	private static boolean isSessionActive() {
		SessionState St1 = new SessionState();
				
				boolean cond = false;
		
		if(St1.isSessionActive())
			cond = true;
		
		return cond;
	}
	
	private static void changePrompt() {
		if(isSessionActive())
			System.out.print(+ prompt);
		
		else
			System.out.print(prompt);
		
	}
	
	private static void unknownCommand() {
		System.out.println("Comando inexistente.");
	}
	
	
	/*Metodos Principais*/
	
	/** Informa sobre os comandos disponiveis no programa */
	
	private static void processAjuda() {
		if(!isSessionActive()) {
			System.out.println("ajuda - Mostra os comandos existentes");
			System.out.println("termina - Termina a execucao do programa");
			System.out.println("regista - Regista um novo utilizador no programa");
			System.out.println("entrada - Permite a entrada (�login�) dum utilizador no programa");
		}
		else {
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
		
		if(isSessionActive()) {
			
			termina = true;
			
			System.out.println("Obrigado. Ate a proxima.");
			
		}
		
		else 
		  unknownCommand();
		
	}
	
	/** Regista um novo utilizador no programa:
	 * 
	 * @param email
	 * 
	 */
	
	private static void processRegista(Scanner in, MainInteraction mi1, User u1) {
		
		if(!isSessionActive()) {
			
			u1.email = in.next();
			
			if(mi1.searchIndex(u1.email) >= 0) {
				
				System.out.println("Utilizador ja existente.");
				System.out.println("Registo nao efetuado.");
				
			}
				
			else {
					
					String nome = in.next();
					
					System.out.println("nome (maximo 50 caracteres):" + nome);
					
					String password = in.next();
					
					if(password.length() >= 3 && password.length() <= 5 && mi1.countChar(password) > 0 && mi1.countNumbers(password) > 0) {
						
						System.out.println("Registo efetuado.");
						
					}
					
					else
						System.out.println("Password incorreta.");
					
				}
		
		}
		
		else
			unknownCommand();
					
		
	}
	
	/** Permite a entrada ("login") dum utilizador no programa:
	 * 
	 * @param email
	 * @param in
	 * 
	 */
	
	private static void processEntrada(String email, String nome, String password, Scanner in, MainInteraction mi1) {
		
		if(!isSessionActive()) {
			
				
			}
			
		
		else {
			
			
			
		}
		
	}
	
	private static void processSai() {
		
		
		
	}
	
	/** Regista uma nova descolacao:
	 * 
	 * @param in
	 * @param sessionState
	 * 
	 */
	
	private static void processNova(Scanner in) {
		
		if(isSessionActive()) {
			
			
		}
		
		else {
			
			unknownCommand();
			
		}
		
	}
	
	private static void processLista() {
		
		
		
		
	}
	
	/** Lista a informacao de uma dada deslocacao:
	 * 
	 * @param email
	 * @param date
	 * 
	 */
	
	private static void processConsulta(String email, String date) {
		
		if(isSessionActive()) {
			
		}
		
		else {
			
			unknownCommand();
			
		}
		
	}
	
	/** Regista uma boleia para uma dada deslocacao:
	 * 
	 * @param email
	 * @param date
	 * 
	 */
	
	private static void processBoleia(String email, String date) {
		
		
		
	}
	
	/** Retira uma dada deslocacao:
	 * 
	 * @param date
	 * @param sessionState
	 * 
	 */
	
	private static void processRemove(String date, boolean sessionState) {
		
		if(sessionState) {
			
			
			
		}
		
		else 
		  unknownCommand();	
		
	}
	
	private static String readOption(Scanner in){
		String opt = in.next().toLowerCase();
		
		return opt;
	}
	
	private static void executeOption(String opt, MainInteraction mi1, Scanner in) {
		if(!isSessionActive()) {
			switch(opt) {
			case AJUDA: processAjuda();
			break;
			
			case TERMINA: processTermina();
			break;
			
			case REGISTA: processRegista(in,mi1);
			break;
			
		//	case ENTRADA: processEntrada();
			break;
			
			default: unknownCommand();
			break;
			}
		}
		else{
			switch(opt) {
			case AJUDA: processAjuda();
			break;
			
			case SAI: processSai();
			break;
			
		//	case NOVA: processNova();
			break;
			
			case LISTA: processLista();
			break;
			
		//	case BOLEIA: processBoleia();
			break;
			
		//	case CONSULTA: processConsulta();
			break;
			
		//	case REMOVE: processRemove();
			break;
			
			default: unknownCommand();
			break;
			}
		}
	}
			
	public static void main(String[] args) {
	
		Scanner in = new Scanner(System.in);
		
		MainInteraction mi1 = new MainInteraction();
		
		String opt = "";
		
		do {
			opt = readOption(in);
			
	//		executeOption(opt);
		}
		while(!termina);
	}

}