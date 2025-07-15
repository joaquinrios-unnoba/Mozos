package mozos_spring.repository;

import org.springframework.stereotype.Repository;
import mozos_spring.model.Usuario;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {   //El primer parametro indica sobre que "tabla" va a trabajar
                                                                            //y el segundo indica de que tipo de dato es la llave primaria de esa "tabla"
    Optional<Usuario> findByEmail(String email);
    Optional<Usuario> findByUsername(String username);
    List<Usuario> findByRol(Usuario.Rol rol);
                                                                            

}