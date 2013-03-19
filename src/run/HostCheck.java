package run;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;


public class HostCheck {

	public HostCheck(){
		
	}
	
	public String getWorkDir(){
		return System.getProperty("user.dir");
	}
	
	
	public String getHomeDir(){
		return System.getProperty("user.home");	
	}
	
	public String getOSName(){
		return System.getProperty("os.name");
	}
	
	public String getHostIP() throws UnknownHostException{
		
			return (String) (InetAddress.getLocalHost().getHostAddress());
		
	}
	
	public String setFilePath(){
		
		String path = getOSName();
		
//		System.out.println(path);
		if (path.contains("Windows")) {
			
			path = getHomeDir() + "\\";
		}
		
		else {
			path = getHomeDir() + "/";
		}
		
			return path;
	}
	

		public static void main(String[] args) {
			HostCheck check = new HostCheck();
			
			String home = check.getHomeDir();
			String work = check.getWorkDir();
			String os = check.getOSName();
			try {
				String ip = check.getHostIP();
				System.out.println(ip);
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(home);
			System.out.println(work);
			System.out.println(os);
			System.out.println(check.setFilePath());
			try {
				getIP();
			} catch (SocketException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
		private static void getIP() throws SocketException {
			Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
			while (interfaces.hasMoreElements()) {
				NetworkInterface current = interfaces.nextElement();
//				System.out.println(current);
				Enumeration<InetAddress> addresses = current.getInetAddresses();
				while (addresses.hasMoreElements()) {
					InetAddress curAdd = addresses.nextElement();
					if (curAdd instanceof Inet4Address) {
						String addr = curAdd.toString();
						addr = addr.trim().substring(1);
						if (addr.startsWith("192.168.16"))
							System.out.println(addr);
					}
				}
			}
		}
	}
