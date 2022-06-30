# grpc-test-task

5 endpoints <br />

# com.example.grpc.PersonService/create <br />
request example: <br />

{ <br/>
  "username": "test username",<br/>
  "age": 111,<br/>
  "email": "abc@exmaple.com",<br/>
  "password":"testPass1"<br/>
}<br/>

# com.example.grpc.PersonService/update <br />
request example: <br />

{ <br/>
  "id": 1, <br/>
  "username": "test username",<br/>
  "age": 111,<br/>
  "email": "abc@exmaple.com",<br/>
  "password":"testPass1" <br/>
} <br/>

# com.example.grpc.PersonService/delete <br /> 
request example: <br />

{ <br/>
  "id": 1, <br/>
} <br/>

# com.example.grpc.PersonService/getAll <br />
request example: <br />

{ } <br/>

# com.example.grpc.PersonService/getById <br />

request example: <br />

{ <br/>
  "id": 1, <br/>
} <br/>



#run instruction

# first step
cd common-grpc <br />
mvn clean install <br />

# second step
cd .. <br />
mvn install  <br />
cd target <br />
java -jar <name-jarfile.jar> <br />
