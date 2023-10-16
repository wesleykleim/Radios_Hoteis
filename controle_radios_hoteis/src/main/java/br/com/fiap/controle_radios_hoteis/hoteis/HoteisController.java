package br.com.fiap.controle_radios_hoteis.hoteis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
@Controller
@RequestMapping("/hoteis")
public class HoteisController {

    @Autowired
    HoteisService service;

    @GetMapping
    public String index(Model model, @AuthenticationPrincipal OAuth2User user) {
        model.addAttribute("avatar_url", user.getAttribute("avatar_url"));
        model.addAttribute("hoteis", service.findAll());
        return "hoteis/index";
    } 

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirect){
        if (service.delete(id)){
            redirect.addFlashAttribute("success", "Hotel apagada com sucesso");
        }else{
            redirect.addFlashAttribute("error", "Hotel não encontrado");
        }
        return "redirect:/hoteis";
    }

}