package com.example.grpc.mapper;

import com.example.grpc.Person;
import com.example.grpc.model.PersonApp;
import org.springframework.stereotype.Component;

@Component
public class PersonMapper {
    public PersonApp requestToPersonCreate(Person.PersonCreateRequest request) {
        return PersonApp.builder()
                .username(request.getUsername())
                .age(request.getAge())
                .email(request.getEmail())
                .password(request.getPassword())
                .build();
    }

    public Person.PersonResponse personToResponse(PersonApp personApp) {
        return Person.PersonResponse.newBuilder()
                .setAge(personApp.getAge())
                .setEmail(personApp.getEmail())
                .setId(personApp.getId())
                .setPassword(personApp.getPassword())
                .setUsername(personApp.getUsername())
                .build();
    }

    public PersonApp requestToPersonUpdate(Person.PersonUpdateRequest request) {
        return PersonApp.builder()
                .age(request.getAge())
                .username(request.getUsername())
                .id(request.getId())
                .password(request.getPassword())
                .email(request.getEmail())
                .build();
    }
}
