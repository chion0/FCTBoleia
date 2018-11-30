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
	 * @param sessionState
	 * @param email
	 * 
	 */
	
	private static void processRegista(boolean sessionState, String email) {
		
		if(!sessionState) {
			
			
		}
		
		else
			System.out.println("Comando inexistente.");	
		
	}
	
	/** Permite a entrada ("login") dum utilizador no programa:
	 * 
	 * @param email
	 * @param sessionState
	 * @param in
	 * 
	 */
	
	private static void processEntrada(String email,boolean sessionState, Scanner in) {
		
		if(sessionState) {
			
			
			
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
	
	private static void processNova(Scanner in, boolean sessionState) {
		
		if(sessionState) {
			
			
		}
		
		else {
			
			System.out.println("Comando inexistente.");
			
		}
		
	}
	
	private static void processLista() {
		
		
		
		
	}
	
	/** Lista a informacao de uma dada deslocacao:
	 * 
	 * @param email
	 * @param date
	 * @param sessionState
	 * 
	 */
	
	private static void processConsulta(String email, String date, boolean sessionState) {
		
		if(sessionState) {
			
		}
		
		else {
			
			System.out.println("Comando inexistente.");
			
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
	 * 
	 */
	
	private static void processRemove(String date) {
		
		if(isSessionActive) {
			
			
			
		}
		
		else
		  unknownCommand();		
		
	}
			
	public static void main(String[] args) {
	
		Scanner in = new Scanner(System.in);
		
		MainInteraction mi1 = new MainInteraction();
		
	}

}
