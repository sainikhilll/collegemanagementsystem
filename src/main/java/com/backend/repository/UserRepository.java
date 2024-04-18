package com.backend.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.backend.domain.User;


@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	
	Optional<User> findByEmailAndPassword(String email,String password);
	
	Optional<User> findByEmail(String email);

}
