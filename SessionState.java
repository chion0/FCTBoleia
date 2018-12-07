/**
 * @author David Mira_d.mira
 * @author Miguel Brites_m.brites
 */

/**
*Class functionality description:
*This class controls the state/mode of the session.
*/

public class SessionState {
	
	/* Variavel de instancia */
	
	public boolean sessionMode;
	
	/* Construtor */
	
	public SessionState() {
		sessionMode = false;
	}
	
	/* Corpo da classe */
	
	/* Session Mode On */
	
	public void sessionOn() {
		sessionMode = true;
	}
	
	/* Initial Mode On */
	
	public void sessionOff() {
		sessionMode = false;
	}
	
	/* Getter for the mode of the session */
	
	public boolean isSessionActive() {
		return sessionMode;	
	}

}
