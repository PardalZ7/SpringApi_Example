package br.com.pardalZ7.API.services.impl;

import br.com.pardalZ7.API.domain.DTO.UserDTO;
import br.com.pardalZ7.API.domain.User;
import br.com.pardalZ7.API.repositories.UserRepository;
import br.com.pardalZ7.API.services.exceptions.DataIntegratyViolationException;
import br.com.pardalZ7.API.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

class UserServiceImplTest {

    public static final Integer ID = 1;
    public static final String EMAIL = "email01";
    public static final String PASS = "pass01";
    @InjectMocks
    private UserServiceImpl service;

    @Mock
    private UserRepository repository;

    @Mock
    private ModelMapper mapper;

    private User user;
    private UserDTO userDTO;
    private Optional<User> optionalUser;

    UserServiceImplTest() {
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.startData();
    }

    @Test
    void whenCreateThenReturnsSuccess() {
        when(repository.save(any())).thenReturn(user);

        User user = service.create(userDTO);

        assertNotNull(user);
        assertEquals(User.class, user.getClass());
        assertEquals(ID, user.getId());
        assertEquals(EMAIL, user.getEmail());
        assertEquals(PASS, user.getPass());

    }

    @Test
    void whenCreateThenReturnsDataIntegratyViolationException() {
        when(repository.findByEmail(anyString())).thenReturn(optionalUser);

        try {
            optionalUser.get().setId(1);
            service.create(userDTO);
        } catch (Exception ex) {
            assertEquals(DataIntegratyViolationException.class, ex.getClass());
        }
    }

    @Test
    void whenFindByIdThenReturnAnUserInstance() {
        when(repository.findById(anyInt())).thenReturn(this.optionalUser);

        User response = service.findById(ID);

        assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(PASS, response.getPass());
    }

    @Test
    void whenFindByIdThenReturnsAnObjectNotFoundException(){
        when(repository.findById(anyInt())).thenThrow(new ObjectNotFoundException("User not found"));

        try {
            service.findById(ID);
        } catch (Exception ex){
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals("User not found", ex.getMessage());
        }
    }

    @Test
    void whenFinAllThenReturnsAnListOfUsers() {
        when(repository.findAll()).thenReturn(List.of(user));

        List<User> response = service.findAll();

        assertNotNull(response);
        assertEquals(1, response.size());

        assertEquals(User.class, response.get(0).getClass());
        assertEquals(ID, response.get(0).getId());
        assertEquals(EMAIL, response.get(0).getEmail());
        assertEquals(PASS, response.get(0).getPass());
    }

    @Test
    void whenUpdateThenReturnsSuccess() {
        when(repository.save(any())).thenReturn(this.user);
        when(repository.findById(anyInt())).thenReturn(this.optionalUser);

        User response = service.update(userDTO);

        assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(PASS, response.getPass());

    }

    @Test
    void whenUpdateThenReturnsAnObjectNotFoundException() {
        when(repository.save(any())).thenReturn(this.user);

        try {
            service.update(userDTO);
        } catch (Exception ex) {
            assertEquals(ObjectNotFoundException.class, ex.getClass());
        }

    }

    @Test
    void whenUpdateThenReturnsAnDataIntegratyViolationException() {
        when(repository.findByEmail(any())).thenReturn(optionalUser);
        when(repository.findById(anyInt())).thenReturn(this.optionalUser);

        try {
            optionalUser.get().setId(9);
            service.update(userDTO);
        } catch (Exception ex) {
            assertEquals(DataIntegratyViolationException.class, ex.getClass());
        }

    }

    @Test
    void deleteById() {
    }

    private void startData(){
        this.user = User.builder().id(ID).email(EMAIL).pass(PASS).build();
        this.userDTO = UserDTO.builder().id(ID).email(EMAIL).pass(PASS).build();
        this.optionalUser = Optional.of(User.builder().id(ID).email(EMAIL).pass(PASS).build());
    }

}