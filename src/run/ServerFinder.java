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
	private HostCheck hc;
	
	public ServerFinder() throws IOException{
		hc = new HostCheck();
		LanScanner ls = new LanScanner(getLocalIP());
		ba = new BasicOnlineTest();
	}

	
	
	public void checkIdentity() throws IOException, InterruptedException{	
		if (myIP.equals("192.168.16.1")) {
			System.out.println("I am DomainController!");
			startDomain("DomainController");
		} else {
			System.out.println("I am Host!");
			startDomain("Host");
		}
//		InetAddress serverAddr = getServer();
//		if (serverAddr == null) {
//			System.out.println("I am Server!");
//			startDomain("DomainController");			
//		} else {
//			System.out.println("I am Host!");
//			startDomain("Host");
//		}
		
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
	
	private InetAddress getServer() {
		String[] tmp = LanScanner.getArray();
		InetAddress serverAddr = null;
		boolean serverFound = false;
		int i = 0;
		while(!serverFound && i<10) {
			if (tmp[i] != null) {
				try {
					serverAddr = InetAddress.getByName(tmp[i]);
					serverFound = ba.isAvailable(serverAddr, JBOSS_PORT, SECURITY_REALM);
				} catch (ConnectException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOError e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				i++;
			}
		}
		return serverAddr;
		
	}
	
	private void startDomain(final String s) {
		new Thread(new Runnable() {				
			@Override
			public void run() {
				// TODO Auto-generated method stub
				if (s.equals("DomainController")) {
					if (System.getProperty("os.name").contains("Windows")) {
						System.out.println("Starte DomainController Windows");
						String workDir = hc.getWorkDir();
						int index = workDir.lastIndexOf("\\");
						workDir = workDir.substring(0, index);
						cmd = workDir + "\\jboss-eap-6.1\\bin\\domain.bat --host-config=host-master.xml -Djboss.bind.address.management=192.168.16.1" ;
					} else {
						System.out.println("Starte DomainController Not Windows");
						String workDir = hc.getWorkDir();
						int index = workDir.lastIndexOf("/");
						workDir = workDir.substring(0, index);
						cmd = workDir + "/jboss-eap-6.1/bin/domain.sh --host-config=host-master.xml -Djboss.bind.address.management=192.168.16.1" ;
			
					}
				} else {
					if (System.getProperty("os.name").contains("Windows")) {
						System.out.println("Starte Host Windows");
						String workDir = hc.getWorkDir();
						int index = workDir.lastIndexOf("\\");
						workDir = workDir.substring(0, index);
						cmd = workDir + "\\jboss-eap-6.1\\bin\\domain.bat --host-config=host-slave.xml -Djboss.domain.master.address=192.168.16.1 -Djboss.bind.address="+myIP+ " -Djboss.bind.address.management="+myIP;
					} else {
						System.out.println("Starte Host Not Windows");
						String workDir = hc.getWorkDir();
						int index = workDir.lastIndexOf("/");
						workDir = workDir.substring(0, index);
						cmd = workDir + "/jboss-eap-6.1/bin/domain.sh --host-config=host-slave.xml -Djboss.domain.master.address=192.168.16.1 -Djboss.bind.address="+myIP+ " -Djboss.bind.address.management="+myIP;
			
					}
				}
				
				try {
					Process myProcess = java.lang.Runtime.getRuntime().exec(cmd);
					int returnVal = myProcess.waitFor();
					System.out.println(returnVal);
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
					sf.getLocalIP();
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

