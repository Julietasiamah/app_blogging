package  it.epicode.app_blogging.blog_post;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import it.epicode.app_blogging.blog_autore.Autore;
import it.epicode.app_blogging.blog_autore.AutoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class BlogPostService {

    @Autowired
    private BlogPostRepository repository;

    @Autowired
    private AutoreRepository autoreRepository;

    @Autowired
    private Cloudinary cloudinary;

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

    public BlogPost create(BlogPostRequest request) {
        Autore autore = autoreRepository.findById(request.getAutoreId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Autore non trovato"));


        BlogPost post = new BlogPost();
        post.setCategoria(request.getCategoria());
        post.setTitolo(request.getTitolo());
        post.setContenuto(request.getContenuto());
        post.setTempoDiLettura(request.getTempoDiLettura());
        post.setCover("https://picsum.photos/200/300");
        post.setAutore(autore);
        return repository.save(post);
    }

    //paginazione e ordinamento
    public Page <BlogPost> getAllPaged(int page, int size, String sortBy){
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return repository.findAll(pageable);
    }

    //upload image
    public String uploadImage (MultipartFile file) {
        try {

            Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            return (String) uploadResult.get("secure_url");
        } catch (IOException e) {
            throw new RuntimeException("Impossibile caricare l'immagine", e);
        }
    }

}
