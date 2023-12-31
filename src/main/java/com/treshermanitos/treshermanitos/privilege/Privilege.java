package com.treshermanitos.treshermanitos.privilege;

import com.treshermanitos.treshermanitos.role.Role;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Entity
@Setter @Getter
@Table(name = "`privilege`")
public class Privilege {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "privileges")
    private Collection<Role> roles;

    public Privilege(String name) {
        this.setName(name);
    }

    public Privilege() {

    }
}