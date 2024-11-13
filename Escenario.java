import java.util.ArrayList;

public class Escenario {
    private Elemento[][] campoDeBatalla;

    public Escenario() {
        this.campoDeBatalla = new Elemento[10][10];
    }

   
    public void agregarElemento(Elemento e) {
        Posicion pos = e.getPosicion();
        campoDeBatalla[pos.getRenglon()][pos.getColumna()] = e;
    }

    public Elemento obtenerElemento(Posicion p) {
        return campoDeBatalla[p.getRenglon()][p.getColumna()];
    }

    public void destruirElementos(Posicion p, int radio) {
        ArrayList<Elemento> destruidos = new ArrayList<>();

        for (int i = 0; i < campoDeBatalla.length; i++) {
            for (int j = 0; j < campoDeBatalla[i].length; j++) {
                Elemento e = campoDeBatalla[i][j];
                if (e != null && e.getPosicion().distancia(p) <= radio && e instanceof Destruible) {
                    destruidos.add(e);
                }
            }
        }

        for (Elemento e : destruidos) {
            System.out.println(((Destruible) e).destruir());
            campoDeBatalla[e.getPosicion().getRenglon()][e.getPosicion().getColumna()] = null;
        }
    }

    // para sobreescribir el escenario
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < campoDeBatalla.length; i++) {
            for (int j = 0; j < campoDeBatalla[i].length; j++) {
                if (campoDeBatalla[i][j] == null) {
                    sb.append("0 ");
                } else {
                    sb.append(campoDeBatalla[i][j].getSimbolo()).append(" ");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    // limpia la posición en el campo de batalla
    public void removerElemento(Posicion p) {
        campoDeBatalla[p.getRenglon()][p.getColumna()] = null;
    }
}
