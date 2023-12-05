import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.*;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class JogoDePalavras {
    private HashSet<String> words;
    private char letraSorteada;
    private boolean jogoAtivo;
    private HashSet<String> palavrasInformadas;

    public JogoDePalavras() {
        words = new HashSet<>();
        palavrasInformadas = new HashSet<>();
        jogoAtivo = true;
    }

    public void carregarPalavras(String arquivo) {
        try {
            Scanner scanner = new Scanner(new File(arquivo));
            while (scanner.hasNext()) {
                words.add(scanner.next().toLowerCase());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado: " + arquivo);
        }
    }

    public void SorteioDaLetra() {
        letraSorteada = (char) ('a' + Math.random() * ('z' - 'a' + 1));
        System.out.println("A letra sorteada é: " + letraSorteada);
    }

    public boolean VerificarWordBD(String palavra, List<String> bancoDeDados) {
        return bancoDeDados.contains(palavra.toLowerCase());
    }

    public void mostrarResultados() {
        System.out.println("Jogo finalizado! Você informou " + palavrasInformadas.size() + " palavras diferentes válidas durante este jogo.");
        System.out.println("As palavras que você informou são: " + palavrasInformadas);
    }

    public void inicioDoJogo() {
        try {
            List<String> bancoDeDados = null;
            try {
                bancoDeDados = Files.readAllLines(Paths.get("BancoDePalavras.txt"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            words.addAll(bancoDeDados);

            SorteioDaLetra();
            Scanner scanner = new Scanner(System.in);

            // Adicionando temporizador para 30 segundos
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                public void run() {
                    System.out.println("Tempo esgotado! Jogo finalizado.");
                    jogoAtivo = false;
                    mostrarResultados();
                }
            }, 30000);

            while (jogoAtivo) {
                System.out.println("Digite uma palavra que começa com a letra " + letraSorteada + " ou 'sair' para terminar o jogo:");
                String palavra = scanner.nextLine().toLowerCase();

                if (palavra.equals("sair")) {
                    jogoAtivo = false;
                    break;
                }

                if (palavra.length() >= 4 && palavra.charAt(0) == letraSorteada && VerificarWordBD(palavra, bancoDeDados)) {
                    if (palavrasInformadas.contains(palavra)) {
                        System.out.println("Você já informou essa palavra durante este jogo!");
                    } else {
                        System.out.println("Palavra válida! Adicionada ao banco de dados.");
                        words.add(palavra);
                        palavrasInformadas.add(palavra);
                    }
                } else {
                    System.out.println("Palavra inválida!");
                }
            }

            // Cancelando o temporizador após o jogo
            timer.cancel();
            mostrarResultados();
        } finally {
            // Recursos de limpeza, se necessário
        }
    }

    public static void main(String[] args) {
        JogoDePalavras jogo = new JogoDePalavras();
        jogo.inicioDoJogo();
    }
}
