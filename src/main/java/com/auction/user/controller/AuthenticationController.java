package com.auction.user.controller;


import com.auction.user.dto.UsersRequest;
import com.auction.user.dto.UsersResponse;
import com.auction.user.service.impl.UsersServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthenticationController {

    private final UsersServiceImpl usersServiceImpl;


    @PostMapping("/create-user")
    //@PreAuthorize("hasRole('client_user') or hasRole('client_admin')")
    @ResponseStatus(HttpStatus.CREATED)
    public UsersResponse registerUser(@RequestBody @Valid UsersRequest requestBody) {
        try {
            return usersServiceImpl.createUser(requestBody);
        } catch (Exception e) {
            log.error("An error occurred while creating the user: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to create user", e);
        }
    }

    @PutMapping("/update-user")
    //@PreAuthorize("hasRole('client_user') or hasRole('client_admin')")
    @ResponseStatus(HttpStatus.CREATED)
    public UsersResponse updateUser(@RequestBody @Valid UsersRequest requestBody) {
        return usersServiceImpl.updateUser(requestBody);
    }

    @GetMapping("/exits/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Boolean> existByUserId(@PathVariable("userId") Long userId) {

        return  ResponseEntity.ok(usersServiceImpl.existByUserId(userId));
    }

    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UsersResponse> findUserByUserId(@PathVariable("userId") Long userId) {
        return  ResponseEntity.ok(usersServiceImpl.findUserByUserId(userId));
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> deleteUserByUserId(@PathVariable("userId") Long userId) {
       usersServiceImpl.deleteUserByUserId(userId);
       return ResponseEntity.accepted().build();
    }



    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public String getUsers() {

        return  "users auth api ";
    }
}
