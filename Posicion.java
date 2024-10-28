public class Posicion {
    private int renglon;
    private int columna;

    public Posicion(int renglon, int columna) {
        this.renglon = renglon;
        this.columna = columna;
    }

    public int getRenglon() {
        return renglon;
    }

    public int getColumna() {
        return columna;
    }

    public int distancia(Posicion otra) {
        return Math.max(Math.abs(this.renglon - otra.renglon), Math.abs(this.columna - otra.columna));
    }
}
