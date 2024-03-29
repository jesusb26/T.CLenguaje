package tclenguaje;

import Util.seed.ListaCD;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TCLenguaje {

    ListaCD<SNoTerminales> SNT = new ListaCD();
    ListaCD<String> ST = new ListaCD();
    SNoTerminales sInicial;

    public TCLenguaje() {
        sInicial = null;
    }

    public String agregarNoTerminal(String simbolo, String reglas) throws RuntimeException {
        if (simbolo.isEmpty()) {
            throw new RuntimeException("debe definir el simbolo no terminal");
        }
        if (reglas.isEmpty()) {
            throw new RuntimeException("debe definir las reglas de producción");
        }
        for (SNoTerminales s : SNT) {
            if (s.getSimbolo().equals(simbolo)) {
                throw new RuntimeException("El símbolo no terminal ya existe");
            }
        }
        SNoTerminales s = new SNoTerminales(simbolo, reglas);
        String noterminal = s.toString();
        SNT.insertarFinal(s);
        return noterminal;
    }

    public void agregarTerminales(String simbolo) {
        for (String s : ST) {
            if (s.equals(simbolo)) {
                throw new RuntimeException("El símbol terminal ya existe");
            }
        }
        ST.insertarFinal(simbolo);
    }

    public void simboloInicial(String simbolo) throws RuntimeException {
        boolean existe = false;
        for (SNoTerminales s : SNT) {
            if (s.getSimbolo().equals(simbolo)) {
                this.sInicial = s;
                existe = true;
            }
        }
        if (existe == false) {
            throw new RuntimeException("El simbolo no terminal no existe");
        }
    }

    public String generarPalabras() throws RuntimeException {
        if (sInicial == null) {
            throw new RuntimeException("Debe definir un símbolo inicial");
        }
        int contador = 0;
        String palabras = "";
        Set<String> palabrasGeneradasSet = new HashSet<>(); // Usar un Set para evitar palabras duplicadas
        List<String> palabrasGeneradas = generarPalabrasAux(sInicial.getSimbolo(), new ArrayList<>(), contador);
        System.out.println(palabrasGeneradas.size());
        for (String palabra : palabrasGeneradas) {
            palabrasGeneradasSet.add(palabra); // Agregar palabras al conjunto
        }
        for (String palabra : palabrasGeneradasSet) { // Iterar sobre el conjunto para generar la cadena final
            palabras += palabra + ",  ";
        }
        return palabras + "...";
    }

    private List<String> generarPalabrasAux(String simbolo, List<String> simbolosProcesados, int contador) {

        List<String> palabras = new ArrayList<>();

        if (simbolosProcesados.contains(sInicial.getSimbolo())) {
            contador++;
        }
        if (contador >= 3) {
            return palabras;
        }
        // Agregar el símbolo actual a la lista de símbolos procesados
        simbolosProcesados.add(simbolo);
        // Verificar si el límite de palabras generadas ha sido alcanzado

        // Verificar si el símbolo es terminal
        if (ST.containTo(simbolo)) {
            palabras.add(simbolo);
            return palabras;
        }
        // Buscar el símbolo no terminal en la lista de símbolos no terminales
        for (SNoTerminales s : SNT) {
            if (s.getSimbolo().equals(simbolo)) {
                // Obtener las reglas de producción de este símbolo no terminal
                String[] reglas = s.getReglas().split("\\|"); // Dividir las reglas por el símbolo de alternancia "|"
                // Iterar sobre cada regla
                for (String regla : reglas) {
                    // Dividir la regla en símbolos
                    String[] partes = regla.trim().split(" "); // Dividir las partes de la regla por espacios
                    // Inicializar una lista para almacenar las palabras generadas para esta regla
                    List<String> palabrasRegla = new ArrayList<>();
                    palabrasRegla.add(""); // Agregar una cadena vacía como punto de partida
                    // Generar palabras para cada símbolo de la regla
                    for (String parte : partes) {
                        List<String> nuevasPalabras = new ArrayList<>();
                        for (String palabraParcial : palabrasRegla) {
                            // Verificar si la parte actual es un símbolo terminal
                            if (ST.containTo(parte)) {
                                // Si es terminal, simplemente agregamos el símbolo a la palabra parcial
                                nuevasPalabras.add(palabraParcial + parte);
                            } else {
                                // Si es no terminal, generamos palabras recursivamente
                                List<String> palabrasGeneradasParaParte = generarPalabrasAux(parte, new ArrayList<>(simbolosProcesados), contador);
                                for (String palabraGenerada : palabrasGeneradasParaParte) {
                                    nuevasPalabras.add(palabraParcial + palabraGenerada);
                                }
                            }
                        }
                        palabrasRegla = nuevasPalabras;
                    }

                    // Agregar todas las palabras generadas para esta regla a la lista principal de palabras
                    palabras.addAll(palabrasRegla);
                    // Verificar si se alcanzó el límite de palabras generadas

                }
                break;
            }
        }
        return palabras;
    }

//    public static void main(String[] args) {
//        // Crear una instancia de la clase TCLenguaje
//        TCLenguaje gramatica = new TCLenguaje();
//
//        // Definir símbolos no terminales y reglas
//        gramatica.agregarNoTerminal("B", "H 1 C | D E 2 3 | C 2 1 | 1");
//        gramatica.agregarNoTerminal("C", "D H 1 | H | D 1 2 | 2");
//        gramatica.agregarNoTerminal("D", "C 1 C 2 | 2");
//        gramatica.agregarNoTerminal("E", "1 2 C | C D 1 | 1");
//        gramatica.agregarNoTerminal("F", "C 1 2 | D H 3 4");
//        gramatica.agregarNoTerminal("H","C 2");
//
////         Definir símbolos terminales
//        gramatica.agregarTerminales("1");
//        gramatica.agregarTerminales("2");
//        gramatica.agregarTerminales("3");
//        gramatica.agregarTerminales("4");
//
//        // Definir símbolo inicial
//        gramatica.simboloInicial("B");
//
//        // Generar palabras y mostrarlas en la consola
//        System.out.println(gramatica.generarPalabras());
//
//    }
}
