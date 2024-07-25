package com.microservices.user.service.Service;

import com.microservices.user.service.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    //User Operations:

    //To create user
    User saveUser(User user);

    //Get All User
    List<User> getAllUsers();

    // Get User by UserId
    User getUserById(String id);

    //Delete User by Id
    void deleteUserById(String id);

    //Update User by Id
    void updateUser(String id, User updateUser);
}
