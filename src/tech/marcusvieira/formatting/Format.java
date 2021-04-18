package tech.marcusvieira.formatting;

public class Format {
    public static void main(String[] args) {
        int i = 425;
        double r = Math.sqrt(i);

        System.out.format("The square root of %d is %f.%n", i, r);

        System.out.format("%f, %1$+020.10f %n", Math.PI);
    }
}