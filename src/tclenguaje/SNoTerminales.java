/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tclenguaje;

/**
 *
 * @author David
 */
public class SNoTerminales {

    public String simbolo;
    String reglas;

    public SNoTerminales() {
        reglas = "";
    }

    public SNoTerminales(String simbolo, String reglas) {
        this.simbolo = simbolo;
        this.reglas = reglas;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public String getReglas() {
        return reglas;
    }

    @Override // agreagr en el pdf
    public String toString() {
        return simbolo + "=> " + reglas + " ";
    }

}
