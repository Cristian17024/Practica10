import java.util.ArrayList;

public class Escenario {
    private Elemento[][] campoDeBatalla;

    public Escenario() {
        this.campoDeBatalla = new Elemento[10][10];
    }

    public void agregarElemento(Elemento e) {
        campoDeBatalla[e.getPosicion().getRenglon()][e.getPosicion().getColumna()] = e;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
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
}
