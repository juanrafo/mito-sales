package com.mitocode.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "user_data")
public class User {
    
    @Id
    @EqualsAndHashCode.Include
    private Integer idUser;

    @ManyToOne //FK
    @JoinColumn(name = "id_role", nullable = false, foreignKey = @ForeignKey(name = "fk_user_role"))
    private Role role;

    @Column(nullable = false, unique = true, length = 25)
    private String username;

    @Column(nullable = false, length = 60) //123 Bcrypt | hash |
    private String password;

    @Column(nullable = false)
    private boolean enabled;
}
