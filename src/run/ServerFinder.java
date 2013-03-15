package run;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ServerFinder {
	
	private static final int MAX_NETSIZE = 255;
	
	private HostCheck hc = new HostCheck();
	private String [] addresses;
	
	public ServerFinder(){
		
		
		
	}

	
	public void fileToArray() throws IOException{
		int i= 0;
		int linecount = 0;
		String path = hc.setFilePath() + "IPs.txt";
		addresses = new String[MAX_NETSIZE];
		
		FileReader fr = new FileReader(path);
	    BufferedReader br = new BufferedReader(fr);
	    
	    while (i < 255 && br.readLine() != null){
	    if (linecount == 0){
	    	linecount++;
	    	}
	    else {
	    	addresses[i] = br.readLine();
	    	i++;
	    }
	    }
	}
	
	public void makeServer(){
		if (addresses[0] != null){
			System.out.println( addresses[1].toString() + " is Server!");
		}
		else {
			
		}
		
	}
}
