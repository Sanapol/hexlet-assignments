package exercise.controller;

import exercise.dto.AuthorDTO;
import exercise.dto.AuthorCreateDTO;
import exercise.dto.AuthorUpdateDTO;
import exercise.service.AuthorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorsController {

    @Autowired
    private AuthorService authorService;

    // BEGIN
    @GetMapping(path = "")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<AuthorDTO>> index() {
        var index = authorService.getAll();
        return ResponseEntity.ok().body(index);
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<AuthorDTO> show(@PathVariable long id) {
        var show = authorService.findById(id);
        return ResponseEntity.ok().body(show);
    }

    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AuthorDTO> create(@Valid @RequestBody AuthorCreateDTO data) {
        var create = authorService.create(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(create);
    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<AuthorDTO> update(@Valid @RequestBody AuthorUpdateDTO data, @PathVariable long id) {
        var update = authorService.update(data, id);
        return ResponseEntity.ok().body(update);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id) {
        authorService.delete(id);
    }
    // END
}
