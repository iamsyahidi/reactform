package com.iam.reactform.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class UserDto {

    private Integer idUser;

    private String username;

    private String password;

    private Integer idBio;

    private String fullName;

    private String email;

    @JsonFormat( shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date birthDate;

    private String bio;
}
