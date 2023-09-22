package com.hr.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.hr.model.User;



@Repository
public interface UserRepository extends MongoRepository<User, String> {
	Optional<User> findByUsername(String username);
User findByUserId(String id);
	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
}
