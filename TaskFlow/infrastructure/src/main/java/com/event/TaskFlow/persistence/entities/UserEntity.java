package com.event.TaskFlow.persistence.entities;


import jakarta.persistence.*;
import jakarta.validation.Valid;

import java.io.Serializable;

@Entity
@Table(name = "USER")
public class UserEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userSeq")
    @SequenceGenerator(name = "userSeq", sequenceName = "user_id_seq", allocationSize = 1)
    private Long id;

    @Embedded
    @Valid
    @AttributeOverride(name = "userName", column = @Column(name = "USERNAME", nullable = false, unique = true))
    private UserNameEntity username;

    @Embedded
    @Valid
    @AttributeOverride(name = "password", column = @Column(name = "PASSWORD", nullable = false))
    private PasswordEntity password;

    @Embedded
    @Valid
    @AttributeOverride(name = "email", column = @Column(name = "EMAIL", nullable = false))
    private EmailEntity email;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ROLE_ID", nullable = false) //Foreign key to RoleEntity
    private RoleEntity role;

    protected UserEntity() {
    }

    public UserEntity(Long id, UserNameEntity username, PasswordEntity password, EmailEntity email, RoleEntity role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public UserNameEntity getUsername() {
        return username;
    }

    public PasswordEntity getPassword() {
        return password;
    }

    public EmailEntity getEmail() {
        return email;
    }

    public RoleEntity getRole() {
        return role;
    }
}
