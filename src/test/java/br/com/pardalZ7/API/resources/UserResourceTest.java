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
import org.springframework.http.ResponseEntity;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

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
        assertNotNull(response.getBody());
        assertEquals(UserDTO.class, response.getBody().getClass());

        assertEquals(ID, this.user.getId());
        assertEquals(NAME, this.user.getName());
        assertEquals(EMAIL, this.user.getEmail());
        assertEquals(PASS, this.user.getPass());

    }

    @Test
    void findAll() {
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}