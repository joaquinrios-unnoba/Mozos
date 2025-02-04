package mozos_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class LoginController {

    @GetMapping("/inicio")
    public String PagInicial() {
        return "autenticaciones/pagInicial"; // eliges acceder como usuario visitante, registrado o admin
    }

     @GetMapping("/login") //recorda agregar "autenticacion" a uso publico
    public String login() {
        return "autenticaciones/login"; // Cambia "/login" a la página que prefieras
    }

   /* @PostMapping("/login")
    public String login (@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        HttpServletRequest request,
                        HttpServletResponse response,
                        Model model){
        Usuario usuario = usuarioService.findByUsername(username); //consigo el usuario buscando por el nombre

        if (usuario != null && passwordEncoder.matches(password, usuario.getContrasenia())){ //el primer parametro es la contraseña ingresada por el user en texto plano
            //usuario y contraseña validos                                                     el segundo parametro es la contraseña encriptada en la BD 
            
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                usuario, null, usuario.getAuthorities());
            //if(usuario.getRol() == Rol.ADMIN){
               // return "administrador/inicio";
            //}       //RECORDA QUE EL LOGIN APARECE CUANDO INTENTAS ENTRAR A UNA PAGINA RESTRINGIDA
            //else{   //ASI QUE NO ES NECESARIO REDIRIGIR SOLO VERIFICAR LA AUTENTICAD, FIJATE COMO HACERLO
               // return "registrado/encuestasRegistrado";
            //}
        }
         // Credenciales inválidas
         model.addAttribute("error", "Usuario o contraseña incorrectos");
         return "autenticaciones/login";             
    }*/
}
