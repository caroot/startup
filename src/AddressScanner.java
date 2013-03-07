import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;


public class AddressScanner implements Runnable, AdressScannerObservable {
	private String sInetAddress = null;
    private static final int TIMEOUT = 15000;
    private int position;
    private ArrayList <AdressScannerObserver> al = new ArrayList();
    
    
    
    public AddressScanner(String inetAddress, int position) {
            this.sInetAddress = inetAddress;
            this.position=position;
            new Thread(this).start();
            
    }
 
    public void run() {
            try {
                InetAddress ia = InetAddress.getByName(this.sInetAddress);
//                ++position;
                if ( 	ia.isReachable(TIMEOUT) 
                		|| !ia.getCanonicalHostName().equalsIgnoreCase(this.sInetAddress) 
                		|| !ia.getHostName().equalsIgnoreCase(this.sInetAddress)) 
                {
//                	System.out.println("Reached " + this.sInetAddress + "(" + ia.getCanonicalHostName() + ")");
//                	LanScanner.inserIntoArray(this.sInetAddress, position);
                	
                	for (AdressScannerObserver observer : al){
                	
                		observer.inserIntoArray(sInetAddress, position);
                	}
                }
                
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
        }	
    }

	@Override
	public void addAdressScannerObserver(AdressScannerObserver observer) {
		// TODO Auto-generated method stub
		al.add(observer);
		
	}

	@Override
	public void removeAdressScannerObserver(AdressScannerObserver observer) {
		// TODO Auto-generated method stub
		al.remove(observer);
	}
}
