package run;

import java.io.IOError;
import java.io.IOException;
import java.net.ConnectException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.Enumeration;

import main.java.org.cluster.control.BasicOnlineTest;

public class ServerFinder {
	
	private static final int JBOSS_PORT = 8080;
	private static final String SECURITY_REALM = "ManagementRealm";
	
	private static String myIP = null;
	private BasicOnlineTest ba;
	private String cmd = "";
	
	public ServerFinder() throws IOException{
		LanScanner ls = new LanScanner(getLocalIP());
		ba = new BasicOnlineTest();
	}

	
	
	public void checkIdentity() throws IOException, InterruptedException{
		InetAddress serverAddr = getServer();
		if (serverAddr == null) {
			System.out.println("I am Server!");
			startDomain("DomainController");			
		} else {
			System.out.println("I am Host!");
			startDomain("Host");
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
	
	private InetAddress getServer() throws ConnectException, IOError, IOException {
		String[] tmp = (String[]) LanScanner.getArray();
		InetAddress serverAddr = null;
		boolean serverFound = false;
		int i = 0;
		while(!serverFound && i<10) {
			serverAddr = InetAddress.getByName(tmp[i]);
			serverFound = ba.isAvailable(serverAddr, JBOSS_PORT, SECURITY_REALM);
			i++;
		}
		return serverAddr;
		
	}
	
	private void startDomain(final String s) {
		new Thread(new Runnable() {				
			@Override
			public void run() {
				// TODO Auto-generated method stub
				if (s.equals("DomainController")) {
					String home = System.getProperty("user.home");
					if (System.getProperty("os.name").contains("Windows")) {
						cmd = home + "\\jboss-as-7.1.1.Final\\bin\\domain.bat --host-config=host-master.xml -Djboss.bind.address.management=192.168.16.1" ;
					} else {
						cmd = home + "/jboss-as-7.1.1.Final/bin/domain.sh --host-config=host-master.xml -Djboss.bind.address.management=192.168.16.1" ;
			
					}
				} else {
					System.out.println("Not yet implemented!");
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

