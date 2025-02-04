package mozos_spring.service;

import mozos_spring.exceptionHandler.ResourceNotFoundException;
import mozos_spring.model.Usuario;
import mozos_spring.repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, BCryptPasswordEncoder passwordEncoder){
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Usuario get(Long id) {
        return usuarioRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con ID: " + id));
    }

    @Override
    public List<Usuario> getAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public void save(Usuario usuario) {     //GUARDAMOS USUARIOS VALIDANDO MAIL Y NAME Y ENCODIANDO SU CONTRASEÑA
                                            
        if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {    //valido que el mail y el nombre no esten resgistrados
            throw new IllegalArgumentException("El email ya está registrado.");
        }
        if (usuarioRepository.findByUsername(usuario.getUsername()).isPresent()) {
            throw new IllegalArgumentException("El username ya está registrado.");
        }
                                            
        usuario.setContrasenia(passwordEncoder.encode(usuario.getContrasenia()));// Se encripta la contraseña antes de guardarla
        usuarioRepository.save(usuario);
    }

    @Override
    public void delete(Long id){
        usuarioRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {       //Aquí, usuario.getRol() debe devolver una COLECCION de ROLES
        Usuario usuario = usuarioRepository.findByUsername(username)                                //generalmente una lista de objetos GrantedAuthority.
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));   //Como getRol() en Usuario no está devolviendo una colección de roles compatible
                                                                                                    // los roles se mapean con el prefijo ROLE_
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_" + usuario.getRol()));
    
        return new User(usuario.getUsername(), usuario.getContrasenia(), authorities);
    }

    @Override
    public Optional<Usuario> findByEmail(String email) {
    return usuarioRepository.findByEmail(email);
}

    @Override
    public Usuario findByUsername(String username) {
    return usuarioRepository.findByUsername(username).orElse(null);
}

}
