package com.example.grpc;

import com.example.grpc.exception.custrom.PersonNotFoundException;
import com.example.grpc.service.PersonService;
import com.google.protobuf.Empty;
import io.grpc.internal.testing.StreamRecorder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class GRpcApplicationTests {

    @Autowired
    private PersonService personService;

    @Test
    void createPersonTest() throws Exception {
        Person.PersonCreateRequest req = Person.PersonCreateRequest.newBuilder()
                .setAge(12)
                .setEmail("test@email.com")
                .setPassword("214333")
                .setUsername("test")
                .build();
        StreamRecorder<Person.PersonResponse> responseObserver = StreamRecorder.create();
        personService.create(req, responseObserver);
        if (!responseObserver.awaitCompletion(5, TimeUnit.SECONDS)) {
            fail("The call did not terminate in time");
        }
        assertNull(responseObserver.getError());
        List<Person.PersonResponse> resps = responseObserver.getValues();
        assertEquals(1, resps.size());
        Person.PersonResponse resp = resps.get(0);
        assertEquals(resp.getAge(), req.getAge());
        assertEquals(resp.getEmail(), req.getEmail());
        assertEquals(resp.getPassword(), req.getPassword());
        assertEquals(resp.getUsername(), req.getUsername());
        assertEquals(1, resp.getId());
    }

    @Test
    void updatePersonTest_shouldPersonNotFoundException() throws Exception {
        Person.PersonUpdateRequest req = Person.PersonUpdateRequest.newBuilder()
                .setAge(12)
                .setEmail("test@email.com")
                .setPassword("214333")
                .setUsername("test")
                .build();
        StreamRecorder<Person.PersonResponse> responseObserver = StreamRecorder.create();
        assertThrows(PersonNotFoundException.class,
                () -> personService.update(req, responseObserver));
    }

    @Test
    void updatePersonTest() throws Exception {
        Person.PersonCreateRequest reqCreate = Person.PersonCreateRequest.newBuilder()
                .setAge(12)
                .setEmail("test@email.com")
                .setPassword("214333")
                .setUsername("test")
                .build();
        StreamRecorder<Person.PersonResponse> responseObserverCreate = StreamRecorder.create();
        personService.create(reqCreate, responseObserverCreate);
        if (!responseObserverCreate.awaitCompletion(5, TimeUnit.SECONDS)) {
            fail("The call did not terminate in time");
        }
        Person.PersonUpdateRequest reqUpdate = Person.PersonUpdateRequest.newBuilder()
                .setId(1)
                .setAge(12)
                .setEmail("test1@emai1l.com")
                .setPassword("21411333")
                .setUsername("test11")
                .build();
        StreamRecorder<Person.PersonResponse> responseObserver = StreamRecorder.create();
        personService.update(reqUpdate, responseObserver);
        if (!responseObserver.awaitCompletion(5, TimeUnit.SECONDS)) {
            fail("The call did not terminate in time");
        }
        assertNull(responseObserver.getError());
        List<Person.PersonResponse> resps = responseObserver.getValues();
        assertEquals(1, resps.size());
        Person.PersonResponse resp = resps.get(0);
        assertEquals(resp.getAge(), reqUpdate.getAge());
        assertEquals(resp.getEmail(), reqUpdate.getEmail());
        assertEquals(resp.getPassword(), reqUpdate.getPassword());
        assertEquals(resp.getUsername(), reqUpdate.getUsername());
        assertEquals(resp.getId(), reqUpdate.getId());
    }

    @Test
    void deletePersonTest_shouldPersonNotFoundException() throws Exception {
        Person.PersonIdRequest req = Person.PersonIdRequest.newBuilder()
                .setId(45)
                .build();
        StreamRecorder<Empty> responseObserver = StreamRecorder.create();
        assertThrows(PersonNotFoundException.class,
                () -> personService.delete(req, responseObserver));
    }

    @Test
    void deletePersonTest() throws Exception {
        Person.PersonCreateRequest reqCreate = Person.PersonCreateRequest.newBuilder()
                .setAge(12)
                .setEmail("test@email.com")
                .setPassword("214333")
                .setUsername("test")
                .build();
        StreamRecorder<Person.PersonResponse> responseObserverCreate = StreamRecorder.create();
        personService.create(reqCreate, responseObserverCreate);
        if (!responseObserverCreate.awaitCompletion(5, TimeUnit.SECONDS)) {
            fail("The call did not terminate in time");
        }
        Person.PersonIdRequest req = Person.PersonIdRequest.newBuilder()
                .setId(1)
                .build();
        StreamRecorder<Empty> responseObserver = StreamRecorder.create();
        personService.delete(req, responseObserver);
        if (!responseObserver.awaitCompletion(5, TimeUnit.SECONDS)) {
            fail("The call did not terminate in time");
        }
        assertNull(responseObserver.getError());
    }

    @Test
    void getByIdPersonTest_shouldPersonNotFoundException() throws Exception {
        Person.PersonIdRequest req = Person.PersonIdRequest.newBuilder()
                .setId(45)
                .build();
        StreamRecorder<Person.PersonResponse> responseObserver = StreamRecorder.create();
        assertThrows(PersonNotFoundException.class,
                () -> personService.getById(req, responseObserver));
    }

    @Test
    void getByIdPersonTest() throws Exception {
        Person.PersonCreateRequest reqCreate = Person.PersonCreateRequest.newBuilder()
                .setAge(12)
                .setEmail("test@email.com")
                .setPassword("214333")
                .setUsername("test")
                .build();
        StreamRecorder<Person.PersonResponse> responseObserverCreate = StreamRecorder.create();
        personService.create(reqCreate, responseObserverCreate);
        if (!responseObserverCreate.awaitCompletion(5, TimeUnit.SECONDS)) {
            fail("The call did not terminate in time");
        }
        Person.PersonIdRequest req = Person.PersonIdRequest.newBuilder()
                .setId(1)
                .build();
        StreamRecorder<Person.PersonResponse> responseObserver = StreamRecorder.create();
        personService.getById(req, responseObserver);
        if (!responseObserver.awaitCompletion(5, TimeUnit.SECONDS)) {
            fail("The call did not terminate in time");
        }
        assertNull(responseObserver.getError());
        List<Person.PersonResponse> resps = responseObserver.getValues();
        assertEquals(1, resps.size());
        Person.PersonResponse resp = resps.get(0);
        assertEquals(resp.getAge(), reqCreate.getAge());
        assertEquals(resp.getEmail(), reqCreate.getEmail());
        assertEquals(resp.getPassword(), reqCreate.getPassword());
        assertEquals(resp.getUsername(), reqCreate.getUsername());
        assertEquals(1, resp.getId());
    }

    @Test
    void getAllPersonsTest() throws Exception {
        Person.PersonCreateRequest reqCreate = Person.PersonCreateRequest.newBuilder()
                .setAge(12)
                .setEmail("test@email.com")
                .setPassword("214333")
                .setUsername("test")
                .build();
        StreamRecorder<Person.PersonResponse> responseObserverCreate = StreamRecorder.create();
        personService.create(reqCreate, responseObserverCreate);
        personService.create(reqCreate, responseObserverCreate);
        personService.create(reqCreate, responseObserverCreate);
        if (!responseObserverCreate.awaitCompletion(15, TimeUnit.SECONDS)) {
            fail("The call did not terminate in time");
        }
        Empty empty = Empty.getDefaultInstance();
        StreamRecorder<Person.PersonResponse> responseObserver = StreamRecorder.create();
        personService.getAll(empty, responseObserver);
        if (!responseObserver.awaitCompletion(5, TimeUnit.SECONDS)) {
            fail("The call did not terminate in time");
        }
        assertNull(responseObserver.getError());
        List<Person.PersonResponse> resps = responseObserver.getValues();
        assertEquals(3, resps.size());
        resps.forEach(resp -> {
            assertEquals(resp.getAge(), reqCreate.getAge());
            assertEquals(resp.getEmail(), reqCreate.getEmail());
            assertEquals(resp.getPassword(), reqCreate.getPassword());
            assertEquals(resp.getUsername(), reqCreate.getUsername());
            assertTrue(resp.getId() != 0);
        });
    }
}
