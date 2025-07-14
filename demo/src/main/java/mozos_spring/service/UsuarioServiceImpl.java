package mozos_spring.service;

import mozos_spring.exceptionHandler.ResourceNotFoundException;
import mozos_spring.model.Usuario;
import mozos_spring.repository.UsuarioRepository;

import java.util.List;

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
    public Usuario get(Long id) {           //Buscamos un usuario por su id
        return usuarioRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con ID: " + id));
    }

    @Override
    public List<Usuario> getAll() {         //Obtenemos TODOS los usuarios
        return usuarioRepository.findAll();
    }

    @Override
    public void save(Usuario usuario) {     //GUARDAMOS USUARIOS VALIDANDO MAIL Y NOMBRE Y ENCODIANDO SU CONTRASEÑA
                                            
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
    public void actualizarUsuario(Usuario usuarioActualizado) {     //Actualizamos un usuario
        Usuario existente = usuarioRepository.findById(usuarioActualizado.getId())
            .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));  //Busco por id el usuario que voy a modificar (debe existir) sino excepcion

        if (!usuarioActualizado.getEmail().equals(existente.getEmail()) &&          //Si actualice el mail y es distinto al que tenia, compruebo que no se repita
            usuarioRepository.findByEmail(usuarioActualizado.getEmail()).isPresent()) {
            throw new IllegalArgumentException("El email ya está en uso.");
        }

        if (!usuarioActualizado.getUsername().equals(existente.getUsername()) &&    //Si actualice el nombre y es distinto al que tenia, compruebo que no se repita
            usuarioRepository.findByUsername(usuarioActualizado.getUsername()).isPresent()) {
            throw new IllegalArgumentException("El username ya está en uso.");
        }

        existente.setUsername(usuarioActualizado.getUsername());
        existente.setEmail(usuarioActualizado.getEmail());

        if (usuarioActualizado.getContrasenia() != null && !usuarioActualizado.getContrasenia().isEmpty()) {
            existente.setContrasenia(passwordEncoder.encode(usuarioActualizado.getContrasenia()));
        }

        usuarioRepository.save(existente);
    }


    @Override
    public void delete(Long id){            //Borra un usuario usando su id
        usuarioRepository.deleteById(id);
    }

    @Override                                                                                       //Se usa para cargar los detalles del usuario (UserDetails) a partir de su username, y se usa durante el proceso de autenticación
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {       //Aquí, usuario.getRol() debe devolver una COLECCION de ROLES
        Usuario usuario = usuarioRepository.findByUsername(username)                                //generalmente una lista de objetos GrantedAuthority.
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));   //Como getRol() en Usuario no está devolviendo una colección de roles compatible
                                                                                                    // los roles se mapean con el prefijo ROLE_
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_" + usuario.getRol()));
    
        return new User(usuario.getUsername(), usuario.getContrasenia(), authorities);
    }

    @Override
    public Usuario findByEmail(String email) {          //Busca un usuario por su mail
    return usuarioRepository.findByUsername(email).orElse(null);
}

    @Override
    public Usuario findByUsername(String username) {   //Busca un usuario pir su nombre
    return usuarioRepository.findByUsername(username).orElse(null);
}

}
