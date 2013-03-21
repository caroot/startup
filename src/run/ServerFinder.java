package run;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.Enumeration;

public class ServerFinder {
	
	
	public ServerFinder(){
		
	}

	
	
	public void checkIdentity() throws IOException, InterruptedException{
		if (LanScanner.getNumerOfAddresses() == 1){
			System.out.println("I am Server!");
			
			
//			Process myProcess = java.lang.Runtime.getRuntime().exec(cmd);
			
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					String cmd = "C:\\Program Files\\jboss\\jboss-as-7.1.1.Final\\bin\\standalone.bat";
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
//			int returnVal = myProcess.waitFor();
			
//			System.out.println(returnVal);
//			Hier JBOSS Server starten und port für abfrage öffnen
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
					String addr = curAdd.toString();
					addr = addr.trim().substring(1);
					if (addr.startsWith("192.168.16"))
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
					LanScanner ls = new LanScanner(getLocalIP());
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

