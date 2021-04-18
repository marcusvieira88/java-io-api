package tech.marcusvieira.commandline;

import java.io.IOException;
import java.io.InputStreamReader;

public class StandardStream {

    public static void main(String[] args) throws IOException {
        InputStreamReader cin = new InputStreamReader(System.in);
        int read = cin.read();
        System.out.println(read);
    }
}
