package com.example.grpc.exception;

import com.example.grpc.exception.custrom.PersonNotFoundException;
import io.grpc.Status;
import net.devh.boot.grpc.server.advice.GrpcAdvice;
import net.devh.boot.grpc.server.advice.GrpcExceptionHandler;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcAdvice
public class ExceptionHandler {

    @GrpcExceptionHandler(PersonNotFoundException.class)
    public Status handlerInvalidArgument(PersonNotFoundException e) {
        return Status.NOT_FOUND.withDescription("Person not found: " + e.getLocalizedMessage()).withCause(e);
    }
}
