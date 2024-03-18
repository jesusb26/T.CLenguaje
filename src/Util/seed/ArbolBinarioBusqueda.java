/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util.seed;

/**
 *
 * @author madar
 */

    
    
    
    import java.util.Iterator;

/**
 * Implementacion de Clase para el manejo de un Arbol Binario de busqueda (ordenado). <br>
 * @param <T> Tipo de dato a almacenar en el Arbol Binario de Busqueda. <br>
 * @author Marco Adarme
 * @version 2.0
 */
public class ArbolBinarioBusqueda <T> extends ArbolB<T> {

    
    ////////////////////////////////////////////////////////////
    // ArbolBinarioDeBusqueda  - Implementacion de Metodos /////
    ////////////////////////////////////////////////////////////
    
   /**
    * Crea un Arbol Binario de Busqueda vacio. <br>
    * <b>post: </b> Se creo un Arbol Binario de Busqueda vacio. <br>
    */
    public ArbolBinarioBusqueda(){
        super();
    }
    
    /**
     * Crea un arbol con una raiz predefinida. <br>
     * <b>post: </b> Se creo un Arbol Binario de Busqueda con raiz predeterminada. <br>
     * @param raiz  un tipo T, almacena la direccion de memoria de un nodo de un Arbol Binario de Busqueda. <br>
     */
     public ArbolBinarioBusqueda(T raiz){
         super(raiz);
     }
     
     @Override
     public NodoB<T> getRaiz(){
         return (super.getRaiz());
     }
     
     /**
     * Metodo que permite conocer el objeto raiz del Arbol AVL. <br>
     * <b>post: </b> Se retorno el objeto raiz del Arbol. <br>
     * @return Un objeto de tipo T que representa el dato en la raiz del Arbol.
     */
    @Override
    public T getObjRaiz() {
        return super.getObjRaiz();
    }
   
    /**
     * Metodo que permite insertar un dato en el Arbol Binario de Busqueda. <br>
     * <b>post: </b> Se inserto un nuevo dato al Arbol Binario de Busqueda. <br>
     * @param dato un elemento tipo T que se desea almacenar en el arbol. <br>
     * @return  true si el elemento fue insertado o false en caso contrario
     */
     public boolean insertar(T dato){
        NodoB<T> rr=this.esta(dato)?null:insertar(this.getRaiz(), dato);
        if(rr!=null)
             this.setRaiz(rr);
        return (rr!=null);
     }

    /**
     * Metodo que permite insertar un dato en el Arbol Binario de Busqueda segun factor de ordenamiento. <br>
     * <b>post: </b> Se inserto ordenado un nuevo dato al Arbol Binario de Busqueda. <br>
     * @param r de tipo NoboBin<T> que representa la raiz del arbol. <br>
     * @param dato elemento a insertar en el arbol de forma ordenada. <br>
     * @return true si el elemento fue insertado o false en caso contrario
     */
    private NodoB<T> insertar(NodoB<T> r, T dato){
        if(r==null)
            return(new NodoB<T>(dato,null, null));
        int compara=((Comparable)r.getInfo()).compareTo(dato);
        if(compara>0) 
            r.setIzquierdo(insertar(r.getIzquierdo(), dato));
        else
            if(compara<0)  
                    r.setDerecho(insertar(r.getDerecho(), dato));
            else{
            System.err.println("Error dato duplicado:"+dato.toString());
            }
        return r;
    }

    /**
     * Metodo que permite borrar un elmento del Arbol Binario de Busqueda. <br>
     * <b>post: </b> Se elimino el elemento en el Arbol Binario de Busqueda. <br>
     * @param x dato que se desea eliminar. <br>
     * @return  el dato borrado o null si no lo encontro
     */
    
    public boolean eliminar(T x){
        if(!this.esta(x)){
            return (false);
        }
        NodoB<T> z=eliminarABB(this.getRaiz(),x);
        this.setRaiz(z);
        return (true);
    }
    
     /**
      * Metodo de tipo privado que permite eliminar un dato en el Arbol Binario de Busqueda segun factor de ordenamiento, manteniendo su propiedad de orden,
      * para esto se busca el menor de los derechos y lo intercambia por el dato que desea eliminar. La idea del algoritmo es que el dato a eliminar 
      * se coloque en una hoja o en un nodo que no tenga una de sus ramas. <br>
      * <b>post: </b> Se elimino el elemento Arbol Binario de Busqueda. <br>
      * @param r de tipo NoboBin<T> que representa la raiz del arbol. <br>
      * @param dato elemento que se desea eliminar del arbol. <br>
      * @return el dato borrado o null si no lo encontro
      */
    private NodoB<T> eliminarABB(NodoB<T> r, T x){
        if (r==null)
                return null;//<--Dato no encontrado		
        int compara=((Comparable)r.getInfo()).compareTo(x);
        if(compara>0)
                r.setIzquierdo(eliminarABB(r.getIzquierdo(), x));
        else
            if(compara<0)
                    r.setDerecho(eliminarABB(r.getDerecho(), x));
            else{
                if(r.getIzquierdo()!=null && r.getDerecho()!=null){
                     NodoB<T> cambiar=this.masIzquierdouierda(r.getDerecho());
                     T aux=cambiar.getInfo();
                     cambiar.setInfo(r.getInfo());
                     r.setInfo(aux);
                     r.setDerecho(eliminarABB(r.getDerecho(),x));
                    }
                else{
                    r=(r.getIzquierdo()!=null)? r.getIzquierdo():r.getDerecho();
                 }
            }
        return r;
    }

    /**
     * Metodo que busca el menor dato del arbol. El menor dato del arbol se encuentra en el nodo mas izquierdo. <br>
     * <b>post: </b> Se retorno el nodo mas izquierdo del arbol. <br>
     * @param r reprenta la raiz del arbol. <br>
     * @return el nodo mas izquierdo del arbol
     */
    @SuppressWarnings("empty-statement")
    protected NodoB<T> masIzquierdouierda(NodoB<T> r){
        for(; r.getIzquierdo()!=null; r=r.getIzquierdo());
        return(r);
    }
    
    /**
     * Metodo que permite evaluar la existencia de un dato dentro del Arbol Binario de Busqueda es necesario para que el metodo funcione 
     * que los objetos almacenados en el arbol tengan sobreescrito el metodo equals. <br>
     * <b>post: </b> Se retorno true si el elemento se encuentra en el Arbol. <br>
     * @param x representa la informacion del elemento que se encontrar en el arbol. <br>
     * @return un boolean , true si el dato esta o false en caso contrario.
     */
    public boolean esta(T x){
        return(esta(this.getRaiz(), x));
    }
    
    /**
     * Metodo que permite conocer si un elemento especifico se encuentra en el arbol. <br>
     * <b>post: </b> Se retorno true si el elemento se encuentra en el arbol. <br>
     * @param r representa la raiz del arbol. <br>
     * @param x representa la informacion del elemento que se encontrar en el arbol. <br>
     * @return un boolean , true si el dato esta o false en caso contrario.
     */
    private boolean esta(NodoB<T> r, T x){
        if (r==null)
            return (false);
        int compara=((Comparable)r.getInfo()).compareTo(x);
        if(compara>0)
            return(esta(r.getIzquierdo(),x));
        else
            if(compara<0)
                return(esta(r.getDerecho(),x));
            else
                return (true);
    }
    
    /**
     * Metodo que permite consultar un elemento existente dentro del Arbol Binario de Busqueda. <br>
     * <b>post: </b> Se retorno un NodoB<T> perteneciente al dato buscado. <br>
     * @param info Elemento a ubicar dentro del Arbol Binario de Busqueda. <br>
     * @return Un objeto de tipo NodoB<T> que representa el objeto buscado.
     */
    protected NodoB<T> buscar(T info){
        return (buscar(this.getRaiz(),info));
    }
   
    /**
     * Metodo que permite consultar un elemento existente dentro del Arbol Binario de Busqueda. <br>
     * <b>post: </b> Se retorno un NodoB<T> perteneciente al dato buscado. <br>
     * @param info Elemento a ubicar dentro del Arbol Binario de Busqueda. <br>
     * @param r Representa la raiz del Arbol. <br>
     * @return Un objeto de tipo NodoB<T> que representa el objeto buscado.
     */
    protected NodoB<T> buscar(NodoB<T> r, T info){
        if(r==null)
            return (null);
        if(r.getInfo().equals(info))
            return r;
        else
        {
            NodoB<T> aux = (r.getIzquierdo()==null)?null:buscar(r.getIzquierdo(),info);
            if(aux!=null)
                return (aux);
            else
                return (r.getDerecho()==null)?null:buscar(r.getDerecho(),info);
        }
    }
    
    /**
     * Metodo que retorna un iterador con las hojas del Arbol Binario de Busqueda. <br>
     * <b>post: </b> Se retorno un iterador con las hojas del Arbol Binario de Busqueda.<br>
     * @return un iterador con las hojas del Arbol Binario de Busqueda.
     */
    @Override
    public Iterator<T> getHojas(){
        return (super.getHojas());
    }
        
    
    public void imprime(){
        System.out.println(" ----- Arbol Binario de Busqueda ----- ");
        this.imprimeABB(super.getRaiz());
    }
    
    /**
     * Metodo de tipo privado que permite mostrar por consola la informacion del Arbol Binario. <br>
     * @param n Representa la raiz del ArbolBinario o de alguno de sus subarboles.
     */
    public void imprimeABB(NodoB<T> n) {
        T l = null;
        T r = null;
        if(n==null)
            return;
        if(n.getIzquierdo()!=null) {
         l = n.getIzquierdo().getInfo();
        }
        if(n.getDerecho()!=null) {
         r =n.getDerecho().getInfo();
        }       
        System.out.println("NodoIzquierdo: "+l+"\t Info: "+n.getInfo()+"\t NodoDerecho: "+r);
        if(n.getIzquierdo()!=null) {
         imprimeABB(n.getIzquierdo());
        }
        if(n.getDerecho()!=null) {
         imprimeABB(n.getDerecho());
        }
    }

}
