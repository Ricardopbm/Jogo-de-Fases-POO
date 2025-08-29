import java.util.ArrayList;
import java.util.Random;

public class Fase {
    private int numero;
    private Jogador jogador;
    private Objetivo objetivo;
    private ArrayList<Inimigo> inimigos;
    private float velocidadeJogador;
    private float velocidadeInimigos;
    private Random random;
    private int larguraTela;
    private int alturaTela;

    public Fase(int numero, Random random, int larguraTela, int alturaTela) {
        this.numero = numero;
        this.random = random;
        this.larguraTela = larguraTela;
        this.alturaTela = alturaTela;
        this.velocidadeJogador = 5.0f + (numero * 0.5f);
        this.velocidadeInimigos = 1.5f + (numero * 0.2f);
        criarFase();
    }

    private void criarFase() {
        int margem = 30;
        
        int jogadorX = random.nextInt(larguraTela - 2*margem) + margem;
        int jogadorY = random.nextInt(alturaTela - 2*margem) + margem;

        int objetivoX, objetivoY;
        do {
            objetivoX = random.nextInt(larguraTela - 2*margem) + margem;
            objetivoY = random.nextInt(alturaTela - 2*margem) + margem;
        } while (Math.abs(objetivoX - jogadorX) < 300 || Math.abs(objetivoY - jogadorY) < 300);

        this.jogador = new Jogador(jogadorX, jogadorY, velocidadeJogador, larguraTela, alturaTela);
        this.objetivo = new Objetivo(objetivoX, objetivoY, larguraTela, alturaTela);
        this.inimigos = new ArrayList<>();

        for (int i = 0; i < 3 + numero * 2; i++) {
            int inimigoX, inimigoY;
            do {
                inimigoX = random.nextInt(larguraTela - 2*margem) + margem;
                inimigoY = random.nextInt(alturaTela - 2*margem) + margem;
            } while (Math.sqrt(Math.pow(inimigoX - jogadorX, 2) + 
                    Math.pow(inimigoY - jogadorY, 2)) < 200);

            inimigos.add(new Inimigo(inimigoX, inimigoY, velocidadeInimigos, larguraTela, alturaTela));
        }
    }

    public void reiniciar() {
        criarFase();
    }

    public int getNumero() { return numero; }
    public Jogador getJogador() { return jogador; }
    public Objetivo getObjetivo() { return objetivo; }
    public ArrayList<Inimigo> getInimigos() { return inimigos; }
}