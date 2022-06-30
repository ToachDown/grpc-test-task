package com.example.grpc.repository;

import com.example.grpc.model.PersonApp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<PersonApp, Long> {
}
