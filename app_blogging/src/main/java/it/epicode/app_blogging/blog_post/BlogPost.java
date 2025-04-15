package it.epicode.app_blogging.blog_post;


import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "posts")
public class BlogPost {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String categoria;
    private String titolo;
    private String cover;

    @Column(columnDefinition = "TEXT")
    private String contenuto;

    private int tempoDiLettura;

}
