package com.auction.user.dto;


import jakarta.validation.constraints.NotNull;

public record UsersRequest(
        Long userId,
        String username,
        String password,
        @NotNull(message = "User's first name is required")
        String firstName,
        @NotNull(message = "User's last name is required")
        String lastName,
        @NotNull(message = "User's email is required")
        String emailAddress,
        String telephone) {

}
