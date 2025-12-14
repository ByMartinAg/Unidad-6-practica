// Clase para inicializar el nodo
public class Nodo {
    private final int dato;
    // constructor
    public Nodo (int dato){
        this.dato= dato;
    }
    // es un getter para el valor del nodo
    public int getValor(){
        return dato;
    }
    // como se muestra el dato
    public String toString(){
        return String.valueOf(dato);
    }
}
