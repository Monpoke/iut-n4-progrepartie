
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bourgeop
 */
public class LocalIPS {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        try {
            System.out.println(java.net.Inet4Address.getLocalHost().toString());
        } catch (UnknownHostException ex) {
            System.out.println("truuc");
        }
        
    }
    
}
