package mozos_spring.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "encuesta")
public class Encuesta {
    //Atributos: Título, Descripción, Fecha de Creación, Fecha de Cierre, Privacidad.
    public enum Privacidad {
        PUBLIC,
        PRIVATE
    }    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)    //, length = 4000)
    //@Size(max = 4000)
    private String descripcion;

    @Column(nullable = false)
    private LocalDate fechacreacion = LocalDate.now(); //por cada instancia se crea una fecha que solo se tendrá dia/mes/anio

    @Column(nullable = true)
    private LocalDate fechacierre;

    @Column(nullable = false)
    private Privacidad privacidad;

    @OneToMany(mappedBy = "encuesta", cascade = CascadeType.ALL, orphanRemoval = true)  //mappedBy = "encuesta" indica que la relación está mapeada en el campo encuesta de la clase Pregunta.
    private List<Pregunta> preguntas = new ArrayList<>();                               //orphanRemoval = true elimina preguntas huérfanas (sin encuesta asociada).
                                                                                        //cascade = CascadeType.ALL asegura que al guardar o eliminar una encuesta, sus preguntas también se guarden o eliminen.
    public Encuesta(){          //JPA necesita constructor sin parametros
    }

    public Long getId() {       //el id no necesita un setter ya que se asigna de forma automatica
        return id;
    }

    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFechacreacion() {   //no usaremos un setter para la fecha de creacion, al ya crearse junto a la entidad(encuesta)
        return fechacreacion;
    }

    public LocalDate getFechacierre() {
        return fechacierre;
    }
    public void setFechacierre(LocalDate fechacierre) {
        this.fechacierre = fechacierre;
    }

    public Privacidad getPrivacidad() {
        return privacidad;
    }
    public void setPrivacidad(Privacidad privacidad) {
        this.privacidad = privacidad;
    }

    public List<Pregunta> getPreguntas() {
        return preguntas;
    }
    
    // Guardar en el repositorio | Ver como hacer esto | Creo que se hace en el controlador
    //encuestaRepository.save(encuesta);

}
