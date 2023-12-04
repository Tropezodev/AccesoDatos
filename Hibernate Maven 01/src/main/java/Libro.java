import javax.persistence.*;

@Entity
@Table(name="libros")
public class Libro {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // El @Column es para añadirle parámetros a la columna, ya las crea con el nombre del String automáticamente.
    @Column(nullable = false) // nullable=false es para que se ponga el título a la fuerza
    private String titulo;
    private String autor;
    @ManyToOne // Se establece la relación entre las clases Libro y Editorial. Se devuelve la Editorial de cada libro
    private Editorial editorial;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Editorial getEditorial() {
        return editorial;
    }

    public void setEditorial(Editorial editorial) {
        this.editorial = editorial;
    }

    public Libro() {
    }
}

