package it.epicode.app_blogging.blog_autore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutoreService {

    @Autowired
    private AutoreRepository repository;

    public Autore create(Autore autore) {
        return repository.save(autore);
    }

    public List<Autore> getAll() {
        return repository.findAll();
    }

    public Optional<Autore> getById(Long id) {
        return repository.findById(id);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}

