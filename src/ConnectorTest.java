import junit.framework.TestCase;


public class ConnectorTest extends TestCase{
	public static void test() {
		
			Connector a = new Connector(-1,4) ;
			Connector b = new Connector(4,4) ;
			Connector c = new Connector(5,3) ;
			Connector d = new Connector(12,4) ;
			Connector f = new Connector(4,12) ;
			Connector [] testArray = {a, b, c, d, f};
			for (int k = 0; k < testArray.length ; k++) {
				try {
					Connector.toConnector(testArray[k].toString());
				}
				catch (Exception e){
					System.out.println(e + " in " + testArray[k].toString());
				}
			}
			try {
				Connector.toConnector(null);
			} catch (Exception e){
				System.out.println("Null Exception"); 
			}
			try {
				Connector.toConnector("xy");
			} catch (Exception e){
				System.out.println(e); 
			}
			try {
				Connector.toConnector("  42 ");
			} catch (Exception e){
				System.out.println("spaces test doesn't work " + e); 
			}
	}
}
