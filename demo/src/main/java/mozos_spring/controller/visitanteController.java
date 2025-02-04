package mozos_spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import mozos_spring.model.Usuario;
import mozos_spring.service.UsuarioService;

@Controller
@RequestMapping("/visitante")
public class visitanteController {

    private final UsuarioService usuarioService;

    @Autowired
    public visitanteController(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    @GetMapping("/encuestas")
    public String encuestas(){
        return "visitante/encuestasVisitante";
    }

    @GetMapping("/crearUsuarioAdmin")
    public String crearUsuarioAdmin(){
        return "administrador/crearUsuarioAdmin";
    }

    @PostMapping("/crearUsuarioAdmin")
    public String crearUsuarioAdmin(@RequestParam String username, 
                                @RequestParam String email, 
                                @RequestParam String contrasenia) {
        Usuario usuario = new Usuario();
        usuario.setUsername(username);
        usuario.setEmail(email);
        usuario.setContrasenia(contrasenia);
        usuario.setRol(Usuario.Rol.ADMIN);

        // Guardar el usuario en la base de datos (suponiendo que tienes un repositorio de usuarios)
        usuarioService.save(usuario);

        return "autenticaciones/pagInicial"; //return "administrador/inicio"; // Redirigir a una página de éxito o lista de usuarios
    }

}
