import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;


public class AddressScanner implements Runnable {
	private String sInetAddress = null;
    private static final int TIMEOUT = 15000;
    private int position;
        
    
    public AddressScanner(String inetAddress, int position) {
            this.sInetAddress = inetAddress;
            new Thread(this).start();
            this.position=position;
        }
 
    public void run() {
            try {
                InetAddress ia = InetAddress.getByName(this.sInetAddress);
//                ++position;
                if ( 	ia.isReachable(TIMEOUT) 
                		|| !ia.getCanonicalHostName().equalsIgnoreCase(this.sInetAddress) 
                		|| !ia.getHostName().equalsIgnoreCase(this.sInetAddress)) 
                {
                	System.out.println("Reached " + this.sInetAddress + "(" + ia.getCanonicalHostName() + ")");
                	LanScanner.inserIntoArray(this.sInetAddress, position);
                }
                
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
        }	
    }
}
