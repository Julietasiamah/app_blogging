package it.epicode.app_blogging.blog_post;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//qui andrò a riportami le validazioni
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogPostRequest {
    @NotBlank (message = "Inserisci una categoria obligatoria")
    private String categoria;

    @NotBlank (message = "Inserisci un titolo obligatorio")
    private String titolo;

    @NotBlank (message = "Inserisci un contenuto obligatorio")
    private String contenuto;

    @Min(value = 1, message = "Il tempo di lettura deve essere almento 1 minuto")
    private int tempoDiLettura;

    @NotNull (message = "L'ID dell'autore e' obbligatorio")
    private Long autoreId;
}
