package mozos_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registrados")
public class registradoController {

    @GetMapping("/inicio")
    public String inicioRegistrados(){
        return "registrado/encuestasRegistrado";
    }

}
