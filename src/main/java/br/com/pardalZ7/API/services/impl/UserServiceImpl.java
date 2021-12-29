package br.com.pardalZ7.API.services.impl;

import br.com.pardalZ7.API.domain.DTO.UserDTO;
import br.com.pardalZ7.API.domain.User;
import br.com.pardalZ7.API.repositories.UserRepository;
import br.com.pardalZ7.API.services.UserService;
import br.com.pardalZ7.API.services.exceptions.DataIntegratyViolationException;
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
    public User create(UserDTO userDto) {
        findByEmail(userDto);
        return repository.save(mapper.map(userDto, User.class));
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
    public User update(UserDTO userDto) {
        findByEmail(userDto);
        return repository.save(mapper.map(userDto, User.class));
    }

    private void findByEmail(UserDTO userDto) {
        Optional<User> user = repository.findByEmail(userDto.getEmail());
        if (user.isPresent() && !user.get().getId().equals(userDto.getId()))
            throw new DataIntegratyViolationException("Email already registered");
    }

}
