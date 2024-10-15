package com.auction.user.service.impl;

import com.auction.user.dto.UsersRequest;
import com.auction.user.dto.UsersResponse;
import com.auction.user.exception.UserNotFoundException;
import com.auction.user.mapper.UserMapper;
import com.auction.user.model.Users;
import com.auction.user.repostory.UsersRepository;
import io.micrometer.common.util.StringUtils;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;
//import org.keycloak.admin.client.Keycloak;

@Service
@Slf4j
@RequiredArgsConstructor
public class UsersServiceImpl {

    private final UsersRepository usersRepository;
   private final UserMapper userMapper;
    //@Autowired
    //private final Keycloak keycloak;
    //private final KeycloakConfig keycloakConfig;

    //@Value("${keycloak.realm}")
    //private String realm;

    public UsersResponse createUser(UsersRequest usersRequestBody) {



        Users users = Users.builder()
                .username(usersRequestBody.username())
                .emailAddress(usersRequestBody.emailAddress())
                .firstName(usersRequestBody.firstName())
                .lastName(usersRequestBody.lastName())
                .telephone(usersRequestBody.telephone())
                .build();

        usersRepository.save(users);
        log.info("User with id {} is saved. ", users.getUserId());
        /*
        createUserInKeycloak(usersRequestBody);

        createUserInKeycloak(usersRequestBody);*/
        return new UsersResponse(users.getUserId(), users.getUsername(), users.getTelephone(), users.getFirstName(), users.getLastName(), users.getEmailAddress());
    }
    /*
    private void createUserInKeycloak(UsersRequest userRequest) {
        //Keycloak keycloak = KeycloakProvider();

        UserRepresentation keycloakUser = new UserRepresentation();
        keycloakUser.setUsername(userRequest.username());
        keycloakUser.setEmail(userRequest.emailAddress());
        keycloakUser.setFirstName(userRequest.firstName());
        keycloakUser.setLastName(userRequest.lastName());
        keycloakUser.setEnabled(true);

        CredentialRepresentation credentials = new CredentialRepresentation();
        credentials.setValue(userRequest.password());
        credentials.setType(CredentialRepresentation.PASSWORD);
        credentials.setTemporary(false);

        keycloakUser.setCredentials(List.of(credentials));
        UsersResource usersResource = getUsersResource();
        Response response = usersResource.create(keycloakUser);

        log.info("Status Code " + response.getStatus());

        if(!Objects.equals(201,response.getStatus())){

            throw new RuntimeException("Status code "+response.getStatus());
        }

        log.info("User is created");

        List<UserRepresentation> userRepresentations = usersResource.searchByUsername(userRequest.username(), true);
        UserRepresentation userRepresentation1 = userRepresentations.get(0);
        sendVerificationEmail(userRepresentation1.getId());

    }*/
    /*
   // @Override
    public void sendVerificationEmail(String userId) {

        UsersResource usersResource = getUsersResource();
        usersResource.get(userId).sendVerifyEmail();

    }*/
/*
   // @Override
    public void deleteUser(String userId) {
        UsersResource usersResource = getUsersResource();
        usersResource.delete(userId);
    }*/
/*
   // @Override
    public void forgotPassword(String username) {

        UsersResource usersResource = getUsersResource();
        List<UserRepresentation> userRepresentations = usersResource.searchByUsername(username, true);
        UserRepresentation userRepresentation1 = userRepresentations.get(0);
        UserResource userResource = usersResource.get(userRepresentation1.getId());
        userResource.executeActionsEmail(List.of("UPDATE_PASSWORD"));


    }*/
/*
   // @Override
    public UserResource getUser(String userId) {
        UsersResource usersResource = getUsersResource();
        return usersResource.get(userId);
    }*/
/*
    //@Override
    public List<RoleRepresentation> getUserRoles(String userId) {


        return getUser(userId).roles().realmLevel().listAll();
    }
*/
/*
    //@Override
    public List<GroupRepresentation> getUserGroups(String userId) {


        return getUser(userId).groups();
    }*/

/*
    private UsersResource getUsersResource() {
      return   keycloak.realm(realm)
                .users();
    }*/

    public List<UsersResponse> getAllUsers() {
       return usersRepository.findAll()
               .stream()
               .map(users -> new UsersResponse(users.getUserId(), users.getUsername(), users.getTelephone(), users.getFirstName(), users.getLastName(), users.getEmailAddress()))
               .toList();
    }

    public UsersResponse makeUserSeller(Long userId) {
        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        user.setSeller(true);
        usersRepository.save(user);
        log.info("User with id {} is now marked as a seller.", userId);

        return new UsersResponse(user.getUserId(), user.getUsername(), user.getTelephone(), user.getFirstName(), user.getLastName(), user.getEmailAddress());
    }


    public UsersResponse makeUserBuyer(Long userId) {
        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        user.setBuyer(true);
        usersRepository.save(user);
        log.info("User with id {} is now marked as a buyer.", userId);

        return new UsersResponse(user.getUserId(), user.getUsername(),  user.getTelephone(), user.getFirstName(), user.getLastName(), user.getEmailAddress());
    }


    public boolean isUserSeller(Long userId) {
        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        return user.isSeller();
    }

    public boolean isUserBuyer(Long userId) {
        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        return user.isBuyer();
    }

    public UsersResponse updateUser(UsersRequest requestBody) {
        var user = usersRepository.findById(requestBody.userId())
                .orElseThrow(() -> new UserNotFoundException(
                   format("Can not update user with the Id provide. No user found with the provided Id:: %s", requestBody.userId())

                ));
        mergeUser(user, requestBody);
        usersRepository.save(user);

        return new UsersResponse(user.getUserId(),  user.getUsername(),  user.getTelephone(), user.getFirstName(), user.getLastName(), user.getEmailAddress());
    }

    private void mergeUser(Users user, UsersRequest requestBody) {
        if (StringUtils.isNotBlank(requestBody.firstName())) {
            user.setFirstName(requestBody.firstName());
        }
        if (StringUtils.isNotBlank(requestBody.lastName())) {
            user.setLastName(requestBody.lastName());
        }
        if (StringUtils.isNotBlank(requestBody.username())) {
            user.setUsername(requestBody.username());
        }
        if (StringUtils.isNotBlank(requestBody.emailAddress())) {
            user.setEmailAddress(requestBody.emailAddress());
        }
        if (StringUtils.isNotBlank(requestBody.telephone())) {
            user.setTelephone(requestBody.telephone());
        }
    }

    public Boolean existByUserId(Long userId) {
        return  usersRepository.findById(userId)
                .isPresent();
    }

    public UsersResponse findUserByUserId(Long userId) {

        return usersRepository.findById(userId)
                .map(userMapper::fromUser)
                .orElseThrow(() -> new UserNotFoundException(
                        format("Can not find user with the given Id %s", userId)
                ));
    }

    public void deleteUserByUserId(Long userId) {
        usersRepository.deleteById(userId);
    }
}
