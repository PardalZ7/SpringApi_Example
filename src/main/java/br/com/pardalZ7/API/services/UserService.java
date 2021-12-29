package br.com.pardalZ7.API.services;

import br.com.pardalZ7.API.domain.User;

import java.util.List;

public interface UserService {

    User findById(Integer id);
    List<User> findAll();

}
