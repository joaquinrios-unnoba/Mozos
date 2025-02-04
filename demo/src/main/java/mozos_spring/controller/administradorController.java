package mozos_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/administrador")
public class administradorController {

    @GetMapping("/inicio")
    public String inicio(){
        return "administrador/inicio";
    }

}
