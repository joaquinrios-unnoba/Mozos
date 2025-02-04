package mozos_spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mozos_spring.model.Encuesta;
import mozos_spring.repository.EncuestaRepository;

@Service
public class EncuestaServiceImpl implements EncuestaService {

    private final EncuestaRepository encuestaRepository;

    @Autowired
    public EncuestaServiceImpl(EncuestaRepository encuestaRepository){
        this.encuestaRepository = encuestaRepository;
    }

    @Override
    public Encuesta get(Long id) {
        return encuestaRepository.findById(id).orElse(null);
    }

    @Override
    public List<Encuesta> getAll() {
        return encuestaRepository.findAll();
    }

    @Override
    public void save(Encuesta encuesta) {
        encuestaRepository.save(encuesta);
    }

    @Override
    public void delete(Long id){
        encuestaRepository.deleteById(id);
    }
}
