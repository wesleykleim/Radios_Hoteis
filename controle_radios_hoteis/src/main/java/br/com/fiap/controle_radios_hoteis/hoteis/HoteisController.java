import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import br.com.fiap.controle_radios_hoteis.user.User;
import jakarta.validation.Valid;
@Controller
@RequestMapping("/hoteis")
public class HoteisController {

  @Autowired
  Hoteis Service service;
  @GetMapping
  public String index(Model model, @AuthenticationPrincipal OAuth2User user) {
    model.addAttribute("avatar_url", user.getAttribute("avatar_url"));
    model.addAttribute("username", user.getAttribute("name"));
    model.addAttribute("hoteis", service.findAll());
    return "hoteis/index";
  }
  @GetMapping("/delete/{id}")
  public String delete(@PathVariable Long id, RedirectAttributes redirect) {
    if (service.delete(id)) {
      redirect.addFlashAttribute("success", "Hotel apagada com sucesso");
    } else {
      redirect.addFlashAttribute("error", "Hotel não encontrada");
    }
    return "redirect:/hoteis";
  }
  @GetMapping("new")
  public String form(Hoteis hoteis) {
    return "task/form";
  }
  @PostMapping
  public String save(@Valid Hoteis hoteis, BindingResult result, RedirectAttributes redirect) {
    if (result.hasErrors())
      return "/hoteis/form";
    service.save(hoteis);
    redirect.addFlashAttribute("success", "Hotel cadastrada com sucesso");
    return "redirect:/hotel";
  }

  @GetMapping("dec/{id}")
    public String decrement(@PathVariable Long id){
        service.decrement(id);
        return "redirect:/hotel";
    }

    @GetMapping("inc/{id}")
    public String increment(@PathVariable Long id){
        service.increment(id);
        return "redirect:/hotel";
    }

    @GetMapping("catch/{id}")
    public String catchTask(@PathVariable Long id, @AuthenticationPrincipal OAuth2User user){
        service.catchTask(id, User.convert(user));
        return "redirect:/hotel";
    }

    @GetMapping("drop/{id}")
    public String dropTask(@PathVariable Long id, @AuthenticationPrincipal OAuth2User user){
        service.dropTask(id, User.convert(user));
        return "redirect:/hotel";
    }

}