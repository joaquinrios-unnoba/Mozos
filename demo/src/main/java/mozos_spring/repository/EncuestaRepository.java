package mozos_spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import mozos_spring.model.Encuesta;

@Repository
public interface EncuestaRepository extends JpaRepository<Encuesta, Long> {

}
