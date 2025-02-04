package mozos_spring.service;

import java.util.List;
import mozos_spring.model.Encuesta;

public interface EncuestaService {

    Encuesta get(Long id);
    List<Encuesta> getAll();
    void save(Encuesta encuesta);
    void delete(Long id);
}
