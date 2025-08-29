import java.util.Random;

public class GameStateManager {
    private Fase faseAtual;
    private int vidas = 3;
    private boolean gameOver = false;
    private boolean faseCompleta = false;
    private boolean jogoAcabado = false;
    private boolean emContador = true;
    private int contador = 3;
    private long tempoUltimoContador;
    private final Random random;
    private final int larguraTela;
    private final int alturaTela;
    
    public GameStateManager(Random random, int larguraTela, int alturaTela) {
        this.random = random;
        this.larguraTela = larguraTela;
        this.alturaTela = alturaTela;
        this.tempoUltimoContador = System.currentTimeMillis();
    }
    
    public void iniciarFase(int numeroFase) {
        this.faseAtual = new Fase(numeroFase, random, larguraTela, alturaTela);
        this.gameOver = false;
        this.faseCompleta = false;
        this.emContador = true;
        this.contador = 3;
        this.tempoUltimoContador = System.currentTimeMillis();
    }
    
    public void reiniciarFase() {
        iniciarFase(faseAtual.getNumero());
    }
    
    public void reiniciarJogo() {
        this.vidas = 3;
        this.jogoAcabado = false;
        iniciarFase(1);
    }
    
    public void atualizarContador() {
        long agora = System.currentTimeMillis();
        if (agora - tempoUltimoContador >= 1000) {
            contador--;
            tempoUltimoContador = agora;
            if (contador <= 0) {
                emContador = false;
            }
        }
    }
    
    public void verificarColisoes() {
        for (Inimigo inimigo : faseAtual.getInimigos()) {
            if (faseAtual.getJogador().getBounds().intersects(inimigo.getBounds())) {
                vidas--;
                if (vidas > 0) {
                    reiniciarFase();
                    return;
                } else {
                    gameOver = true;
                    jogoAcabado = true;
                    break;
                }
            }
        }
        
        if (faseAtual.getJogador().getBounds().intersects(faseAtual.getObjetivo().getBounds())) {
            faseCompleta = true;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            iniciarFase(faseAtual.getNumero() + 1);
        }
    }
    
    public Fase getFaseAtual() { return faseAtual; }
    public int getVidas() { return vidas; }
    public boolean isGameOver() { return gameOver; }
    public boolean isFaseCompleta() { return faseCompleta; }
    public boolean isJogoAcabado() { return jogoAcabado; }
    public boolean isEmContador() { return emContador; }
    public int getContador() { return contador; }
}