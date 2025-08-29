import java.awt.Rectangle;

public class Objetivo {
    private int x, y;
    private Rectangle bounds;

    public Objetivo(int x, int y, int larguraTela, int alturaTela) {
        this.x = Math.max(30, Math.min(x, larguraTela - 30));
        this.y = Math.max(30, Math.min(y, alturaTela - 30));
        this.bounds = new Rectangle(this.x - 15, this.y - 15, 30, 30);
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public Rectangle getBounds() { return bounds; }
}