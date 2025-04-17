package it.epicode.app_blogging.blog_post;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/posts")
public class BlogPostController {

    @Autowired
    private BlogPostService service;

    @PostMapping
    public ResponseEntity<BlogPost> create(@RequestBody BlogPost post){
        BlogPost created = service.create(post);
        return new ResponseEntity<>(created, HttpStatus.CREATED);

    }

    @GetMapping
    public List<BlogPost> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity <BlogPost> getById(@PathVariable Long id){
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

    @PutMapping("/{id}")
    public ResponseEntity<BlogPost> update(@PathVariable Long id, @RequestBody BlogPost post) {
        return ResponseEntity.ok(service.update(id, post));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/paged") //Espone l'endpoint HTTP GET /paged
        //Serve per richiedere i blog post in modo paginato
    public Page <BlogPost> getAllPaged (
            @RequestParam(defaultValue = "0") int page, //Numero della pagina da visualizzare0 = prima pagina
            @RequestParam(defaultValue = "5") int size, //Quanti blog post mostrare per pagina mostra 5 post per pagina
            @RequestParam(defaultValue = "titolo") String sortBy //Campo secondo cui ordinare i risultati di default titolo
    ) {
        /*
        Chiama il metodo nel service (BlogPostService) per eseguire la logica di paginazione e ordinamento.
        Restituisce un oggetto Page<BlogPost> che contiene:
        i blog post della pagina richiesta
        e metadati (numero di pagine totali, numero corrente, ecc...)
        * */
        return service.getAllPaged(page, size, sortBy);
    }

    //abilito le validazioni
    @PostMapping
    public ResponseEntity<BlogPost> create(
            @RequestBody @Valid BlogPostRequest request){
            BlogPost created = service.create(request);
            return new ResponseEntity<>(created, HttpStatus.CREATED);

    }




}
