package it.epicode.app_blogging.global_handler;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice //annotazione che intercetta tutte le eccezioni lanciate dai controller REST
//Funziona come un "filtro" globale per gestire errori in modo centralizzato.
public class GlobalExceptionHandler {

    //Quando nel codice viene lanciata un'eccezione EntityNotFoundException, questo metodo la intercetta.
    //
    //Restituisce un errore HTTP 404 Not Found con il messaggio contenuto nell'eccezione.
    @ExceptionHandler (EntityNotFoundException.class)
    public ResponseEntity <String> handleNotFound (EntityNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    //Questo gestisce qualsiasi altra eccezione generica (Exception.class) che non sia già gestita da un altro handler.
    //Restituisce un errore HTTP 500 Internal Server Error con un messaggio di errore personalizzato.

    @ExceptionHandler (Exception.class)
    public ResponseEntity <String> handleGeneric (Exception ex){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }

    @ExceptionHandler (MethodArgumentNotValidException.class)
    public ResponseEntity <List<String>> handleValidationError (MethodArgumentNotValidException ex){
        List <String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map (err -> err.getField() + ": " + err.getDefaultMessage())
                .toList();
        return ResponseEntity.badRequest().body(errors);


    }
}
//Questo ti evita di scrivere try/catch ovunque nei controller.
//
//Centralizza la gestione degli errori REST.
//
//Migliora leggibilità e manutenzione.