package bourgeop.progrepartie;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bourgeop
 */
public class CopieTexte {

    public CopieTexte() {
    }

    public static void main(String[] args) {
        try {
            if (args.length != 2) {
                System.out.println("Usage: copiebinaire src dest");
                System.exit(1);
            }
            
            // testFileExists
            FileInputStream src = findInputFile(args[0]);
            FileOutputStream dest = findOutputFile(args[1]);
            
            byte[] tab = new byte[255];
            
            while(src.read(tab) > 0){
                dest.write(tab);
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
    private static FileOutputStream findOutputFile(String out) {
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
                FileOutputStream fos = new FileOutputStream(file);
                return fos;
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
    private static FileInputStream findInputFile(String in) {
        File file = new File(in);

        try {
            FileInputStream fis = new FileInputStream(file);

            return fis;
        } catch (FileNotFoundException ex) {
            System.out.println("Source file not found.");
            System.exit(1);
        }
        return null;
    }

}
