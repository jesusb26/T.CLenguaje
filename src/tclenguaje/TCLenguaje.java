package tclenguaje;

import Util.seed.ListaCD;
import java.util.ArrayList;
import java.util.List;



public class TCLenguaje {
    ListaCD<SNoTerminales> SNT=new ListaCD();
    ListaCD<String> ST=new ListaCD();
    SNoTerminales sInicial;
    

    public TCLenguaje() {
        sInicial=new SNoTerminales();
    }
    
    public void agregarNoTerminal(String simbolo,String reglas){
        SNoTerminales s=new SNoTerminales(simbolo,reglas);
        SNT.insertarFinal(s);
    }
    
    public void agregarTerminales(String simbolo){
    ST.insertarFinal(simbolo);
    }
    
    public void simboloInicial(String simbolo){
    for(SNoTerminales s: SNT){
    if(s.getSimbolo().equals(simbolo))
        this.sInicial=s;
    }
    }
    
    public void generarPalabras() {
    List<String> palabrasGeneradas = generarPalabrasAux(sInicial.getSimbolo(), new ArrayList<>());
    System.out.println("Palabras formadas: " + palabrasGeneradas.size());
    for (String palabra : palabrasGeneradas) {
        System.out.println(palabra);
    }
}

private List<String> generarPalabrasAux(String simbolo, List<String> simbolosProcesados) {
    List<String> palabras = new ArrayList<>();

    // Verificar si el símbolo ya ha sido procesado para evitar la recursión infinita
    if (simbolosProcesados.contains(simbolo)) {
        return palabras;
    }

    // Agregar el símbolo actual a la lista de símbolos procesados
    simbolosProcesados.add(simbolo);

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
                // Si la regla es una cadena vacía, agregar una palabra vacía y continuar
                if (regla.equals("ε")) {
                    palabras.add("");
                    continue;
                }

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
                            List<String> palabrasGeneradasParaParte = generarPalabrasAux(parte, new ArrayList<>(simbolosProcesados));
                            for (String palabraGenerada : palabrasGeneradasParaParte) {
                                nuevasPalabras.add(palabraParcial + palabraGenerada);
                            }
                        }
                    }
                    palabrasRegla = nuevasPalabras;
                }

                // Agregar todas las palabras generadas para esta regla a la lista principal de palabras
                palabras.addAll(palabrasRegla);
            }
            break;
        }
    }

    return palabras;
}


     public static void main(String[] args) {
        // Crear una instancia de la clase TCLenguaje
        TCLenguaje gramatica = new TCLenguaje();

        // Definir símbolos no terminales y reglas
        gramatica.agregarNoTerminal("B", "C D 1 | 1");
        gramatica.agregarNoTerminal("C", "D E 2 | 2");
        gramatica.agregarNoTerminal("D", "C 1 C 2 | 2");
        gramatica.agregarNoTerminal("E", "B C | C D 1 | 1");
       
        
        // Definir símbolos terminales
        gramatica.agregarTerminales("1");
        gramatica.agregarTerminales("2");
        gramatica.agregarTerminales("3");
         

        // Definir símbolo inicial
        gramatica.simboloInicial("B");

        // Generar palabras y mostrarlas en la consola
        gramatica.generarPalabras();
    }

}