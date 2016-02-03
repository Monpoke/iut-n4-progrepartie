package stuttering;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * UDP server. To test with netcat on port 9876
 *
 * @author bourgeop
 */
public class StutteringServer {

    // PORT
    final static int DEFAULT_PORT = 9876;

    private DatagramSocket dgSocket;

    StutteringServer(int pSrv) throws IOException {
        dgSocket = new DatagramSocket(pSrv);
    }

    void go() throws IOException {
        DatagramPacket dgPacket = new DatagramPacket(new byte[255], 0);
        String str = "";

        while (true) {
            str = "";
            dgPacket.setData(new byte[255]);

            dgSocket.receive(dgPacket);
            String result = new String(dgPacket.getData());

            /**
             * Informations
             */
            System.out.println("Received: " +dgPacket.getAddress()+" on port "+dgPacket.getPort() + "\t" + result);
            
            
            
            
            
            String[] split = result.split(":");

            // no error
            if (split.length < 2) {
                str = "1Erreur: phrase manquante.";
            } // pas d'erreur
            else if (str.length() == 0) {

                // multiplicateur
                int nb = 0;
                try {
                    nb = Integer.parseInt(split[0]);
                } catch (Exception e) {
                    str = "1Erreur: multiplicateur manquant.";
                }

                String[] words = split[1].split(" ");
                
                for (String word : words) {
                    String wo = word.replace("\n", "");
                    for (int i = 0; i < nb; i++) {
                        str += wo + " ";

                    }
                }

                str = "0" + str.trim() + (str.trim().length()>0 ?".":"");
            }

            str += "\n";

            // send data
            dgPacket.setSocketAddress(dgPacket.getSocketAddress());
            byte[] bufDate = str.getBytes();

            dgPacket.setData(bufDate, 0, bufDate.length);
            dgSocket.send(dgPacket);

        }
    }

    public static void main(String[] args) throws IOException {
        System.out.println("[Sttuttering server created!]");

        new StutteringServer(args.length == 0 ? DEFAULT_PORT : Integer.parseInt(args[ 0])).go();
    }
}
