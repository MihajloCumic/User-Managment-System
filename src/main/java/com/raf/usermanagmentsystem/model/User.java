package com.raf.usermanagmentsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;


import java.util.HashSet;

import java.util.Set;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    @NotBlank(message = "First name must be provided.")
    private String firstName;
    @Column
    @NotBlank(message = "Last name must be provided.")
    private String lastName;
    @Column(unique = true)
    @Email(message = "Incorrect form of an email.")
    private String email;
    @Column
    @JsonIgnore
    @NotBlank(message = "Password must be provided.")
    @Min(value = 4, message = "Password must be at least 4 characters.")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_privileges", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "privilege_id", referencedColumnName = "id"))
    private Set<Privilege> privileges = new HashSet<>();
}
