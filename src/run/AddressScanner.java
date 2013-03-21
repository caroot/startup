package run;
import java.io.IOException;
import java.net.InetAddress;


public class AddressScanner implements Runnable {
	
	private String sInetAddress = null;
    private int position;
 
    public AddressScanner(String inetAddress, int position) {

            this.sInetAddress = inetAddress;
            this.position=position;
            run();
    }
 
    @Override
	public void run() {
            try {
                InetAddress ia = InetAddress.getByName(this.sInetAddress);
                
                boolean reachable = isReachableByPing(sInetAddress);
                
                if (reachable)
                {
                	LanScanner.inserIntoArray(sInetAddress, position);
                	System.out.println(ia.toString() +" is reachable!");
                }
                else
                {
                	System.out.println(ia.toString() +" is not reachable!");
                }
            }
            catch (Exception e){
            	e.printStackTrace();
            }
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
