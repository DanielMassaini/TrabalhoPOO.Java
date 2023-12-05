import java.util.Random;

public class SorteioDaLetra {

    private static final String ALFABETO = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public char sortearLetra() {
        Random random = new Random();
        return ALFABETO.charAt(random.nextInt(ALFABETO.length()));
    }
}
