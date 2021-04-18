package tech.marcusvieira.filebytestream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CopyBytes {
    public static void main(String[] args) throws IOException {

// EXAMPLE WITH TRY CATCH RESOURCE
//        try (
//            FileInputStream in = new FileInputStream("inputfile.txt");
//            FileOutputStream out = new FileOutputStream("outputfile.txt")
//        ) {
//            int c;
//
//            while ((c = in.read()) != -1) {
//                out.write(c);
//            }
//        }

        FileInputStream in = null;
        FileOutputStream out = null;

        try {
            in = new FileInputStream("inputfile.txt");
            out = new FileOutputStream("outputfile.txt");
            int c;

            //READ method returns the next byte of data, or -1 if the end of the file is reached.
            while ((c = in.read()) != -1) {
                out.write(c);
            }
        } finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }
}
