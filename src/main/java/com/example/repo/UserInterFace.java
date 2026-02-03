package com.example.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.example.model.User;
@EnableJpaRepositories
@Repository
@Component
public interface UserInterFace extends JpaRepository<User, Integer> {

}
