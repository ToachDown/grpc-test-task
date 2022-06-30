package com.example.grpc.service;

import com.example.grpc.Person;
import com.example.grpc.PersonServiceGrpc;
import com.example.grpc.exception.custrom.PersonNotFoundException;
import com.example.grpc.mapper.PersonMapper;
import com.example.grpc.model.PersonApp;
import com.example.grpc.repository.PersonRepository;
import com.google.protobuf.Descriptors;
import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@AllArgsConstructor
@GrpcService
public class PersonService extends PersonServiceGrpc.PersonServiceImplBase {

    private static final String EXCEPTION_MESSAGE = "Person with this id [%s] not found";

    private final PersonMapper mapper;
    private final PersonRepository personRepository;

    @Override
    public void create(Person.PersonCreateRequest request, StreamObserver<Person.PersonResponse> responseObserver) {
        PersonApp person = mapper.requestToPersonCreate(request);
        person = personRepository.saveAndFlush(person);
        Person.PersonResponse response = mapper.personToResponse(person);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void update(Person.PersonUpdateRequest request, StreamObserver<Person.PersonResponse> responseObserver) {
        if(!personRepository.existsById(request.getId())) {
            throw new PersonNotFoundException(String.format(EXCEPTION_MESSAGE, request.getId()));
        }
        PersonApp person = mapper.requestToPersonUpdate(request);
        person = personRepository.saveAndFlush(person);
        Person.PersonResponse response = mapper.personToResponse(person);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void delete(Person.PersonIdRequest request, StreamObserver<Empty> responseObserver) {
        long id = request.getId();
        if(!personRepository.existsById(id)) {
            throw new PersonNotFoundException(String.format(EXCEPTION_MESSAGE, id));
        }
        personRepository.deleteById(id);
        responseObserver.onNext(Empty.newBuilder().build());
        responseObserver.onCompleted();
    }

    @Override
    public void getById(Person.PersonIdRequest request, StreamObserver<Person.PersonResponse> responseObserver) {
        long id = request.getId();
        PersonApp person = personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(String.format(EXCEPTION_MESSAGE, id)));
        Person.PersonResponse response = mapper.personToResponse(person);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getAll(Empty request, StreamObserver<Person.PersonResponse> responseObserver) {
        personRepository.findAll().stream()
                .map(mapper::personToResponse)
                .forEach(responseObserver::onNext);
        responseObserver.onCompleted();
    }
}
