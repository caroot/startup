package run;
import java.net.InetAddress;
import java.net.UnknownHostException;


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
	

		public static void main(String[] args) {
			HostCheck check = new HostCheck();
			
			String home = check.getHomeDir();
			String work = check.getWorkDir();
			String os = check.getOSName();
			
			System.out.println(home);
			System.out.println(work);
			System.out.println(os);
			
		}
	}
