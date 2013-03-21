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

	
	
	
	
	
	public static void main(String[] args) {
			  
		    try {
					LanScanner ls = new LanScanner(getLocalIP());
					ServerFinder sf = new ServerFinder();
					ls.getArrayContent();

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

