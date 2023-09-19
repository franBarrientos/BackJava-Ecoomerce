package com.treshermanitos.infrastructure.db.springdata.entities;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.treshermanitos.role.Role;
import com.treshermanitos.privilege.Privilege;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.treshermanitos.customer.Customer;

import jakarta.annotation.Nullable;

@Setter @Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity()
@Table(name = "`user`", uniqueConstraints = {
        @UniqueConstraint(name = "IDX_e12875dfb3b1d92d7d7c5377e2", columnNames = {"email"}),
})
public class User implements UserDetails {

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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "userId", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "roleId", referencedColumnName = "id"))
    private Set<Role> roles;

    @Column()
    @Nullable
    private String province;


    private Boolean state;

    @Column(columnDefinition = "TIMESTAMP", name = "createdAt")
    private Date createdAt;
    @Column(columnDefinition = "TIMESTAMP", name = "updatedAt")
    private Date updatedAt;

    @OneToOne(mappedBy = "user")
    private Customer customer;


    @PrePersist
    private void prePersist() {
        if (getState() == null) {
            setState(true);
        }
        setCreatedAt(new Date());
        setUpdatedAt(getCreatedAt());
    }

    @PreUpdate
    protected void onUpdate() {
        setUpdatedAt(new Date());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<String> rolesAndPrivileges =
                this.getRoles().stream().flatMap(role -> Stream.concat(
                                Stream.of(role.getName()),
                                role.getPrivileges().stream().map(Privilege::getName)))
                        .collect(Collectors.toList());


        return rolesAndPrivileges.stream().map(
                role -> new SimpleGrantedAuthority(role)
        ).collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return getEmail();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.getState();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.getState();
    }


}
