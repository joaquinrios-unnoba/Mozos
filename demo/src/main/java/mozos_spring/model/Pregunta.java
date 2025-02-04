package mozos_spring.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "pregunta")
public class Pregunta {
    //Atributos: Texto de la pregunta, Tipo (opción múltiple, texto, etc.), EncuestaID.

    public enum TipoPreg{
        MULTIPLECHOISE,
        TEXTO
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String textoPregunta;

    @Column(nullable = false)
    private TipoPreg tipoPreg;

    @ManyToOne
    @JoinColumn(name = "encuesta_id", nullable = false) //@ManyToOne establece la relación de muchas preguntas hacia una encuesta.
    private Encuesta encuesta;                          //@JoinColumn(name = "encuesta_id") define la clave foránea (encuesta_id) que conecta preguntas con encuestas
                                             //El campo encuesta en el modelo almacena el objeto Encuesta.
                                             //La columna encuesta_id en la base de datos almacena el ID de esa encuesta.

    @OneToMany(mappedBy = "pregunta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Respuesta> respuestas = new ArrayList<>();


    public Pregunta(){ //Constructor vacio
    }

    public Long getId() {
        return id;
    }

    public String getTextoPregunta() {
        return textoPregunta;
    }
    public void setTextoPregunta(String textoPregunta) {
        this.textoPregunta = textoPregunta;
    }

    public TipoPreg getTipoPreg() {
        return tipoPreg;
    }
    public void setTipoPreg(TipoPreg tipoPreg) {
        this.tipoPreg = tipoPreg;
    }

    public Encuesta getEncuesta() {
        return encuesta;
    }
    public void setEncuesta(Encuesta encuesta) {
        this.encuesta = encuesta;
    }

    public List<Respuesta> getRespuestas() {
        return respuestas;
    }
 

}
