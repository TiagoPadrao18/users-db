package com.usersDb.usersDb.model;
import jakarta.persistence.*;
import lombok.*;


import java.io.Serializable;

@Entity
@Table(name = "user_table")
@Data
@NoArgsConstructor

public class User implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String password;



}
