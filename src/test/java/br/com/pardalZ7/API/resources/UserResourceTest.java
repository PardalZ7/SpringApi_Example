package br.com.pardalZ7.API.resources;

import br.com.pardalZ7.API.domain.DTO.UserDTO;
import br.com.pardalZ7.API.domain.User;
import br.com.pardalZ7.API.services.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserResourceTest {

    public static final Integer ID = 1;
    public static final String EMAIL = "email01";
    public static final String PASS = "pass01";
    public static final String NAME = "name";

    @InjectMocks
    private UserResource resource;

    @Mock
    private UserServiceImpl service;

    @Mock
    private ModelMapper mapper;

    private User user;
    private UserDTO userDTO;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);
        this.startData();
    }

    private void startData(){
        this.user = User.builder().id(ID).email(EMAIL).pass(PASS).name(NAME).build();
        this.userDTO = UserDTO.builder().id(ID).email(EMAIL).pass(PASS).name(NAME).build();
    }

    @Test
    void whenFindByIdThenReturnSuccess() {

        when(service.findById(ID)).thenReturn(this.user);
        when(mapper.map(this.user, UserDTO.class)).thenReturn(this.userDTO);

        ResponseEntity<UserDTO> response = resource.findById(ID);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(UserDTO.class, response.getBody().getClass());

        assertEquals(ID, this.user.getId());
        assertEquals(NAME, this.user.getName());
        assertEquals(EMAIL, this.user.getEmail());
        assertEquals(PASS, this.user.getPass());

    }

    @Test
    void whenFindAllThenReturnAnListOfUserDTO() {

        when(service.findAll()).thenReturn(List.of(this.user));
        when(mapper.map(this.user, UserDTO.class)).thenReturn(this.userDTO);

        ResponseEntity<List<UserDTO>> response = resource.findAll();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());

        UserDTO user01 = response.getBody().get(0);
        assertEquals(UserDTO.class, user01.getClass());

        assertEquals(ID, user01.getId());
        assertEquals(NAME, user01.getName());
        assertEquals(EMAIL, user01.getEmail());
        assertEquals(PASS, user01.getPass());

    }

    @Test
    void whenCreateThenReturnSuccess() {

        when(service.create(this.userDTO)).thenReturn(this.user);

        ResponseEntity<UserDTO> response = resource.create(this.userDTO);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getHeaders().get("Location"));

    }

    @Test
    void whenUpdateThenReturnSuccess() {

        when(service.update(this.userDTO)).thenReturn(this.user);
        when(mapper.map(this.user, UserDTO.class)).thenReturn(this.userDTO);

        ResponseEntity<UserDTO> response = resource.update(ID, this.userDTO);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        UserDTO localUser = response.getBody();
        assertEquals(UserDTO.class, localUser.getClass());

        assertEquals(ID, localUser.getId());
        assertEquals(NAME, localUser.getName());
        assertEquals(EMAIL, localUser.getEmail());
        assertEquals(PASS, localUser.getPass());

    }

    @Test
    void whenDeleteThenReturnSuccess() {

        doNothing().when(service).deleteById(ID);
        ResponseEntity<Void> response = resource.delete(ID);

        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(service, times(1)).deleteById(ID);

    }
}