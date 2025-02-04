package mozos_spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import mozos_spring.model.Respuesta;

@Repository
public interface RespuestaRepository extends JpaRepository<Respuesta, Long> {

}
