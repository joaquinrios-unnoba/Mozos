package mozos_spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mozos_spring.model.Respuesta;
import mozos_spring.repository.RespuestaRepository;

@Service
public class RespuestaServiceImpl implements RespuestaService {

    private final RespuestaRepository respuestaRepository;

    @Autowired
    public RespuestaServiceImpl(RespuestaRepository respuestaRepository){
        this.respuestaRepository = respuestaRepository;
    }

    @Override
    public Respuesta get(Long id) {
        return respuestaRepository.findById(id).orElse(null);
    }

    @Override
    public List<Respuesta> getAll() {
        return respuestaRepository.findAll();
    }

    @Override
    public void save(Respuesta respuesta) {
        respuestaRepository.save(respuesta);
    }

    @Override
    public void delete(Long id){
        respuestaRepository.deleteById(id);
    }

}
