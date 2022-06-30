# grpc-test-task

# 5 endpoints <br />

# com.example.grpc.PersonService/create <br />
request example: <br />

{ <br/>
 <pre>  "username": "test username",<br/>
  "age": 111,<br/>
  "email": "abc@exmaple.com",<br/>
  "password":"testPass1"<br/> </pre>
}<br/>

# com.example.grpc.PersonService/update <br />
request example: <br />

{ <br/>
   <pre>  "id": 1, <br/>
  "username": "test username",<br/>
  "age": 111,<br/>
  "email": "abc@exmaple.com",<br/>
  "password":"testPass1" <br/></pre>
} <br/>

# com.example.grpc.PersonService/delete <br /> 
request example: <br />

{ <br/>
   <pre>"id": 1, <br/></pre>
} <br/>

# com.example.grpc.PersonService/getAll <br />
request example: <br />

{<br/>
<pre><br/></pre> 
} <br/>

# com.example.grpc.PersonService/getById <br />

request example: <br />

{ <br/>
  <pre>"id": 1, <br/></pre>
} <br/>



# run instruction

# first step
<pre>cd common-grpc <br />
mvn clean install <br /></pre>

# second step
<pre>cd .. <br />
mvn install  <br />
cd target <br />
java -jar <name-jarfile.jar> <br /></pre>
