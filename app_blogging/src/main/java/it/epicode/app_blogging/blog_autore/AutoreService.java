package it.epicode.app_blogging.blog_autore;

import it.epicode.app_blogging.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutoreService {

    @Autowired
    private AutoreRepository repository;

    @Autowired
    private EmailService emailService;

    public Autore create(Autore autore) {
        Autore savedAutore = repository.save(autore);
        emailService.sendConfermaAutore(autore.getEmail(), "Benvenuto!", "Ciao " + autore.getNome() + ", benvenuto nel nostro sistema!");
        return savedAutore;
    }

    public List<Autore> getAll() {
        return repository.findAll();
    }

    public Optional<Autore> getById(Long id) {
        return repository.findById(id);
    }

    public void delete(Long id) {
        Optional<Autore> autore = repository.findById(id);

        if (autore.isPresent()) {
            // Chiamare il servizio EmailService per inviare una notifica prima di eliminare
            emailService.sendConfermaAutore(autore.get().getEmail(), "Eliminazione Account", "Ciao " + autore.get().getNome() + ", il tuo account è stato eliminato.");

            // Elimina l'autore
            repository.deleteById(id);
        }
    }

}

