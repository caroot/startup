 
import java.util.Observable;
import java.util.Observer;
 
public class ObserverNotifier implements Observer {
    private String resp;
    public void update (Observable obj, Object arg) {
        if (arg instanceof String) {
            resp = (String) arg;
            System.out.println("\n Port No "+ resp +" is accessible.");
        }
    }
}