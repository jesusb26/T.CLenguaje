/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util.seed;

import java.util.Iterator;

/**
 *
 * @author Docente
 */
public class ArbolB<T> {

    private NodoB<T> raiz;

    public ArbolB() {
    }

    public NodoB<T> getRaiz() {
        return raiz;
    }

    public void setRaiz(NodoB<T> raiz) {
        this.raiz = raiz;
    }

    
     public ArbolB(T raiz) {
        this.raiz = new NodoB(raiz);
     }

    
    /**
     * Decorador de un recorrido preOrden
     *
     * @return un iterador de los elementos en preOrden
     */
    public Iterator<T> preOrden() {
        if (this.isEmpty()) {
            throw new RuntimeException("No se puede obtener un recorrido, el árbol está vacío");
        }
        ListaCD<T> l = new ListaCD();
        preOrden(this.raiz, l);
        return l.iterator();
    }

    private void preOrden(NodoB<T> r, ListaCD<T> l) {
        //Caso base:
        if (r == null) {
            return;
        }
        l.insertarFinal(r.getInfo());
        preOrden(r.getIzquierdo(), l);
        preOrden(r.getDerecho(), l);

    }

    public Iterator<T> inOrden() {
        if (this.isEmpty()) {
            throw new RuntimeException("No se puede obtener un recorrido, el árbol está vacío");
        }
        ListaCD<T> l = new ListaCD();
        inOrden(this.raiz, l);
        return l.iterator();
    }

    private void inOrden(NodoB<T> r, ListaCD<T> l) {
        //Caso base:
        if (r == null) {
            return;
        }
        inOrden(r.getIzquierdo(), l);
        l.insertarFinal(r.getInfo());
        inOrden(r.getDerecho(), l);
    }

    public Iterator<T> posOrden() {
        if (this.isEmpty()) {
            throw new RuntimeException("No se puede obtener un recorrido, el árbol está vacío");
        }
        ListaCD<T> l = new ListaCD();
        posOrden(this.raiz, l);
        return l.iterator();
    }

    private void posOrden(NodoB<T> r, ListaCD<T> l) {
        //Caso base:
        if (r == null) {
            return;
        }
        posOrden(r.getIzquierdo(), l);
        posOrden(r.getDerecho(), l);
        l.insertarFinal(r.getInfo());

    }

    public boolean isEmpty() {
        return this.raiz == null;
    }

    public Iterator<T> preOrdenDerechos() {
        if (this.isEmpty()) {
            throw new RuntimeException("No se puede obtener un recorrido, el árbol está vacío");
        }
        ListaCD<T> l = new ListaCD();
        preOrdenDerechos(this.raiz, l, false);
        return l.iterator();
    }

    private void preOrdenDerechos(NodoB<T> r, ListaCD<T> l, boolean esDerecho) {
        //Caso base:
        if (r == null) {
            return;
        }
        if (esDerecho) {
            l.insertarFinal(r.getInfo());
        }
        preOrdenDerechos(r.getIzquierdo(), l, false);
        preOrdenDerechos(r.getDerecho(), l, true);

    }

    public Iterator<T> getHojas() {
        if (this.isEmpty()) {
            throw new RuntimeException("No se puede obtener un recorrido, el árbol está vacío");
        }
        ListaCD<T> l = new ListaCD();
        getHojas(this.raiz, l);
        return l.iterator();
    }

    private void getHojas(NodoB<T> r, ListaCD<T> l) {
        //Caso base:
        if (r == null) {
            return;
        }
        if (r.isHoja()) {
            l.insertarFinal(r.getInfo());
        }
        getHojas(r.getIzquierdo(), l);
        getHojas(r.getDerecho(), l);

    }

    //Versión 1:
    public int getCardinalidad() {
        if (this.isEmpty()) {
            throw new RuntimeException("No se puede obtener un recorrido, el árbol está vacío");
        }
        int c[] = {0};
        getCardinalidad(this.raiz, c);
        return c[0];
    }

    private void getCardinalidad(NodoB<T> r, int[] c) {
        //Caso base:
        if (r == null) {
            return;
        }
        c[0]++;
        getCardinalidad(r.getIzquierdo(), c);
        getCardinalidad(r.getDerecho(), c);

    }

    public int getCardinalidad2() {
        if (this.isEmpty()) {
            throw new RuntimeException("No se puede obtener un recorrido, el árbol está vacío");
        }
        return getCardinalidad2(this.raiz);
    }

    private int getCardinalidad2(NodoB<T> r) {
        //Caso base:
        if (r == null) {
            return 0;
        }
        return 1 + getCardinalidad2(r.getIzquierdo()) + getCardinalidad2(r.getDerecho());

    }

    public int getCardinalidadExternos() {
        if (this.isEmpty()) {
            throw new RuntimeException("No se puede obtener un recorrido, el árbol está vacío");
        }
        return getCardinalidadExternos(this.raiz);
    }

    private int getCardinalidadExternos(NodoB<T> r) {
        //Caso base:
        if (r == null) {
            return 1;
        }
        return getCardinalidadExternos(r.getIzquierdo()) + getCardinalidadExternos(r.getDerecho());

    }

    public T getMenor() {
        if (this.isEmpty()) {
            throw new RuntimeException("No se puede obtener un recorrido, el árbol está vacío");
        }
        ListaCD<T> l = new ListaCD();
        l.insertarFinal(this.raiz.getInfo());
        getMenor(this.raiz, l);
        return l.get(0);
    }

    private void getMenor(NodoB<T> r, ListaCD<T> l) {
        //Caso base:
        if (r == null) {
            return;
        }

        Comparable c = (Comparable) r.getInfo();
        if (c.compareTo(l.get(0)) <= 0) {
            l.set(0, r.getInfo());
        }
        getMenor(r.getIzquierdo(), l);
        getMenor(r.getDerecho(), l);
    }

    public Iterator<T> getPreOrdenIterativo() {

        if (this.isEmpty()) {
            throw new RuntimeException("No recorrido por què el árbol es vacìo");
        }
        return this.getPreOrdenIterativo(raiz);
    }

    private Iterator<T> getPreOrdenIterativo(NodoB<T> raiz) {
        ListaCD<T> l = new ListaCD();
        Pila<NodoB<T>> pila = new Pila();
        pila.push(raiz);
        while (!pila.isEmpty()) {
            NodoB<T> nodoActual = pila.pop();
            l.insertarFinal(nodoActual.getInfo());
            if (nodoActual.getDerecho() != null) {
                pila.push(nodoActual.getDerecho());
            }
            if (nodoActual.getIzquierdo() != null) {
                pila.push(nodoActual.getIzquierdo());
            }
        }
        return l.iterator();
    }

    public Iterator<T> getRecorridoAnchura() {
        if (this.isEmpty()) {
            throw new RuntimeException("No recorrido por què el árbol es vacìo");
        }
        return this.getRecorridoAnchura(raiz);
    }

    private Iterator<T> getRecorridoAnchura(NodoB<T> r) {
        ListaCD<T> l = new ListaCD();
        Cola<NodoB<T>> cola = new Cola();
        cola.enColar(raiz);
        while (!cola.isEmpty()) {
            NodoB<T> nodoActual = cola.deColar();
            l.insertarFinal(nodoActual.getInfo());
            if (nodoActual.getIzquierdo() != null) {
                cola.enColar(nodoActual.getIzquierdo());
            }
            if (nodoActual.getDerecho() != null) {
                cola.enColar(nodoActual.getDerecho());
            }
        }
        return l.iterator();
    }

    public Iterator<T> getNiveles(int n) {
        if (this.isEmpty()) {
            throw new RuntimeException("No recorrido por què el árbol es vacìo");
        }
         if (n<0) {
            throw new RuntimeException("N tiene un valor negativo");
        }
        
        return this.getNiveles(raiz,n);
    }

    private Iterator<T> getNiveles(NodoB<T> r, int n) {

        ListaCD<T> l = new ListaCD();
        Cola<NodoB<T>> cola = new Cola();
        Cola<Integer> colaNiveles = new Cola();
        colaNiveles.enColar(0);
        cola.enColar(raiz);
        while (!cola.isEmpty()) {
            NodoB<T> nodoActual = cola.deColar();
            int m = colaNiveles.deColar();
            if (m == n) {
                l.insertarFinal(nodoActual.getInfo());
            }
            if (nodoActual.getIzquierdo() != null) {
                cola.enColar(nodoActual.getIzquierdo());
                colaNiveles.enColar(m + 1);
            }
            if (nodoActual.getDerecho() != null) {
                cola.enColar(nodoActual.getDerecho());
                colaNiveles.enColar(m + 1);
            }
        }
        return l.iterator();
    }
    
     public T getObjRaiz() {
        return (raiz.getInfo());
    }

}
