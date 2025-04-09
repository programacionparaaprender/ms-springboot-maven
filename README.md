### cursos
>- https://www.udemy.com/course/introduccion-a-sql-con-oracle-11g/learn/lecture/3002512#overview

### dockerizar aplicativo
>- https://github.com/pedrovelasquez9/springboot-postgresql-docker-compose/blob/master/docker-compose.yml
>- https://www.youtube.com/watch?v=uqBZEL9m2V4
>- docker-compose build java
>- docker-compose build
>- docker-compose up
>- docker-compose down
>- docker image rm afd 801


### configurar properties de oracle
>- https://stackoverflow.com/questions/54305348/how-to-connect-to-oracle-database-using-spring-boot
>- https://stackoverflow.com/questions/57715024/hibernate-dialect-for-oracle-19
>- https://www.codejava.net/frameworks/spring-boot/connect-to-oracle-database-examples

# levantar proyecto
mvn spring-boot:run
mvn test

### proyecto del curso
https://github.com/rlechetaudemy/springboot_graphql

--------------------------
# Ejemplo
--------------------------
https://memorynotfound.com/spring-cloud-eureka-service-discovery-client-server-example/

# curso
https://www.udemy.com/course/curso-completo-junit-mockito-spring-boot-test/

# documentación
https://www.geeksforgeeks.org/unit-testing-in-spring-boot-project-using-mockito-and-junit/

# youtube
https://www.youtube.com/watch?v=j9k3epjUgr8

# ejemplo de mockito
´´´
https://www.geeksforgeeks.org/unit-testing-in-spring-boot-project-using-mockito-and-junit/
https://www.geeksforgeeks.org/unit-testing-in-spring-boot-project-using-mockito-and-junit/
´´´
# ejemplo de spring test
´´´
https://www.callicoder.com/spring-5-reactive-webclient-webtestclient-examples/
´´´

---------------------------------------
# modificando el application.properties
---------------------------------------
https://www.youtube.com/watch?v=u-WkRMYhFb0
Anterior de application.properties
spring.datasource.url=jdbc:mysql://127.0.0.1:3306/refugiojava?useSSL=false&serverTimezone=UTC

# nombre de usuario y contrase�a
spring.datasource.username = root
spring.datasource.password =

# mostrar sentencias SQL en la consola
spring.jpa.show-sql = true

# actualizar base de datos y crear entidades
spring.jpa.hibernate.ddl-auto = update

# crear y eliminar
spring.jpa.hibernate.ddl-auto = create-drop

# hibernate genera SQL optimizado
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL5InnoDBDialect

------------------
### configuración para sql server
--------------------------
https://springframework.guru/configuring-spring-boot-for-microsoft-sql-server/
spring.datasource.url=jdbc:sqlserver://localhost;databaseName=editorialweb
spring.datasource.username=sa
spring.datasource.password=123
spring.datasource.driverClassName=com.microsoft.sqlserver.jdbc.SQLServerDriver
spring.jpa.show-sql=true
spring.jpa.hibernate.dialect=org.hibernate.dialect.SQLServer2012Dialect
spring.jpa.hibernate.ddl-auto = create-drop


------------------------------
### configuración para mysql
-------------------------------
spring.datasource.url=jdbc:mysql://127.0.0.1:3306/editorialweb?useSSL=false&serverTimezone=UTC

# nombre de usuario y contraseï¿½a
spring.datasource.username = root
spring.datasource.password =

# mostrar sentencias SQL en la consola
spring.jpa.show-sql = true

# actualizar base de datos y crear entidades
spring.jpa.hibernate.ddl-auto = update

# hibernate genera SQL optimizado
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL5InnoDBDialect


# mostrar sentencias SQL en la consola
spring.jpa.show-sql = true

# actualizar base de datos y crear entidades
spring.jpa.hibernate.ddl-auto = update

# hibernate genera SQL optimizado
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.PostgreSQLDialect

### probar api desde bash
curl -g http://localhost:8762/tio/detalle/1


### error Please refer to dump files (if any exist) [date].dump, [date]-jvmRun[N].dump and [date].dumpstream.
https://stackoverflow.com/questions/71282385/how-to-fix-dump-files-if-any-exist-date-dump-date-jvmrunn-dump-and-dat?answertab=active

### error Caused by: org.apache.logging.log4j.LoggingException: log4j-slf4j-impl cannot be present with log4j-to-slf4j
https://stackoverflow.com/questions/59629214/caused-by-org-apache-logging-log4j-loggingexception-log4j-slf4j-impl-cannot-be

### error The Bean Validation API is on the classpath but no implementation could be found
https://stackoverflow.com/questions/48483120/the-bean-validation-api-is-on-the-classpath-but-no-implementation-could-be-foun

###
http://localhost:8300/graphiql?query=%7B%0A%20%20hello%2C%0A%20%20soma(a%3A1%2C%20b%3A2)%0A%7D
# solo query o mutation
query GetCliente($id:ID!){
  hello,
  soma(a:1, b:2),
  t1: tio(id: $id) {
    id, nombre, email, password
  },
  t2: tio(id: 2) {
    ...fragTio
  },
  tios {
    id, nombre, password
  }
}

fragment fragTio on Tio {
  id, nombre, email, password
}

# solo se puede ejecutar uno a la vez
mutation{
 saveTio(tio:{
    nombre:"nuevo",
    email:"nuevo@ejemplo.com",
    password:"12345678"
  }) {
    id, nombre
  },
  deleteTio(id: 4)
}


#### ver esquema
http://localhost:8762/graphql/schema.json

### hacer dos peticiones
query {
  t1: tio(id: 1) {
    id,
    nombre,
   email
  },
  t2: tio(id: 2) {
    id,
    nombre,
   email
  }
}

### usando fragment
query {
  t1: tio(id: 1) {
    ...fragTio
  },
  t2: tio(id: 2) {
    ...fragTio
  }
}

fragment fragTio on Tio {
  id, nombre, email, password
}

#### http://localhost:8300/graphiql
query GetCliente($id:ID!){
  t1: tio(id: $id) {
    id, nombre, email, password
  }
}
#### query variables
{
  "id":1
}


