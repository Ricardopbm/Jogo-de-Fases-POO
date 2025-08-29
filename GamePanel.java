import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;

public class GamePanel extends JPanel implements Runnable {
    private final GameStateManager stateManager;
    private final InputHandler inputHandler;
    private final GameRenderer renderer;
    private final JButton botaoReiniciar;
    
    public GamePanel(int larguraTela, int alturaTela) {
        setLayout(null);
        this.stateManager = new GameStateManager(new Random(), larguraTela, alturaTela);
        this.inputHandler = new InputHandler();
        this.renderer = new GameRenderer(larguraTela, alturaTela);
        
        this.botaoReiniciar = new JButton("Jogar Novamente");
        this.botaoReiniciar.setBounds((larguraTela - 200) / 2, 600, 200, 50);
        this.botaoReiniciar.addActionListener(e -> {
            stateManager.reiniciarJogo();
            botaoReiniciar.setVisible(false);
        });
        this.botaoReiniciar.setVisible(false);
        add(this.botaoReiniciar);
        
        stateManager.iniciarFase(1);
        
        new Thread(this).start();
    }
    
    @Override
    public void run() {
        while (true) {
            if (!stateManager.isGameOver() && !stateManager.isFaseCompleta()) {
                atualizar();
            }
            repaint();
            try {
                Thread.sleep(16); 
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
    
    private void atualizar() {
        if (stateManager.isEmContador()) {
            stateManager.atualizarContador();
            return;
        }
        
        float dx = 0, dy = 0;
        if (inputHandler.isTeclaPressionada(KeyEvent.VK_W)) dy -= 1;
        if (inputHandler.isTeclaPressionada(KeyEvent.VK_S)) dy += 1;
        if (inputHandler.isTeclaPressionada(KeyEvent.VK_A)) dx -= 1;
        if (inputHandler.isTeclaPressionada(KeyEvent.VK_D)) dx += 1;
        
        if (dx != 0 || dy != 0) {
            stateManager.getFaseAtual().getJogador().move(dx, dy);
        }
        
        for (Inimigo inimigo : stateManager.getFaseAtual().getInimigos()) {
            inimigo.perseguirJogador(
                stateManager.getFaseAtual().getJogador().getX(),
                stateManager.getFaseAtual().getJogador().getY()
            );
        }
        
        stateManager.verificarColisoes();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        renderer.render(g, stateManager, botaoReiniciar);
    }
}