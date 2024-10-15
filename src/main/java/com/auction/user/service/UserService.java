package com.auction.user.service;


import com.auction.user.dto.UsersRequest;
//import org.keycloak.admin.client.resource.UserResource;
//import org.keycloak.representations.idm.GroupRepresentation;
//import org.keycloak.representations.idm.RoleRepresentation;


public interface UserService {

    void createUser(UsersRequest usersRequest);
    void sendVerificationEmail(String userId);
    void deleteUser(String userId);
    void forgotPassword(String username);
    //UserResource getUser(String userId);
    //List<RoleRepresentation> getUserRoles(String userId);
    //List<GroupRepresentation> getUserGroups(String userId);
}
