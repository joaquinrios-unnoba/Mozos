package mozos_spring.service;

import java.util.List;

import mozos_spring.model.Respuesta;

public interface RespuestaService {

    Respuesta get(Long id);
    List<Respuesta> getAll();
    void save(Respuesta respuesta);
    void delete(Long id);

}
