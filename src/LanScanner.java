import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
 
public class LanScanner {
    private static final int TIMEOUT = 15000;
 
    public static void main(String[] args) {
        try {
            Enumeration<NetworkInterface> enumInterfaces = NetworkInterface.getNetworkInterfaces();
            while (enumInterfaces.hasMoreElements()) {
                NetworkInterface currentInterface = enumInterfaces.nextElement();
                if (currentInterface.isLoopback() == false && currentInterface.isUp()) {
                    Enumeration<InetAddress> enumAdresses = currentInterface.getInetAddresses();
                    while (enumAdresses.hasMoreElements()) {
                        InetAddress currentAddress = enumAdresses.nextElement();
                        new LanScanner(currentAddress);
                    }
                }
            }
            // Wenn man davon ausgeht, dass nur eine Netzwerkkarte aktiv ist, dann nehmen wir die Kurzfassung:
            // new LanScanner(InetAddress.getLocalHost());
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
 
    private LanScanner(InetAddress ia) {
        String[] sAddress = ia.getHostAddress().split("[.]");
        String lanAddress = sAddress[0] + "." + sAddress[1] + "." + sAddress[2] + ".";
        for (short s = 1; s < 255; s++) {
            new AddressScanner(lanAddress + s);
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
                if(ia.isReachable(TIMEOUT) || !ia.getCanonicalHostName().equalsIgnoreCase(this.sInetAddress) || !ia.getHostName().equalsIgnoreCase(this.sInetAddress)) {
                    System.out.println("Reached " + this.sInetAddress + "(" + ia.getCanonicalHostName() + ")");
                }
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

