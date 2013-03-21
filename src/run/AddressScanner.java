package run;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;


public class AddressScanner implements Runnable, AdressScannerObservable {
	
    private static final int TIMEOUT = 1000;
    private final static  String filename= "IPs.txt";
    
    private String sInetAddress = null;
    
    private int position;
    private ArrayList <AdressScannerObserver> al = new ArrayList<AdressScannerObserver>();
    private HostCheck hc = new HostCheck();
    private File file;
    private FileWriter fw;
    
    
    public AddressScanner(String inetAddress, int position) {

    		file = new File(hc.setFilePath()+ filename);
            this.sInetAddress = inetAddress;
            this.position=position;
            run();
//            new Thread(this).start();
            
    }
 
    @Override
	public void run() {
            try {
                InetAddress ia = InetAddress.getByName(this.sInetAddress);
                fw = new FileWriter(file,true);
//                ++position;
                
//                Process p1 = java.lang.Runtime.getRuntime().exec("ping -w 200 -n 2 "+sInetAddress);
//                int returnVal = p1.waitFor();
//                boolean reachable = (returnVal==0);
                
                boolean reachable = isReachableByPing(sInetAddress);
                
                if (reachable)
                	System.out.println(ia.toString() +" is reachable!");
                else
                	System.out.println(ia.toString() +" is not reachable!");
                
                if ( 	ia.isReachable(TIMEOUT)) 
//                		|| !ia.getCanonicalHostName().equalsIgnoreCase(this.sInetAddress) 
//                		|| !ia.getHostName().equalsIgnoreCase(this.sInetAddress)) 
                {
//                	System.out.println("Reached " + this.sInetAddress + "(" + ia.getCanonicalHostName() + ")");
//                	LanScanner.inserIntoArray(this.sInetAddress, position);
                		
                	fw.write(sInetAddress);
            		fw.write(System.getProperty("line.separator"));
            		fw.flush();
            		fw.close();
                	
                	for (AdressScannerObserver observer : al){
                	
                		observer.inserIntoArray(sInetAddress, position);
                		
//                		System.out.println("Reached " + this.sInetAddress);
                	}
                }                
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
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
	
	public static boolean isReachableByPing(String host) {
		try {
			String cmd = "";
			if (System.getProperty("os.name").startsWith("Windows")) {
				// For Windows
				cmd = "ping -w 200 -n 2 " + host;
			} else {
				// For Linux and OSX
				cmd = "ping -w 200 -c 2 " + host;
			}

			Process myProcess = java.lang.Runtime.getRuntime().exec(cmd);
			int exitStatus = myProcess.waitFor();

			if (exitStatus == 0) {
				return true;
			} else {
				return false;
			}
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
}
