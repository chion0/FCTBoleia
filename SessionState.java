public class SessionState {
	
	public boolean sessionMode;

	public SessionState() {
		sessionMode = false;
	}
	
	public void sessionOn() {
		sessionMode = true;
	}
	
	public void sessionOff() {
		sessionMode = false;
	}
	
	public boolean isSessionActive() {
		return sessionMode;	
	}

}
