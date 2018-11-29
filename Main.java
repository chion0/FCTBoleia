import java.util.Scanner;

public class Main {
	
	private static String prompt = "> ";
	
	private static void changePrompt(boolean sessionState) {
		if(sessionState)
			System.out.print(+ prompt);
		
		else
			System.out.print(prompt);
		
	}
			
	public static void main(String[] args) {
	
		Scanner in = new Scanner(System.in);
		
		MainInteraction mi1 = new MainInteraction();
		
	}

}
