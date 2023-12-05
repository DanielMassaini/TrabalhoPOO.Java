import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

public class JogoPalavras {
    private HashSet<String> palavras;
    private char letraSorteada;
    private boolean jogoAtivo;

    public JogoPalavras() {
        palavras = new HashSet<>();
        jogoAtivo = true;
    }

    public void carregarPalavras(String arquivo) {
        try {
            Scanner scanner = new Scanner(new File(arquivo));
            while (scanner.hasNext()) {
                palavras.add(scanner.next().toLowerCase());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado: " + arquivo);
        }
    }

    public void sortearLetra() {
        Random random = new Random();
        letraSorteada = (char) ('a' + random.nextInt('z' - 'a' + 1));
        System.out.println("A letra sorteada é: " + letraSorteada);
    }

    public void encerrarJogo() {
        jogoAtivo = false;
    }

    public void iniciarJogo() {
        carregarPalavras("pexe.txt");
        sortearLetra();
        Scanner scanner = new Scanner(System.in);

        while (jogoAtivo) {
            System.out.println("Digite uma palavra que começa com a letra " + letraSorteada + " ou 'sair' para terminar o jogo:");
            String palavra = scanner.nextLine().toLowerCase();

            if (palavra.equals("sair")) {
                encerrarJogo();
            } else if (palavra.length() >= 4 && palavra.charAt(0) == letraSorteada) {
                if (palavras.contains(palavra)) {
                    System.out.println("Você já informou essa palavra!");
                } else {
                    palavras.add(palavra);
                }
            } else {
                System.out.println("Palavra inválida!");
            }
        }

        System.out.println("Jogo finalizado! Você informou " + palavras.size() + " palavras diferentes.");
        System.out.println("As palavras que você informou são: " + palavras);
    }

    public static void main(String[] args) {
        JogoPalavras jogo = new JogoPalavras();
        jogo.iniciarJogo();
    }
}
