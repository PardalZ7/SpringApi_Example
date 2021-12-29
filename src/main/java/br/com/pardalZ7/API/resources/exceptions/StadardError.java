package br.com.pardalZ7.API.resources.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@Getter @Setter
@AllArgsConstructor
public class StadardError {

    private LocalDateTime timeStamp;
    private Integer status;
    private String error;
    private String path;

}
