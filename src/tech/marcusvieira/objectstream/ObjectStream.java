package tech.marcusvieira.objectstream;

import java.io.*;

public class ObjectStream {

    public static class Person implements Serializable {
        public String name = null;
        public int    age  =   0;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        ObjectOutputStream objectOutputStream =
            new ObjectOutputStream(new FileOutputStream("person.bin"));

        Person person = new Person();
        person.name = "Marcus Vieira";
        person.age  = 32;

        objectOutputStream.writeObject(person);
        objectOutputStream.close();


        ObjectInputStream objectInputStream =
            new ObjectInputStream(new FileInputStream("person.bin"));

        Person personRead = (Person) objectInputStream.readObject();

        objectInputStream.close();

        System.out.println(personRead.name);
        System.out.println(personRead.age);
    }
}
