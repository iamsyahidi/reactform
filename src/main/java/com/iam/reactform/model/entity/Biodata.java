package com.iam.reactform.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Table(name = "t_biodata")
@Entity
public class Biodata {

    @Id
    @GeneratedValue( generator = "sequence_bio", strategy = GenerationType.AUTO )
    @Column(name = "id_bio", nullable = false)
    private Integer idBio;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "email", nullable = false)
    private String email;

    @Temporal(TemporalType.DATE)
    @Column(name = "birth_date", nullable = true)
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd")
    private Date birthDate;

    @Column(name = "bio", length = 512, nullable = true)
    private String bio;

}
