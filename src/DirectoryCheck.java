
public class DirectoryCheck {

	public DirectoryCheck(){
		
	}
	
	public String getWorkDir(){
		return System.getProperty("user.dir");
	}
	
	
	public String getHomeDir(){
		return System.getProperty("user.home");	
	}
	

		public static void main(String[] args) {
			DirectoryCheck check = new DirectoryCheck();
			String home = check.getHomeDir();
			String work = check.getWorkDir();
			System.out.println(home);
			System.out.println(work);
		}
	}
