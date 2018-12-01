public class SessionState {
	
	/* Variavel de instancia */
	
	public boolean sessionMode;
	
	/* Construtor */
	
	public SessionState() {
		sessionMode = false;
	}
	
	/* Corpo da classe */
	
	/* Modo sessao ativo */
	
	public void sessionOn() {
		sessionMode = true;
	}
	
	/* Modo inicial ativo */
	
	public void sessionOff() {
		sessionMode = false;
	}
	
	public boolean isSessionActive() {
		return sessionMode;	
	}

}
