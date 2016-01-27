
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
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
            //      System.out.println(java.net.Inet4Address.getLoopbackAddress());
            //     System.out.println(java.net.Inet4Address.getLocalHost().toString());
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            
            /**
             * Interfaces
             */
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface nextElement = networkInterfaces.nextElement();
                
                System.out.println("+++ " + nextElement.getDisplayName() + " +++");
                
                /**
                 * Addresses
                 */
                Enumeration<InetAddress> address = nextElement.getInetAddresses();
                while(address.hasMoreElements()){
                    InetAddress addr = address.nextElement();
                    System.out.println("\t"+addr.toString() + "\t"+ addr.getHostName());
                }
                System.out.println();
            }
            
            
            
        } catch (SocketException ex) {
            Logger.getLogger(LocalIPS.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
