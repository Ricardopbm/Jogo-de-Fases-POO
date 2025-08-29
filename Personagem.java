import java.awt.Rectangle;

public abstract class Personagem {
    protected float x, y;
    protected float velocidade;
    protected Rectangle bounds;
    protected int larguraTela;
    protected int alturaTela;

    public Personagem(float x, float y, float velocidade, int larguraTela, int alturaTela) {
        this.x = x;
        this.y = y;
        this.velocidade = velocidade;
        this.larguraTela = larguraTela;
        this.alturaTela = alturaTela;
        this.bounds = new Rectangle((int) x - 10, (int) y - 10, 20, 20);
    }

    public void move(float dx, float dy) {
        x += dx * velocidade;
        y += dy * velocidade;
        limitarAoPainel();
    }

    public void limitarAoPainel() {
        x = Math.max(10, Math.min(x, larguraTela - 10));
        y = Math.max(10, Math.min(y, alturaTela - 10));
        atualizarBounds();
    }

    protected void atualizarBounds() {
        bounds.setLocation((int) x - 10, (int) y - 10);
    }

    public int getX() { return (int) x; }
    public int getY() { return (int) y; }
    public Rectangle getBounds() { return bounds; }
}