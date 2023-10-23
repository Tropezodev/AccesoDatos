import javax.persistence.*;

@Entity
@Table(name="libros")
public class Libro {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    String



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

