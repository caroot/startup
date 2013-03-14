package test;
import java.net.InetAddress;
import java.net.Socket;
 
 
public class PingNative
{
  public static void main( String[] args )
  {
      // funktioniert auch mit ip's
      String hostip = "boehrsi.net";
      int port = 80;
      
      Object[] e;
      if((e = ping(hostip, port)) != null)
      {
          System.out.println("HostIP: " + e[0] + "\n" +
                               "HostName: " + e[1] + "\n" + 
                               "MilliSeconds: " + e[2]);
      }
      else
      {
          System.out.println("No Response");
      }
  }
  
  public static Object[] ping(String hostip, int port)
  {
      try 
        {
            long roundTripTime = System.nanoTime();
            InetAddress host = InetAddress.getByName(hostip);
            
            Socket sock = new Socket(host, port);
            roundTripTime = (System.nanoTime() - roundTripTime) / 1000000L;
            sock.close();
            return (new Object[]{host.getHostAddress(), host.getHostName(), roundTripTime});
        } 
        catch(Exception e) 
        {
            System.out.println(e.getMessage());
            return null;
        }
  }
}