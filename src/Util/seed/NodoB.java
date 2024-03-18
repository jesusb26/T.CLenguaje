/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util.seed;

import java.util.Objects;

/**
 *
 * @author Docente
 */
public class NodoB<T> {
    
    private T info;
    private NodoB<T> izquierdo;
    private NodoB<T> derecho;

    public NodoB() {
    }

    /**
     * Deseo crear hojas
     * @param info un elemento que se va almacenar en las hojas
     */
    public NodoB(T info) {
        this.info = info;
        //NO es necesario:
        this.izquierdo=this.derecho=null;
    }
    
    

    public NodoB(T info, NodoB<T> izquierdo, NodoB<T> derecho) {
        this.info = info;
        this.izquierdo = izquierdo;
        this.derecho = derecho;
    }

    public T getInfo() {
        return info;
    }

    public void setInfo(T info) {
        this.info = info;
    }

    public NodoB<T> getIzquierdo() {
        return izquierdo;
    }

    public void setIzquierdo(NodoB<T> izquierdo) {
        this.izquierdo = izquierdo;
    }

    public NodoB<T> getDerecho() {
        return derecho;
    }

    public void setDerecho(NodoB<T> derecho) {
        this.derecho = derecho;
    }

    @Override
    public String toString() {
        return "NodoB{" + "info=" + info + ", izquierdo=" + izquierdo + ", derecho=" + derecho + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.info);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final NodoB<?> other = (NodoB<?>) obj;
        return Objects.equals(this.info, other.info);
    }
    
    
    public boolean isHoja()
    {
        return this.derecho==null && this.izquierdo==null;
    }
    
}
