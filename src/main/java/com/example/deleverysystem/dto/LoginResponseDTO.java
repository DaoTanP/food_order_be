package com.example.deleverysystem.dto;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class LoginResponseDTO {
    @Setter
    @Getter
    private String jwt ;

    public LoginResponseDTO(String jwt) {
        this.jwt = jwt;
    }
}
