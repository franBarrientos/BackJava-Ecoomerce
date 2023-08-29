package com.treshermanitos.treshermanitos.user;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.treshermanitos.treshermanitos.customer.Customer;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
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
@Table(name = "`user`", uniqueConstraints = {
        @UniqueConstraint(name = "IDX_e12875dfb3b1d92d7d7c5377e2", columnNames = { "email" }),
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

    @Column()
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column()
    @Nullable
    private String province;


    private Boolean state;

    @Column(columnDefinition = "TIMESTAMP", name = "createdAt")
    private Date createdAt;
    @Column(columnDefinition = "TIMESTAMP", name = "updatedAt")
    private Date updatedAt;

    @OneToOne(mappedBy = "user")
    @JsonIgnore
    private Customer customer;


    @PrePersist
    private void prePersist() {
        if (getRole() == null) {
            setRole(Role.USER);
        }
        if(getState() == null){
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
        return List.of(new SimpleGrantedAuthority(role.name()), new SimpleGrantedAuthority("ROLE_"+role.name()));
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
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
