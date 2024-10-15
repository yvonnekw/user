package com.auction.user.model;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Setter
@Getter
@Table(name="users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
   // @Column(unique = true, nullable = false)
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String telephone;
    // User can act as a buyer or seller
    private boolean isSeller;
    private boolean isBuyer;
}
