import javax.swing.*;
import java.awt.*;

public class JogoDeFases extends JFrame {
    private GamePanel gamePanel;

    public JogoDeFases() {
        setTitle("Jogo de Fases");
        setUndecorated(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        gamePanel = new GamePanel(screenSize.width, screenSize.height);
        add(gamePanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new JogoDeFases();
        });
    }
}