public class Extraterrestre extends Personaje {
    public Extraterrestre(String nombre, Escenario e, Posicion p) {
        super(nombre, e, p);
    }

    @Override
    public String destruir() {
        return nombre + " destruido";
    }

    @Override
    public char getSimbolo() {
        return 'E';
    }
}
