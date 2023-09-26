package com.treshermanitos.api.infrastructure.db.springdata.entities;

import com.treshermanitos.api.infrastructure.db.springdata.entities.RoleEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Entity(name = "Privilege")
@Setter @Getter
@Table(name = "`privilege`")
public class PrivilegeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "privileges", fetch = FetchType.LAZY)
    private Collection<RoleEntity> roles;

    public PrivilegeEntity(String name) {
        this.setName(name);
    }

    public PrivilegeEntity() {

    }
}