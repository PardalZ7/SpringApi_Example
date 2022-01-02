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
    void findById() {
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