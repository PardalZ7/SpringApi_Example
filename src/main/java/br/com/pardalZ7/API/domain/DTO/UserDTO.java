package br.com.pardalZ7.API.domain.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Setter @Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO{

    private Integer id;
    private String name;
    private String email;

    @JsonIgnore
    private String pass;

}
