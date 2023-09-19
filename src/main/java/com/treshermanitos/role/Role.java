package com.treshermanitos.role;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.treshermanitos.privilege.Privilege;
import com.treshermanitos.infrastructure.db.springdata.entities.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

@Entity
@Setter @Getter
@NoArgsConstructor
@Table(name = "`role`")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private Collection<User> users;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "roles_privileges",
            joinColumns = @JoinColumn(
                    name = "roleId", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "privilegeId", referencedColumnName = "id"))
    private Collection<Privilege> privileges;

    public Role(String name, Collection<Privilege> privileges) {
        this.setName(name);
        this.setPrivileges(privileges);
    }
}