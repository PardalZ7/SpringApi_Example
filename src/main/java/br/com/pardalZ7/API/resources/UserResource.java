package br.com.pardalZ7.API.resources;

import br.com.pardalZ7.API.domain.User;
import br.com.pardalZ7.API.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/user")
@RestController
public class UserResource {

    @Autowired
    private UserService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> findById(@PathVariable Integer id) {

        return ResponseEntity.ok().body(service.findById(id));

    }

    @GetMapping()
    public ResponseEntity<User> findAll() {

        return ResponseEntity.ok().body(new User(1, "userName", "userEmail", "userPass"));

    }
}
