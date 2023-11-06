package br.com.fiap.controle_radios_hoteis.hoteis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import br.com.fiap.epictask.user.User;
import br.com.fiap.epictask.user.UserService;

@Service
public class HoteisService {

    @Autowired
    HoteisService repository;

    @Autowired
    UserService userService;

    public List<Hoteis> findAll(){
        return repository.findAll();
    }

    /**
     * @param id
     * @return
     */
    public boolean delete(Long id) {
        final var hoteis = repository.findById(id);

        repository.deleteById(id);
        return true;
    }

    private void deleteById(Long id) {
    }

    private Object findById(Long id) {
        return null;
    }

    public void decrement(Long id) {
        var optional = repository.findById(id);
        if (optional.isEmpty())
            throw new RuntimeException("radio não encontrada");

        var task = optional.get();
        if (task.getStatus() == 0)
            throw new RuntimeException("status não pode ser negativo");

        task.setStatus(task.getStatus() - 10);
        repository.save(hotel);
    }

    public void increment(Long id) {
        var optional = repository.findById(id);
        if (optional.isEmpty())
            throw new RuntimeException("Radio não encontrada");

        var task = optional.get();
        if (task.getStatus() == 100)
            throw new RuntimeException("status não pode ser maior do que 100%");

        task.setStatus(task.getStatus() + 10);

        if (task.getStatus() == 100){
            var user = (OAuth2User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            userService.addScore(User.convert(user), hotel.getScore());
        }


        repository.save(hotel);
    }

    public void catchTask(Long id, User user) {
        var optional = repository.findById(id);
        if (optional.isEmpty())
            throw new RuntimeException("Radio não encontrada");

        var task = optional.get();

        if (task.getUser() != null)
            throw new RuntimeException("Radio já atribuída");

        task.setUser(user);
        repository.save(hotel);

    }

    public void dropTask(Long id, User user) {
        var optional = repository.findById(id);
        if (optional.isEmpty())
            throw new RuntimeException("Radio  não encontrada");

        var task = optional.get();

        if (task.getUser() == null)
            throw new RuntimeException("Radio não já atribuída");

        if (!task.getUser().equals(user))
            throw new RuntimeException("Radio atribuída para outro usuário");

        task.setUser(null);
        repository.save(hotel);
    }
}