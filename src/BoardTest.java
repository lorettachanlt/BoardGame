import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;

import junit.framework.TestCase;

public class BoardTest extends TestCase {
	
	// Check empty board.
	public void testEmptyBoard ( ) {
		Board b = new Board ( );
		assertTrue (b.isOK ( ));
		checkCollection (b, 0, 0); // applies more tests
		assertTrue (!b.formsTriangle (new Connector (1, 2), Color.RED));
		
	}
	
	// Check one-connector board.
	public void test1Connector ( ) {
		Board b = new Board ( );
		b.add (new Connector (1, 2), Color.RED);
		assertTrue (b.isOK ( ));
		checkCollection (b, 1, 0);
		
		Iterator<Connector> iter = b.connectors (Color.RED);
		assertTrue (iter.hasNext ( ));
		Connector cnctr = iter.next ( );
		assertEquals (b.colorOf (cnctr), Color.RED);
		assertEquals (new Connector (1, 2), cnctr);
		assertTrue (!iter.hasNext ( ));
		
		assertTrue (!b.formsTriangle (new Connector(1,3), Color.RED));
		assertTrue (!b.formsTriangle (new Connector(5,6), Color.RED));
		assertTrue (!b.choice ( ).equals (new Connector (1, 2)));
		assertEquals (b.colorOf (b.choice ( )), Color.WHITE);
	}
	
	// More tests go here.
	
	// (a useful helper method)
	// Make the following checks on a board that should be legal:
	//	Check connector counts (# reds + # blues + # uncolored should be 15.
	//	Check red vs. blue counts.
	//	Check for duplicate connectors.
	//	Check for a blue triangle, which shouldn't exist.
	private void checkCollection (Board b, int redCount, int blueCount) {
		if ((b.getwhitearraysize() + redCount + blueCount ) != 15) {
			throw new IllegalFormatException("board inconsistency");
		}
		if ((redCount - blueCount) > 1 || (blueCount - redCount) > 1) {
			throw new IllegalFormatException("board inconsistency");
		}
		for (int indx1 = 0; indx1 < b.gettotalarray().size(); indx1++ ) {
			for (int indx2 = indx1 + 1; indx2 < b.gettotalarray().size(); indx2++ ) {
				if (b.gettotalarray().get(indx1) == b.gettotalarray().get(indx2) ){
					throw new IllegalFormatException("board inconsistency");
				}
			}
		}
	}
}
