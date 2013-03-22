package run;
import java.net.InetAddress;
import java.net.UnknownHostException;
 
public class LanScanner {
   
	private static Object[] addresses = new Object[10];

	
	public LanScanner(InetAddress ia) {
	        String[] sAddress = ia.getHostAddress().split("[.]");
	        String lanAddress = sAddress[0] + "." + sAddress[1] + "." + sAddress[2] + ".";
	        for (int s = 1; s <= 10; s++) {
	        	AddressScanner a=  new AddressScanner(lanAddress + s, s);

	        }
	    }
	

	public static void inserIntoArray(String o, int position){
		addresses[position-1]=o;		
	}

	public void getArrayContent() {
		for (int i=0; i<10; i++){
			
			System.out.println("pos: " + i + "\t" + "IP: " + addresses[i]);
		}
		
	}	
	
	public static Object[] getArray(){
		return addresses;
	}
	
	public static int getNumerOfAddresses(){
		int count=0;
		
		for (int i=0; i<10; i++){
			if (addresses[i] != null){
				count++;
			}
		}
		return 	count;
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

