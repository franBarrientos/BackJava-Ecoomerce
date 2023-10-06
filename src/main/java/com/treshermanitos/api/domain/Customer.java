package com.treshermanitos.api.domain;

import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer {
    private Long id;

    private Integer dni;

    private String addres;

    private String phone;

    private User user;

    private List<Purchase> purchases;

    private Date createdAt;

    private Date updatedAt;

}
