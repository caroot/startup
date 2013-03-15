package run;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

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
		System.out.println(path);
		FileReader fr = new FileReader(path);
	    BufferedReader br = new BufferedReader(fr);
	    
	    while (i < 255 && br.readLine() != null){
		    if (linecount == 0){
		    	System.out.println(linecount);
		    	linecount++;
		    	System.out.println(linecount);
		    	}
			    else {
			    	System.out.println("filetoarray!");
			    	addresses[i] = br.readLine();
			    	
			    	i++;
			    }
	    }
	    br.close();
	    fr.close();
	}
	
	public void makeServer(){
		if (addresses[0] != null){
			System.out.println( addresses[1].toString() + " is Server!");
		}
		else {
			
		}
		
	}
		public static void main(String[] args) {
			  
		    try {
					LanScanner ls = new LanScanner(InetAddress.getLocalHost());
					ServerFinder sf = new ServerFinder();
					sf.fileToArray();
					sf.makeServer();
					
				} catch (UnknownHostException e) {
				
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
			}
	}

