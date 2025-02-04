package mozos_spring.model;

import jakarta.persistence.*;

@Entity
@Table(name = "respuesta")
public class Respuesta {
    //Atributos: ID, UsuarioID (puede ser null para encuestas públicas), PreguntaID, Respuesta.

    @Id
    @Column(nullable = false, unique = true)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_Id", nullable = true)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "pregunta_id", nullable = false)
    private Pregunta pregunta;

    @Column(nullable = false)
    private String respuesta;

    public Respuesta(){ //contructor vacio        
    }

    public Long getId() {
        return id;
    }

    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Pregunta getPregunta() {
        return pregunta;
    }
    public void setPregunta(Pregunta pregunta) {
        this.pregunta = pregunta;
    }

    public String getRespuesta() {
        return respuesta;
    }
    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }
    

}
