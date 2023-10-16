package br.com.fiap.controle_radios_hoteis.hoteis;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/hoteis")
public class HoteisController {

    @GetMapping
    public String index(Model model){
        model.addAttribute("hoteis", List.of( "Hotel 1","Hotel 2","Hotel 3","Hotel 4"));
        return"hoteis/index";
    }
    
}
