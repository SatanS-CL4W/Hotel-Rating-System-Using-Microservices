package com.microservices.user.service.repositories;

import com.microservices.user.service.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, String> {

}
