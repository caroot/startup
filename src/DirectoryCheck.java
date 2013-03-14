
public class DirectoryCheck {

	public DirectoryCheck(){
		
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
	

		public static void main(String[] args) {
			DirectoryCheck check = new DirectoryCheck();
			String home = check.getHomeDir();
			String work = check.getWorkDir();
			String os = check.getOSName();
			System.out.println(home);
			System.out.println(work);
			System.out.println(os);
			
		}
	}
