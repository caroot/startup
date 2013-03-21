package run;
import java.net.InetAddress;
import java.net.UnknownHostException;
 
public class LanScanner {
   
	private static Object[] addresses = new Object[255];

	
	public LanScanner(InetAddress ia) {
	        String[] sAddress = ia.getHostAddress().split("[.]");
	        String lanAddress = sAddress[0] + "." + sAddress[1] + "." + sAddress[2] + ".";
	        for (short s = 1; s < 10; s++) {
	        	AddressScanner a=  new AddressScanner(lanAddress + s, s);

	        }
	    }
	

	public static void inserIntoArray(String o, int position){
		addresses[position]=o;		
	}

	public void getArrayContent() {
		for (int i=1; i<255; i++){
			
			System.out.println("pos: " + i + "\t" + "IP: " + addresses[i]);
		}
		
	}	
	
	public static void main(String[] args) {
		  
	    try {
				LanScanner ls = new LanScanner(InetAddress.getLocalHost());
				ls.getArrayContent();
			} catch (UnknownHostException e) {
			
				e.printStackTrace();
			}
	
		}

}

