package com.treshermanitos.api.infrastructure.db.springdata.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Set;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Category")
@Table(name = "`category`")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String img;

    private Date createdAt;

    private Date updatedAt;

    private Boolean state;

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


}
