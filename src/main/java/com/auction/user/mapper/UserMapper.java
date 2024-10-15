package com.auction.user.mapper;

import com.auction.user.dto.UsersResponse;
import com.auction.user.model.Users;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    public UsersResponse fromUser(Users user) {
        return new UsersResponse(
                user.getUserId(),
                user.getUsername(),
                user.getTelephone(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmailAddress()
                );
    }
}

