public class BasicDate {

	private static final int NUM_FIELDS = 3;

	private int[] rawDate;

	/***
	 * Builds a new raw date object. 
	 * @param date -- a string of the form N1-N2-N3, 
	 * where N1,N2,N3 are positive numbers representable as integers.
	 */
	public BasicDate(String date) {
		String[] split = date.split("-");
		rawDate = new int[NUM_FIELDS];

		for (int i = 0; i < split.length; i++) {
			rawDate[i] = Integer.parseInt(split[i].trim());
		}

	}

	public boolean isValid() {
		
		// Ano bissexto

		if(getYear() % 4 == 0) {
			
			if(getMonth() == 2) {
				
				if(1 <= getDay() && getDay() <= 29) {
					
					return true;
					
				}
			
			}
				
		}
		
		else if(getMonth() == 2) {
			
			
			}
		
		else if(getMonth() == 1 || getMonth() == 3 || getMonth() == 5 || getMonth() == 7 || getMonth() == 8 || getMonth() == 10 || getMonth() == 12) {
			
		}
		
		else if(getMonth() == 4 || getMonth() == 6 || getMonth() == 9 || getMonth() == 11) {
			
		}
			
		return false;
		
	}

	/**
	 * Returns the year field of this date, assuming the string used
	 * in the constructor was a valid date (i.e., isValid() ).
	 * 
	 */
	public int getYear() {
		return rawDate[2];
	}
	
	/**
	 * Returns the day field of this date, assuming the string used
	 * in the constructor was a valid date (i.e., isValid() ).
	 *  
	 */
	public int getDay() {
		return rawDate[0];
	}
	
	/**
	 * Returns the month field of this date, assuming the string used
	 * in the constructor was a valid date (i.e., isValid() ).
	 * 
	 */
	public int getMonth() {
		return rawDate[1];
	}
	
}