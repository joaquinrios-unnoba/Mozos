package mozos_spring.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import mozos_spring.model.Usuario;

public interface UsuarioService extends UserDetailsService{

    Usuario get(Long id);
    List<Usuario> getAll();
    void save(Usuario usuario);
    void delete(Long id);
    Usuario findByEmail(String email);
    Usuario findByUsername(String username);
    public void actualizarUsuario(Usuario usuarioActualizado); 

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException; 

}
