package br.com.pardalZ7.API.config;

import br.com.pardalZ7.API.domain.User;
import br.com.pardalZ7.API.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile("local")
public class LocalConfig {

    @Autowired
    private UserRepository repository;

    @Bean
    public void startDB(){

        User u01 = User.builder().id(null).email("email01").name("name01").pass("pass01").build();
        User u02 = User.builder().id(null).email("email02").name("name02").pass("pass02").build();

        repository.saveAll(List.of(u01, u02));

    }

}
