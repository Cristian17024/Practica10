public class Roca extends Elemento {
    public Roca(Escenario e, Posicion p) {
        super(e, p);
    }

    @Override
    public char getSimbolo() {
        return 'R';
    }
}
