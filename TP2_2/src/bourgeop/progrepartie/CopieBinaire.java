package bourgeop.progrepartie;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bourgeop
 */
public class CopieBinaire {

    public CopieBinaire() {
    }

    public static void main(String[] args) {
        try {
            if (args.length != 2) {
                System.out.println("Usage: copiebinaire src dest");
                System.exit(1);
            }
            
            // testFileExists
            BufferedReader src = findInputFile(args[0]);
            PrintWriter dest = findOutputFile(args[1]);
            
            
            /**
             * 
             */
            String line;
            while((line=src.readLine()) != null){
                dest.println(line);
            }
            
            
            src.close();
            dest.close();
            
            System.out.println("Successful");
            
        } catch (IOException ex) {
            System.out.println("");
            System.exit(1);
        }
        
        
        
    }

    /**
     * Output stream
     * @param out
     * @return 
     */
    private static PrintWriter findOutputFile(String out) {
        File file = new File(out);

        if (file.exists()) {
            System.out.println("Destination file already exists.");
            System.exit(1);
        } else {
            try {
                if (!file.createNewFile()) {
                    System.out.println("Can't create destination file.");
                    System.exit(1);
                }
            } catch (IOException ex) {
                System.out.println("Can't create destination file.");
                System.exit(1);
            }

            try {
                PrintWriter pw = new PrintWriter(file);
                return pw;
            } catch (FileNotFoundException ex) {
                System.out.println("Can't read destination file.");
                System.exit(1);
            }

        }

        return null;
    }

    /**
     * Gets input
     *
     * @param in
     * @return
     */
    private static BufferedReader findInputFile(String in) {
        File file = new File(in);

        try {
            BufferedReader fis = new BufferedReader(new FileReader(file));

            return fis;
        } catch (FileNotFoundException ex) {
            System.out.println("Source file not found.");
            System.exit(1);
        }
        return null;
    }

}
