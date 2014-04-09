import java.awt.Color;

import java.util.*;

public class Board {

	public static boolean iAmDebugging = true;
	
	// Initialize an empty board with no colored edges.
	private ArrayList<Connector> whitearray = new ArrayList<Connector>();
	private ArrayList<Connector> bluearray = new ArrayList<Connector>();
	private ArrayList<Connector> redarray = new ArrayList<Connector>();
	private ArrayList<Connector> totalarray = new ArrayList<Connector>();
	
	public Board ( ) {
		int p1;
		int p2;
		for (p1 = 1; p1 < 6; p1 ++) {
			for (p2 = p1 + 1; p2 <= 6; p2++) {
				Connector temp = new Connector(p1, p2);
				whitearray.add(temp);
				totalarray.add(temp);
			}
		}
	}
	
	public ArrayList<Connector> gettotalarray () {
		return totalarray;
	}
	
	public int getwhitearraysize () {
		return whitearray.size();
	}
	
	// Add the given connector with the given color to the board.
	// Unchecked precondition: the given connector is not already chosen 
	// as RED or BLUE.
	public void add (Connector cnctr, Color c) {
		if (c == Color.RED) {
			redarray.add(cnctr);
		}
		if (c == Color.BLUE) {
			bluearray.add(cnctr);
		}
		whitearray.remove(whitearray.indexOf(cnctr));
		
	}

	// Set up an iterator through the connectors of the given color, 
	// which is either RED, BLUE, or WHITE. 
	// If the color is WHITE, iterate through the uncolored connectors.
	// No connector should appear twice in the iteration.  
	public Iterator<Connector> connectors (Color c) {
		ArrayList<Connector> temparray = new ArrayList<Connector>();
		if (c == Color.RED) {
			temparray = redarray;
		}
		if (c == Color.BLUE) {
			temparray = bluearray;
		}
		if (c == Color.WHITE) {
			temparray = whitearray;
		}
		Iterator<Connector> arrayiterator = temparray.iterator();
			
		return arrayiterator;
	}
	
	// Set up an iterator through all the 15 connectors.
	// No connector should appear twice in the iteration.  
	public Iterator<Connector> connectors ( ) {
		Iterator<Connector> totalarrayiterator = totalarray.iterator();
		return totalarrayiterator;
	}
	
	// Return the color of the given connector.
	// If the connector is colored, its color will be RED or BLUE;
	// otherwise, its color is WHITE.
	public Color colorOf (Connector e) {
		Color result;
		if (bluearray.contains(e)) {
			result = Color.BLUE;
		}
		else if (redarray.contains(e)) {
			result = Color.RED;
		} else {
			result = Color.WHITE;
	}
		return result;
	}
	
	// Unchecked prerequisite: cnctr is an initialized uncolored connector.
	// Let its endpoints be p1 and p2.
	// Return true exactly when there is a point p3 such that p1 is adjacent
	// to p3 and p2 is adjacent to p3 and those connectors have color c.
	public boolean formsTriangle (Connector cnctr, Color c) {
		boolean result = false;
		ArrayList<Connector> temparray = new ArrayList<Connector>();
		if (c == Color.RED) {
			temparray = redarray;
		}
		else if (c == Color.BLUE) {
			temparray = bluearray;
		}
		
		int x = cnctr.endPt1();
		int y = cnctr.endPt2();
		ArrayList<Integer> store1 = new ArrayList<Integer>();
		ArrayList<Integer> store2 = new ArrayList<Integer>();
		for (int k = 0; k < temparray.size(); k++) {
			if (temparray.get(k).endPt1() == x) {
				store1.add(temparray.get(k).endPt2());
			}
			if (temparray.get(k).endPt2() == x) {
				store1.add(temparray.get(k).endPt1());
			}
			if (temparray.get(k).endPt1() == y) {
				store2.add(temparray.get(k).endPt2());
			}
			if (temparray.get(k).endPt2() == y) {
				store2.add(temparray.get(k).endPt1());
			}	
		}
		
		for (int k = 0; k < store1.size(); k++) {
			if (store2.contains(store1.get(k))) {
				result = true;
			}
		}
		return result;
	}
	
	// The computer (playing BLUE) wants a move to make. 
	// The board is assumed to contain an uncolored connector, with no 
	// monochromatic triangles.
	// There must be an uncolored connector, since if all 15 connectors are colored,
	// there must be a monochromatic triangle.
	// Pick the first uncolored connector that doesn't form a BLUE triangle.
	// If each uncolored connector, colored BLUE, would form a BLUE triangle,
	// return any uncolored connector.
	public Connector choice ( ) {
		for (int k = 0; k < whitearray.size(); k++) {
			if ((formsTriangle(whitearray.get(k), Color.RED)) == false) {
				if ((formsTriangle(whitearray.get(k), Color.BLUE)) == false) {
					return whitearray.get(k);
				}
			}
		}	
		return whitearray.get(1);
	}

	// Return true if the instance variables have correct and internally
	// consistent values.  Return false otherwise.
	// Unchecked prerequisites:
	//	Each connector in the board is properly initialized so that 
	// 	1 <= myPoint1 < myPoint2 <= 6.
	public boolean isOK ( ) {
		for (int k = 0; k < totalarray.size(); k++) {
			int pt1 = totalarray.get(k).endPt1() ;
			int pt2 = totalarray.get(k).endPt2() ;
			if (pt1 < 1 || pt2 > 6 || pt1 > pt2)  {
				return false;
			}
		}
		return true;
		
		
		


}
}