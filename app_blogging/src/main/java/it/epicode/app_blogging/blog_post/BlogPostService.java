package it.epicode.app_blogging.blog_post;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class BlogPostService {

    @Autowired
    private BlogPostRepository repository;

    public BlogPost create (BlogPost post) {
        post.setCover("https://picsum.photos/200/300");
        return repository.save(post);
    }

    public List<BlogPost> getAll(){
        return repository.findAll();
    }

    public Optional<BlogPost> getById(Long id) {
        return repository.findById(id);
    }

    public BlogPost update(Long id, BlogPost post){
        post.setId(id);
        return repository.save(post);
    }

    public void delete(Long id){
        repository.deleteById(id);
    }
}
