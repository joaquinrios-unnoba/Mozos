package mozos_spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import mozos_spring.model.Usuario;

public interface UsuarioService extends UserDetailsService{

    Usuario get(Long id);
    List<Usuario> getAll();
    void save(Usuario usuario);
    void delete(Long id);
    Optional<Usuario> findByEmail(String email);
    Optional<Usuario> findByUsername(String username);
    void actualizarUsuario(Usuario usuarioActualizado); 
    List<Usuario> listarAdmins();

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException; 

}
