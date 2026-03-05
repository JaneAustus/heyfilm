package com.example.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.model.User;

@Repository
public interface UserInterFace extends JpaRepository<User, String> {

}
