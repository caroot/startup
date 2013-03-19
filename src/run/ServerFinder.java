package run;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

import sun.security.action.GetLongAction;

public class ServerFinder {
	
	private static final int MAX_NETSIZE = 255;
	
	private static HostCheck hc = new HostCheck();
	private String [] addresses;
	
	public ServerFinder(){
		
	}

	
	public void fileToArray() throws IOException{
		int i= 0;
		String line = null;
		
		String path = hc.setFilePath() + "IPs.txt";
		addresses = new String[MAX_NETSIZE];
		System.out.println(path);
		FileReader fr = new FileReader(path);
	    BufferedReader br = new BufferedReader(fr);
	    
	    while (i < 255 && (line = br.readLine()) != null){
			    	addresses[i] = line;
			    	System.out.println(addresses[i]);
			    	
			    	i++;
	    }
	    br.close();
	    fr.close();
	}
	
	public void makeServer(){
		System.out.println(addresses[0]);
		if (addresses[0] != null){
			System.out.println( addresses[1].toString() + " is Server!");
		}
		else {
			
		}
		
	}
	
	private static void checkFile() {
		File tmp = new File(hc.setFilePath()+"Ips.txt");
		if (tmp.exists())
			tmp.delete();
	}
	
	public static void main(String[] args) {
			  
		    try {
		    		checkFile();
					LanScanner ls = new LanScanner(getLocalIP());
					ServerFinder sf = new ServerFinder();
					sf.fileToArray();
//					Thread.sleep(150);
					
//					sf.makeServer();
					sf.makeServer();
				} catch (UnknownHostException e) {
				
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
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
		
	}

