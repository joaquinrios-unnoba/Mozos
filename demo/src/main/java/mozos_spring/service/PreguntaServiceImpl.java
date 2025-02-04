package mozos_spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mozos_spring.model.Pregunta;
import mozos_spring.repository.PreguntaRepository;

@Service
public class PreguntaServiceImpl implements PreguntaService {

    private final PreguntaRepository preguntaRepository;

    @Autowired
    public PreguntaServiceImpl(PreguntaRepository preguntaRepository){
        this.preguntaRepository = preguntaRepository;
    }

    @Override
    public Pregunta get(Long id) {
        return preguntaRepository.findById(id).orElse(null);
    }

    @Override
    public List<Pregunta> getAll(){
        return preguntaRepository.findAll();
    }

    @Override
    public void save(Pregunta pregunta){
        preguntaRepository.save(pregunta);
    }

    @Override
    public void delete(Long id){
        preguntaRepository.deleteById(id);
    }

}
