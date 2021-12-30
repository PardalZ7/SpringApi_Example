package br.com.pardalZ7.API.domain.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Setter @Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO{

    private Integer id;
    private String name;
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String pass;

}
