package it.epicode.app_blogging.blog_post;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;

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

}
