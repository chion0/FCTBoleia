import java.util.Scanner;

public class Main {
	
	private static String prompt = "> ";
	
	private static void changePrompt(boolean sessionState) {
		if(sessionState)
			System.out.print(+ prompt);
		
		else
			System.out.print(prompt);
		
	}
	
	private static void entrada(String email,boolean sessionState, Scanner in) {
		
		if(sessionState) {
			
			
			
		}
		
		else {
			
		}
		
	}
	
	private static void nova(Scanner in, boolean sessionState) {
		
		if(sessionState) {
			
			
		}
		
		else {
			
			System.out.println("Comando inexistente.");
			
		}
		
	}
	
	private static void consulta(String email, String date, boolean sessionState) {
		
		if(sessionState) {
			
		}
		
		else {
			
			System.out.println("Comando inexistente.");
			
		}
		
	}
	
	private static void remove(String date, boolean sessionState) {
		
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
