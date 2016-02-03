package stuttering;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * UDP client.
 *
 * @author bourgeop
 */
public class StutteringUDP {

    static int port = 9876;
    static String host = "";

    private DatagramSocket dgSocket;

    public StutteringUDP() {

        try {

            // adresse 
            InetAddress ip = InetAddress.getByName(host);

            dgSocket = new DatagramSocket();

            // on ecoute en parallele
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {

                    DatagramPacket dp = new DatagramPacket(new byte[255], 0);

                    while (true) {
                        try {
                            dp.setData(new byte[255]);
                            dgSocket.receive(dp);
                            String re = new String(dp.getData());
                            
                            System.out.println(re.substring(1));
                            

                        } catch (IOException ex) {
                            Logger.getLogger(StutteringUDP.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            });
            t.start();

            // creation packet
            DatagramPacket packet = new DatagramPacket(new byte[0], 0, ip, port);

            while (true) {

                // on écrit
                String message = (((int)new GregorianCalendar().get(Calendar.SECOND))% 3) + ":" + "bonjour le monde";

                // on set, on envoie
                byte[] by = message.getBytes();
                packet.setData(by, 0, by.length);
                dgSocket.send(packet);

                System.out.println("Paquet envoye: " + message);

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(StutteringUDP.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } catch (SocketException ex) {
            Logger.getLogger(StutteringUDP.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(StutteringUDP.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(StutteringUDP.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void main(String[] argvs) {

        if (argvs.length == 2) {
            try {
                port = Integer.parseInt(argvs[1]);
            } catch (Exception e) {
                System.out.println("Port invalide");
                System.exit(1);
            }
        }
        if (argvs.length >= 1) {
            host = argvs[0];
        }

        System.out.println("Connecting to " + host + " on port " + port);

        new StutteringUDP();
    }

}
