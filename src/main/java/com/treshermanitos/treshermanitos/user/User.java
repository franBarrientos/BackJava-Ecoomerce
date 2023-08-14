package com.treshermanitos.treshermanitos.user;

import java.util.Date;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity()
@Table(name = "user", uniqueConstraints = {
        @UniqueConstraint(name = "IDX_e12875dfb3b1d92d7d7c5377e2", columnNames = { "email" }),
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstName")
    private String firstName;

    @Nullable
    @Column(name = "lastName")
    private String lastName;

    @Column()
    private String email;

    @Column()
    private String password;

    @Column()
    private String city;

    @Column()
    @Nullable
    private Integer age;

    @Column()
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column()
    @Nullable
    private String province;

    @Column(columnDefinition = "TIMESTAMP", name = "createdAt")
    private Date createdAt;
    @Column(columnDefinition = "TIMESTAMP", name = "updatedAt")
    private Date updatedAt;

    @PrePersist
    private void prePersist() {
        if (getRole() == null) {
            setRole(Role.USER);
        }
        setCreatedAt(new Date());
        setUpdatedAt(getCreatedAt());
    }
}
