import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;

public class InputHandler {
    private final boolean[] teclasPressionadas = new boolean[256];
    
    public InputHandler() {
        KeyboardFocusManager.getCurrentKeyboardFocusManager()
            .addKeyEventDispatcher(this::processarEventoTecla);
    }
    
    private boolean processarEventoTecla(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode >= 0 && keyCode < teclasPressionadas.length) {
            teclasPressionadas[keyCode] = e.getID() == KeyEvent.KEY_PRESSED;
            
            if (keyCode == KeyEvent.VK_ESCAPE && e.getID() == KeyEvent.KEY_PRESSED) {
                System.exit(0);
            }
        }
        return false;
    }
    
    public boolean isTeclaPressionada(int keyCode) {
        if (keyCode >= 0 && keyCode < teclasPressionadas.length) {
            return teclasPressionadas[keyCode];
        }
        return false;
    }
    
    public void limparTeclas() {
        for (int i = 0; i < teclasPressionadas.length; i++) {
            teclasPressionadas[i] = false;
        }
    }
}