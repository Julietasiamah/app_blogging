package it.epicode.app_blogging.blog_autore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/autori")
public class AutoreController {

    @Autowired
    private AutoreService service;

    @PostMapping
    public ResponseEntity<Autore> create(@RequestBody Autore autore) {
        Autore creato = service.create(autore);
        return new ResponseEntity<>(creato, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Autore> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Autore> getById(@PathVariable Long id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
