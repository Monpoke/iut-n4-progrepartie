
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * UDP server. To test with netcat on port 9876
 *
 * @author bourgeop
 */
public class ServeurUDP {

    // PORT
    final static int DEFAULT_PORT = 9876;

    
    
    private DatagramSocket dgSocket;

    ServeurUDP(int pSrv) throws IOException {
        dgSocket = new DatagramSocket(pSrv);
    }

    void go() throws IOException {
        DatagramPacket dgPacket = new DatagramPacket(new byte[255], 0);
        String str;
        while (true) {
            dgPacket.setData(new byte[255]);

            dgSocket.receive(dgPacket);
            System.out.println("Datagram received from " + dgPacket.getSocketAddress());
            
            
            System.out.println("Longueur="+dgPacket.getLength());
            System.out.println("Result=" + (new String(dgPacket.getData())));
            dgPacket.setSocketAddress(dgPacket.getSocketAddress());
            str ="Bonjour" + "\n";
            byte[] bufDate = str.getBytes();
                        
            
            dgPacket.setData(bufDate, 0, bufDate.length);
            dgSocket.send(dgPacket);
            
            
        }
    }

    public static void main(String[] args) throws IOException {

        new ServeurUDP(args.length == 0 ? DEFAULT_PORT : Integer.parseInt(args[ 0])).go();
    }
}
