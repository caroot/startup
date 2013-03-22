package run;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.Enumeration;

import main.java.org.cluster.control.BasicOnlineTest;

public class ServerFinder {
	
	private static final int JBOSS_PORT = 8080;
	
	private static String myIP = null;
	private BasicOnlineTest ba;
	
	
	public ServerFinder() throws IOException{
		LanScanner ls = new LanScanner(getLocalIP());
		ba = new BasicOnlineTest();
	}

	
	
	public void checkIdentity() throws IOException, InterruptedException{
		if (LanScanner.getNumerOfAddresses() == 1){
			System.out.println("I am Server!");
						
			new Thread(new Runnable() {				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					String cmd;
					String home = System.getProperty("user.home");
					if (System.getProperty("os.name").contains("Windows")) {
						cmd = home + "\\jboss-as-7.1.1.Final\\bin\\standalone.bat -b "+ myIP;
					} else {
						cmd = home + "/jboss-as-7.1.1.Final/bin/standalon.sh -b " + myIP;
					}
					
					try {
						Process myProcess = java.lang.Runtime.getRuntime().exec(cmd);
						int returnVal = myProcess.waitFor();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}).start();
			
		} else {
//			ba.isAvailable(server, port, securityRealm);
		}
		
	}

	
	private static InetAddress getLocalIP() throws IOException {
		InetAddress localIP = null;
		Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
		while (interfaces.hasMoreElements()) {
			NetworkInterface current = interfaces.nextElement();
			Enumeration<InetAddress> addresses = current.getInetAddresses();
			while (addresses.hasMoreElements()) {
				InetAddress curAdd = addresses.nextElement();
				if (curAdd instanceof Inet4Address) {
					myIP = curAdd.toString();
					myIP = myIP.trim().substring(1);
					if (myIP.startsWith("192.168.16"))
						localIP = curAdd;
				}
			}
		}
		if (localIP != null)
			return localIP;
		else
			return InetAddress.getLocalHost();
	}

	
	public static void main(String[] args) {
			  
		    try {
					ServerFinder sf = new ServerFinder();
//					ls.getArrayContent();
					sf.checkIdentity();
					
				System.out.println(	);

				} catch (UnknownHostException e) {
				
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
		}
		
				
	}

