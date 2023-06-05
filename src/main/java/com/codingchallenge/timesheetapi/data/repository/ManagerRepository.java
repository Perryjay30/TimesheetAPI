package com.codingchallenge.timesheetapi.data.repository;

import com.codingchallenge.timesheetapi.data.model.Manager;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ManagerRepository extends MongoRepository<Manager, String> {
    Optional<Manager> findUserByRole(String userRole);
}
