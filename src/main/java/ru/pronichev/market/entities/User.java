package ru.pronichev.market.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "name", length = 36)
    private String name;

    @Column(name = "last_name", length = 36)
    private String lastName;

    @Column(name = "second_name", length = 36)
    private String secondName;

    @Column(name = "phone", length = 14, nullable = false, unique = true)
    private String phone;

    @Column(name = "login", length = 35, nullable = false, unique = true)
    private String login;

    @Column(name = "password", length = 35, nullable = false)
    private String password;

    @Column(name = "role", length = 35, nullable = false, unique = true)
    private String role;
}