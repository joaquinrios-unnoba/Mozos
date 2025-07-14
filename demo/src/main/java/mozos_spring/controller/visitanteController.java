package mozos_spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @GetMapping("/crearUsuarioResgistrado")
    public String crearUsuarioAdmin(){          //ACOMODAR ESTO PARA CREAR USUARIOS REGISTRADOS
        return "administrador/crearUsuarioAdmin";
    }

    @PostMapping("/crearUsuarioRegistrado")
    public String crearUsuarioAdmin(@ModelAttribute Usuario usuario) {
        usuario.setRol(Usuario.Rol.USER);
        usuarioService.save(usuario);
        return "autenticaciones/pagInicial"; //return "administrador/inicio"; // Redirigir a una página de éxito o lista de usuarios
    }

}
