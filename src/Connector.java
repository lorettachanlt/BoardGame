public class Connector {
	
	// Implement an immutable connector that connects two points on the game board.
	// Invariant: 1 <= myPoint1 < myPoint2 <= 6.
	
	private int myPoint1, myPoint2;
	
	public Connector (int p1, int p2) {
		if (p1 < p2) {
			myPoint1 = p1;
			myPoint2 = p2;
		} else {
			myPoint1 = p2;
			myPoint2 = p1;
		}
	}
	
	public int endPt1 ( ) {
		return myPoint1;
	}
	
	public int endPt2 ( ) {
		return myPoint2;
	}
	
	public boolean equals (Object obj) {
		Connector e = (Connector) obj;
		return (e.myPoint1 == myPoint1 && e.myPoint2 == myPoint2);
	}
	
	public String toString ( ) {
		return "" + myPoint1 + myPoint2;
	}
	
	// Format of a connector is endPoint1 + endPoint2 (+ means string concatenation),
	// possibly surrounded by white space. Each endpoint is a digit between
	// 1 and 6, inclusive; moreover, the endpoints aren't identical.
	// If the contents of the given string is correctly formatted,
	// return the corresponding connector.  Otherwise, throw IllegalFormatException.
	public static Connector toConnector (String s) throws IllegalFormatException {
		String newstr = s.trim();
		if (newstr == null) {
			throw new IllegalFormatException("please input a number");
		}
		if (newstr.length() != 2) {
			throw new IllegalFormatException("please input only 2 numbers that are between 1 and 6");
		}		
		else {
			int po1 = Integer.parseInt(newstr.substring(0, 1));
			int po2 = Integer.parseInt(newstr.substring(1, 2));
			if (po1 < 1 || po1 > 6 || po2 < 1 || po2 > 6) {
				throw new IllegalFormatException("the point must be between 1 and 6");
			}
			if (po1 == po2) {
				throw new IllegalFormatException("the 2 points should not be identical");
			}
			else {
			Connector result = new Connector(po1, po2);
			return result;
		}
	}
	}
}