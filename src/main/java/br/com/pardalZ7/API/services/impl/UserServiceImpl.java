package br.com.pardalZ7.API.services.impl;

import br.com.pardalZ7.API.domain.DTO.UserDTO;
import br.com.pardalZ7.API.domain.User;
import br.com.pardalZ7.API.repositories.UserRepository;
import br.com.pardalZ7.API.services.UserService;
import br.com.pardalZ7.API.services.exceptions.DataIntegrityViolationException;
import br.com.pardalZ7.API.services.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public User create(UserDTO userDTO) {
        findByEmail(userDTO);
        return repository.save(mapper.map(userDTO, User.class));
    }

    @Override
    public User findById(Integer id) {
        Optional<User> user = repository.findById(id);
        return user.orElseThrow(() -> new ObjectNotFoundException("User not found"));
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public User update(UserDTO userDTO) {
        findById(userDTO.getId());
        findByEmail(userDTO);
        return repository.save(mapper.map(userDTO, User.class));
    }

    private void findByEmail(UserDTO userDTO) {
        Optional<User> user = repository.findByEmail(userDTO.getEmail());
        if (user.isPresent() && !user.get().getId().equals(userDTO.getId()))
            throw new DataIntegrityViolationException("Email already registered");
    }

    @Override
    public void deleteById(Integer id) {
        findById(id);
        repository.deleteById(id);
    }
}
