package br.com.fiap.controle_radios_hoteis.hoteis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HoteisService {

    @Autowired
    HoteisService repository;

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

}