package mozos_spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.ui.Model;
import mozos_spring.model.Usuario;
import mozos_spring.service.UsuarioService;

@Controller
@RequestMapping("/administrador")
public class administradorController {

    private final UsuarioService usuarioService;

    @Autowired
    public administradorController(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    @GetMapping("/inicio")
    public String inicio(){
        return "administrador/inicio";
    }

    @GetMapping("/crearUsuarioAdmin")
    public String crearUsuarioAdmin(){
        return "administrador/crearUsuarioAdmin";
    }
    //Decidir si usar DTOs, si lo hago presta atencion a como implementas su constructor
    @PostMapping("/crearUsuarioAdmin")                  //Crear usuario
    public String crearUsuarioAdmin(@ModelAttribute Usuario usuario){
        usuario.setRol(Usuario.Rol.ADMIN);
        usuarioService.save(usuario);
        return "administrador/inicio"; // Redirigir a una página de éxito o lista de usuarios
    }

    @PostMapping("/borrarUsuarioAdmin/{id}")           //Borrar usuario
    public String borrarUsuarioAdmin(@PathVariable Long id){
        usuarioService.delete(id);
        return "administrador/inicio";
    }

    @GetMapping("/modificarUsuarioAdmin/{id}")        //Obtiene id del usuario a actualizar (del front)
    public String modificarUsuarioAdmin(@PathVariable Long id, Model model){
        Usuario usuario = usuarioService.get(id); //la excepcion se arroja en el serviceImpl
        model.addAttribute("usuario", usuario);
        return "administrador/modificarAdmin";
    }

    @PostMapping("/modificarUsuarioAdmin")             //Modifica usuario
    public String modificarUsuarioAdmin(Usuario usuario){
        usuarioService.actualizarUsuario(usuario);
        return "redirect:/administrador/inicio";

    }


}
