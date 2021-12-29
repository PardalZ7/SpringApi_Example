package br.com.pardalZ7.API.resources;

import br.com.pardalZ7.API.domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/user")
@RestController
public class UserResource {

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> findById(@PathVariable Integer id) {

        return ResponseEntity.ok().body(new User(1, "userName", "userEmail", "userPass"));

    }

    @GetMapping()
    public ResponseEntity<User> findAll() {

        return ResponseEntity.ok().body(new User(1, "userName", "userEmail", "userPass"));

    }
}
