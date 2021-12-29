package br.com.pardalZ7.API.resources;

import br.com.pardalZ7.API.domain.DTO.UserDTO;
import br.com.pardalZ7.API.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping(value = "/user")
@RestController
public class UserResource {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private UserService service;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(mapper.map(service.findById(id), UserDTO.class));
    }

    @GetMapping()
    public ResponseEntity<List<UserDTO>> findAll() {
        List<UserDTO> usersDto = service.findAll().stream().map(x -> mapper.map(x, UserDTO.class)).toList();
        return ResponseEntity.ok().body(usersDto);
    }
}
