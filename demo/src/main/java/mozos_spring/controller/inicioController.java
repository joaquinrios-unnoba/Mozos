package mozos_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class inicioController {

    @GetMapping("/")
    public String PagInicial() {
        return "redirect:/inicio"; // eliges acceder como usuario visitante, registrado o admin
    }
}
