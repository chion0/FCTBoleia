
import java.util.Scanner;

public class Main {
	
		/*Constantes*/
	
	private static final String SAI = "SAI", JOGA = "JOGA", FIM = "FIM", AJUDA = "AJUDA", NOVO = "NOVO";
	
		
		/*Variaveis de Instancia*/
	
	private static Key key;
	private static Bet bet;
	private static Jogo jogo;
	private static float premioTotal;

		
		/*Metodos Auxiliares*/
	
	private static void unknownCommand() {
		System.out.println("Commando inexistente.");
	}
	
	private static void prompt(boolean jogoAtivo) {
		if(jogoAtivo)
			System.out.print("FCTMILHOES> ");
		else
			System.out.print("> ");
	}
	
	
		/*Corpo da Classe*/
	
	//Metodo para registar a opcao do interpretador de comandos escolhida pelo utilizador
	private static String registerOption(Scanner in) {
		String option = in.next().toUpperCase();
		
		return option;
	}
	
	private static void processNovo(float premio, Jogo jg) {
		if(!jg.isGameRunning() && premio > 0) {
			
			jg.activateGame();
			
			jogo = jg;
			
			key = jg.getNewKey();
			
			premioTotal = jg.valorPremio(premio);
			
			System.out.printf("Jogo iniciado. Valor do premio: %.2f Euros.\n",premioTotal);
		}
		else if (premio <= 0)
			System.out.println("Valor incorreto.");
		else
			System.out.println("Comando inexistente.");
		
		prompt(jg.isGameRunning());
	}
	
	//Metodo para criacao de um interpretador de comandos	
	private static void executeAjuda(Jogo jg) {
		if(!jg.isGameRunning()) {
			System.out.println("novo - Novo jogo dando um valor inicial");
			System.out.println("sai - Termina a execucao do programa");
			System.out.println("ajuda - Mostra os comandos existentes");
		}
		else if(jg.isGameRunning()) {
			System.out.println("joga - Simula uma aposta, dando uma chave");
			System.out.println("fim - Termina o jogo em curso");
			System.out.println("ajuda - Mostra os comandos existentes");
		}
		else
			unknownCommand();
		
		prompt(jg.isGameRunning());
	}
	

	
	/** @pre: b1.condition() == true
	 *  @param: n1, n2, n3, n4, n5 sao os numeros da chave da aposta
	 *  @param: e1, e2 sao as estrelas da chave da aposta
	 *  
	 *  Metodo de criacao de nova aposta para um jogo previamente criado
	 */
	private static void executeJoga(int n1, int n2, int n3, int n4, int n5, int e1, int e2) {
		bet = jogo.getNewBet();
		
		if(jogo.isGameRunning() || bet.condicao()) {
			bet.joga(n1, n2, n3, n4, n5, e1, e2);
			
			int premioLvl = bet.getPremioLvl(key);
			
			if(premioLvl > 0)
				System.out.println("Obrigado pela aposta. Premio de nivel: " + premioLvl);
			else 
				System.out.println("Obrigado pela aposta.");
		}
		else if(!bet.condicao())
			System.out.println("Chave incorreta.");
		else
			unknownCommand();
		
		prompt(jogo.isGameRunning());
	}
	
	private static void processFim() {
		int i = 0, e = 1;
		float valorPremio = jogo.devolvePremioPorJogador();
		
		if(jogo.isGameRunning()) {
			
			while(i < 13) {
				if(bet.getNumberWinners(key, i) > 0) {
						System.out.print("Nivel: " + e);
						System.out.print(" Jogadores: " + bet.getNumberWinners(key, i));
						System.out.printf(" Valor premio: %.2f",valorPremio);
						System.out.println(" Euros");
				}
				else {
						System.out.println("Nivel: " + e + " Jogadores: " + bet.getNumberWinners(key, i));
				}
				i++;
				e++;
			}
		
			System.out.printf("Valor acumulado: %.2f\n",jogo.devolveValorAcumulado());
		}
		else {
			unknownCommand();
		}
		
		jogo.deactivateGame();
		
		prompt(jogo.isGameRunning());
	}
	
	//Metodo que permite a interpretacao do comando registado por registerOption()
	private static void executeOption(String option, Scanner in, Jogo jg) {
		
			if(!jg.isGameRunning()) {
				switch(option) {
					
					case NOVO:
						float premio = in.nextFloat();
						in.nextLine();
						
						prompt(jg.isGameRunning());
						
						processNovo(premio, jg);
					break;
				
					case AJUDA: 
						prompt(jg.isGameRunning());
						executeAjuda(jg);
					break;
				
					default:
						unknownCommand();
					break;
				}
			}
			else if(jg.isGameRunning()) {
				switch(option) {
					
					case JOGA:
						
						prompt(jg.isGameRunning());
						
						int n1 = in.nextInt();
						int n2 = in.nextInt();
						int n3 = in.nextInt();
						int n4 = in.nextInt();
						int n5 = in.nextInt();
						
						int e1 = in.nextInt();
						int e2 = in.nextInt();
						
						executeJoga(n1, n2, n3, n4, n5, e1, e2);
						
					break;
				
					case AJUDA: 
						prompt(jg.isGameRunning());
						executeAjuda(jg);
					break;
				
					case FIM: 
						processFim();
					break;
				
					default: unknownCommand();
					break;
			}
		}
	}
	
	//Metodo Principal
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Jogo jg = new Jogo();
		
		System.out.print("> ");
		
		String opt = registerOption(in);
		
		while(!opt.equals(SAI)) {
			executeOption(opt, in, jg);
			opt = registerOption(in);
		}
		
		float valorAcumulado = jg.devolveValorAcumulado();
		System.out.print("> ");
		System.out.printf("Valor acumulado: %.2f Euros. Ate a proxima.\n",valorAcumulado);
	}

}
