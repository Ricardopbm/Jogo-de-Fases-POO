import java.awt.*;
import javax.swing.JButton;

public class GameRenderer {
    private final int larguraTela;
    private final int alturaTela;
    private final Font fontePadrao = new Font("Arial", Font.BOLD, 24);
    private final Font fonteContador = new Font("Arial", Font.BOLD, 120);
    private final Font fonteGameOver = new Font("Arial", Font.BOLD, 80);
    
    public GameRenderer(int larguraTela, int alturaTela) {
        this.larguraTela = larguraTela;
        this.alturaTela = alturaTela;
    }
    
    public void render(Graphics g, GameStateManager state, JButton botaoReiniciar) {
        renderizarFundo(g);
        renderizarObjetivo(g, state);
        renderizarPersonagens(g, state);
        renderizarUI(g, state);
        
        if (state.isEmContador() && state.getContador() > 0) {
            renderizarContador(g, state.getContador());
        }
        
        if (state.isJogoAcabado()) {
            renderizarGameOver(g, botaoReiniciar);
        }
    }
    
    private void renderizarFundo(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, larguraTela, alturaTela);
    }
    
    private void renderizarObjetivo(Graphics g, GameStateManager state) {
        g.setColor(Color.GREEN);
        Objetivo objetivo = state.getFaseAtual().getObjetivo();
        g.fillRect(objetivo.getX() - 15, objetivo.getY() - 15, 30, 30);
    }
    
    private void renderizarPersonagens(Graphics g, GameStateManager state) {
        Fase fase = state.getFaseAtual();
        
        // Renderizar jogador
        g.setColor(Color.BLUE);
        Jogador jogador = fase.getJogador();
        g.fillRect(jogador.getX() - 10, jogador.getY() - 10, 20, 20);
        
        // Renderizar inimigos
        g.setColor(Color.RED);
        for (Inimigo inimigo : fase.getInimigos()) {
            g.fillRect(inimigo.getX() - 10, inimigo.getY() - 10, 20, 20);
        }
    }
    
    private void renderizarUI(Graphics g, GameStateManager state) {
        g.setColor(Color.WHITE);
        g.setFont(fontePadrao);
        g.drawString("Fase: " + state.getFaseAtual().getNumero(), 20, 30);
        g.drawString("Vidas: " + state.getVidas(), 20, 60);
    }
    
    private void renderizarContador(Graphics g, int contador) {
        g.setColor(Color.YELLOW);
        g.setFont(fonteContador);
        String texto = String.valueOf(contador);
        int textoX = (larguraTela - g.getFontMetrics().stringWidth(texto)) / 2;
        int textoY = alturaTela / 2;
        g.drawString(texto, textoX, textoY);
    }
    
    private void renderizarGameOver(Graphics g, JButton botaoReiniciar) {
        g.setColor(new Color(255, 0, 0, 150));
        g.fillRect(0, 0, larguraTela, alturaTela);
        
        g.setColor(Color.RED);
        g.setFont(fonteGameOver);
        String texto = "GAME OVER";
        int textoX = (larguraTela - g.getFontMetrics().stringWidth(texto)) / 2;
        g.drawString(texto, textoX, alturaTela / 2);
        
        botaoReiniciar.setVisible(true);
    }
}