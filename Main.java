import java.util.Scanner;

public class Main {
	
	/* Constante */
	
	private static String prompt = "> ";
	
	private static void changePrompt(boolean sessionState) {
		if(sessionState)
			System.out.print(+ prompt);
		
		else
			System.out.print(prompt);
		
	}
	
	/** Regista um novo utilizador no programa:
	 * 
	 * @param email
	 * 
	 */
	
	private static void processRegista(String email) {
		
		if(!isSessionActive()) {
			
			
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
	
	private static void processEntrada(String email, Scanner in) {
		
		if(isSessionActive()) {
			
			
			
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
		
		else {
			
			System.out.println("Comando inexistente.");
			
		}
		
	}
			
	public static void main(String[] args) {
	
		Scanner in = new Scanner(System.in);
		
		MainInteraction mi1 = new MainInteraction();
		
	}

}
