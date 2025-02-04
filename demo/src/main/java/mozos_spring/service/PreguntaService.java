package mozos_spring.service;

import java.util.List;
import mozos_spring.model.Pregunta;

public interface PreguntaService {

    Pregunta get(Long id);
    List<Pregunta> getAll();
    void save(Pregunta pregunta);
    void delete(Long id);

}
