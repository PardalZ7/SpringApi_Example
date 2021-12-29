package br.com.pardalZ7.API.services.impl;

import br.com.pardalZ7.API.domain.User;
import br.com.pardalZ7.API.repositories.UserRepository;
import br.com.pardalZ7.API.services.UserService;
import br.com.pardalZ7.API.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public User findById(Integer id) {

        Optional<User> user = repository.findById(id);
        return user.orElseThrow(() -> new ObjectNotFoundException("User not found"));

    }

}
