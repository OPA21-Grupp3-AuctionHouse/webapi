package com.sprint2.webapi.repository;

import java.util.Optional;

import com.sprint2.webapi.models.ERole;
import com.sprint2.webapi.models.Role;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByName(ERole name);
}
