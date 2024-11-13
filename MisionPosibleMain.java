import java.io.*;
import java.util.Scanner;

public class MisionPosibleMain {
    public static void main(String[] args) {
        Escenario escenario = new Escenario();
        Scanner scanner = new Scanner(System.in);

        String archivoConfiguracion = "C:/Users/ISAI LEDESMA MELENA/Desktop/Poo/Laboratorio/Practica 11/configuracion.txt";

        // Lee la configuración inicial desde el archivo
        cargarEscenarioDesdeArchivo(escenario, archivoConfiguracion);

        // Mostrar estado inicial del escenario
        System.out.println("Estado inicial del escenario:");
        System.out.println(escenario);

        // Lee instrucción para detonar una bomba
        System.out.println("Ingrese la posición de la bomba que desea detonar (renglón y columna):");
        int renglon = scanner.nextInt();
        int columna = scanner.nextInt();
        Posicion posicionBomba = new Posicion(renglon, columna);

        Elemento elemento = escenario.obtenerElemento(posicionBomba);
        if (elemento instanceof Bomba) {
            ((Bomba) elemento).explotar();
        } else {
            System.out.println("No hay una bomba en esa posición.");
        }

        // Mostrar estado del escenario tras la explosión
        System.out.println("Estado del escenario después de la explosión:");
        System.out.println(escenario);

        // Guarda el estado actual en el archivo de configuración
        guardarEscenarioEnArchivo(escenario, archivoConfiguracion);
    }

    private static void cargarEscenarioDesdeArchivo(Escenario escenario, String archivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(" ");
                String tipoElemento = partes[0];
                int renglon = Integer.parseInt(partes[1]);
                int columna = Integer.parseInt(partes[2]);
                Posicion posicion = new Posicion(renglon, columna);

                switch (tipoElemento) {
                    case "Roca":
                        escenario.agregarElemento(new Roca(escenario, posicion));
                        break;
                    case "Extraterrestre":
                        escenario.agregarElemento(new Extraterrestre("Alien", escenario, posicion));
                        break;
                    case "Terricola":
                        escenario.agregarElemento(new Terricola("Ripley", escenario, posicion));
                        break;
                    case "Bomba":
                        int radio = Integer.parseInt(partes[3]);
                        escenario.agregarElemento(new Bomba(escenario, posicion, radio));
                        break;
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    private static void guardarEscenarioEnArchivo(Escenario escenario, String archivo) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    Elemento elemento = escenario.obtenerElemento(new Posicion(i, j));
                    if (elemento != null) {
                        if (elemento instanceof Bomba) {
                            Bomba bomba = (Bomba) elemento;
                            bw.write("Bomba " + i + " " + j + " " + bomba.getRadio() + "\n");
                        } else if (elemento instanceof Extraterrestre) {
                            bw.write("Extraterrestre " + i + " " + j + "\n");
                        } else if (elemento instanceof Terricola) {
                            bw.write("Terricola " + i + " " + j + "\n");
                        } else if (elemento instanceof Roca) {
                            bw.write("Roca " + i + " " + j + "\n");
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error al guardar el archivo: " + e.getMessage());
        }
    }
}
