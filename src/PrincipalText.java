import java.util.Random;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        String alfabeto = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        Random r = new Random();
        char x = alfabeto.charAt(r.nextInt(alfabeto.length()));
        System.out.println("Letra sorteada: "+x);


    }
}
