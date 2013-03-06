import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class LanScanner {
	private static final int TIMEOUT = 15000;
	private static Object[] addressesOld = new Object[255];
	private static Object[] addressesNew = new Object[255];
	private static int i=0;
	

//	public static void main(String[] args) {
//		try {
//			;
//			Enumeration<NetworkInterface> enumInterfaces = NetworkInterface
//					.getNetworkInterfaces();
//			while (enumInterfaces.hasMoreElements()) {
//				NetworkInterface currentInterface = enumInterfaces
//						.nextElement();
//				if (currentInterface.isLoopback() == false
//						&& currentInterface.isUp()) {
//					Enumeration<InetAddress> enumAdresses = currentInterface
//							.getInetAddresses();
//					while (enumAdresses.hasMoreElements()) {
//						InetAddress currentAddress = enumAdresses.nextElement();
//						new LanScanner(currentAddress);
//						i++;
//					}
//				}
//			}
//			 Wenn man davon ausgeht, dass nur eine Netzwerkkarte aktiv ist,
//			 dann nehmen wir die Kurzfassung:
//			System.out.print(InetAddress.getLocalHost());
//			
//			new LanScanner(InetAddress.getLocalHost());
//			
//		} catch (SocketException e) {
//			e.printStackTrace();
//		}
//	}

		public static void main(String[] args) {
			try {
				new LanScanner(InetAddress.getLocalHost());
				
//				sortArray(addressesOld);
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
	}

		
	private LanScanner(InetAddress ia) {
		String[] sAddress = ia.getHostAddress().split("[.]");
		String lanAddress = sAddress[0] + "." + sAddress[1] + "." + sAddress[2]
				+ ".";
		for (short s = 1; s < 255; s++) {
			new AddressScanner(lanAddress + s);
		}
		
	}	
	private static void sortArray(Object[] array){
			
			java.util.Arrays.sort( array );
			
			  for (int n = 0; n < array.length; n++) {
				  addressesNew[n] = array[n];
				  System.out.println(addressesNew[n]);
			  }
		}
	

	
	
	
	private class AddressScanner implements Runnable {
		private String sInetAddress = null;

		private AddressScanner(String inetAddress) {
			this.sInetAddress = inetAddress;
			new Thread(this).start();
		}

		public void run() {
			try {
				InetAddress ia = InetAddress.getByName(this.sInetAddress);
				if (ia.isReachable(TIMEOUT)
						|| !ia.getCanonicalHostName().equalsIgnoreCase(
								this.sInetAddress)
						|| !ia.getHostName()
								.equalsIgnoreCase(this.sInetAddress)) {
					
					addressesOld[i] = this.sInetAddress;
					
					
//					System.out.println("Reached " + this.sInetAddress + "("
//							+ ia.getCanonicalHostName() + ")");

				System.out.println(addressesOld[i]);
				}
				
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		

	}
}
