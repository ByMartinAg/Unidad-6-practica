
public class Cancion {
    private final int id;
    private final String nombre;
    private final String artista;
    private final String genero;

    public Cancion(int id, String nombre, String artista, String genero) {
        this.id = id;
        this.nombre = nombre;
        this.artista = artista;
        this.genero = genero;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getArtista() {
        return artista;
    }

    public String getGenero() {
        return genero;
    }

    @Override
    public String toString() {
        return "--" + nombre + " - " + artista + " [" + genero + "]";
    }
}