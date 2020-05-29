package com.iam.reactform.model.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "t_user")
@Entity

public class User {

    @Id
    @GeneratedValue( generator = "sequence_user", strategy = GenerationType.AUTO )
    @Column( name = "id_user", nullable = false )
    private Integer idUser;

    @Column(name = "username", columnDefinition = "varchar(50)", nullable = false)
    private String username;

    @Column(name = "password", columnDefinition = "varchar(255)", nullable = false)
    private String password;

    @OneToOne
    @JoinColumn(name = "id_bio", nullable = false, columnDefinition = "integer")
    private Biodata biodata;

}
