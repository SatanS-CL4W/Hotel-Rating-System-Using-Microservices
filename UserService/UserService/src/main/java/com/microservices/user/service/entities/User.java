package com.microservices.user.service.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.*;

import java.util.ArrayList;

@Entity
@Table(name = "micro-users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    @Column(name = "ID")
    private String userId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "ABOUT")
    private String about;

    @Transient
    private List<Rating> ratings = new ArrayList<>();
}
