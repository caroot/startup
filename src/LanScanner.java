import java.net.InetAddress;
import java.net.UnknownHostException;
 
public class LanScanner implements AdressScannerObserver{
   
	private static Object[] addresses = new Object[255];

	
	public LanScanner(InetAddress ia) {
	        String[] sAddress = ia.getHostAddress().split("[.]");
	        String lanAddress = sAddress[0] + "." + sAddress[1] + "." + sAddress[2] + ".";
	        for (short s = 1; s < 255; s++) {
	        	AddressScanner a=  new AddressScanner(lanAddress + s, s);
	        	a.addAdressScannerObserver(this);
	        }
	    }
	
	public void inserIntoArray(String o, int position){
		
//		System.out.println(position);
		addresses[position]=o;
//		System.out.println("pos: " + position + "\t" + "IP: " + addresses[position]);
		
	}

	private void getArrayContent() {
		for (int i=1; i<255; i++){
//			System.out.println("pos: " + i + "\t" + "IP: " + addresses[i]);
		}
	}	
	
	public static void main(String[] args) {
		  
	    try {
				LanScanner ls = new LanScanner(InetAddress.getLocalHost());
				//for (int i=0; i<100000; i++) {
				//	System.out.print(":");
				//}
				ls.getArrayContent();
			} catch (UnknownHostException e) {
			
				e.printStackTrace();
			}
	
		}

}

