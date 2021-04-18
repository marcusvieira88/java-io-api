package tech.marcusvieira.datastream;

import java.io.*;

public class DataStream {

    public static void main(String[] args) throws IOException {

        try (
            DataOutputStream dataOutputStream =
            new DataOutputStream(
                new FileOutputStream("data.bin"));
        ){
            dataOutputStream.writeInt(123);
            dataOutputStream.writeFloat(123.45F);
            dataOutputStream.writeLong(789);
        }

        try (
            DataInputStream dataInputStream =
            new DataInputStream(
                new FileInputStream("data.bin"));
        ) {
            int int123 = dataInputStream.readInt();
            float float12345 = dataInputStream.readFloat();
            long long789 = dataInputStream.readLong();

            System.out.println("int123     = " + int123);
            System.out.println("float12345 = " + float12345);
            System.out.println("long789    = " + long789);
        }
    }
}
