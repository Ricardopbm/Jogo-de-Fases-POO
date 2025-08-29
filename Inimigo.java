public class Inimigo extends Personagem {
    public Inimigo(float x, float y, float velocidade, int larguraTela, int alturaTela) {
        super(x, y, velocidade, larguraTela, alturaTela);
    }

    public void perseguirJogador(int jogadorX, int jogadorY) {
        float dx = jogadorX - this.getX();
        float dy = jogadorY - this.getY();
        float distancia = (float) Math.sqrt(dx * dx + dy * dy);

        if (distancia > 0) {
            dx = (dx / distancia) * velocidade;
            dy = (dy / distancia) * velocidade;
            move(dx, dy);
        }
    }

    public void setPosicao(int x, int y) {
        this.x = x;
        this.y = y;
        atualizarBounds();
    }
}