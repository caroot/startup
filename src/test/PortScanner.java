package test;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Observable;
import java.io.IOException;

public class PortScanner extends Observable implements Runnable {
	private String target;
	private int fromPort = 0; // default start port number
	private int toPort = 65535; // default end port number

	public void setFromPort(int fromPort) { // setter
		this.fromPort = fromPort;
	}

	public void setToPort(int toPort) { // setter
		this.toPort = toPort;
	}

	public void setTarget(String target) { // setter
		this.target = target;
	}

	public void run() {
		InetAddress inetAddress = null;
		try {
			inetAddress = InetAddress.getByName(target);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		String hostName = inetAddress.getHostName();
		for (int port = fromPort; port <= toPort; port++) {
			try {
				Socket socket = new Socket(hostName, port);
				socket.close();
				setChanged();
				notifyObservers(port + " of " + hostName);
			} catch (IOException e) {
				// do nothing.
			}
		}
	}
}