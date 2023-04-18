# Spring Application

## POM (Project Object Model)

- The POM is an XML File used by Apache Maven to manage a Project's Dependencies, Build Configuration, and other Project-related Metadata
- The POM contains Information about the Project such as its Name, Version, Dependencies, Plugins, Repositories, and Build Configuration. It serves as a Descriptor for the Project and is used by Maven to understand the Project Structure and its Dependencies
- Maven uses `pom.xml` to manage the Project Lifecycle, including Tasks such as Compiling, Testing, Packaging and Deployment. It also uses the `pom.xml` to resolve Dependencies, which allows it to automatically download and manage the necessary Libraries and Frameworks

### Maven Tags

- `<project>`: This is the Root Element of the `pom.xml` File, and contains all the other Elements of the File. It also specifies the Coordinates of the Project, such as the GroupId, ArtifactId and Version
- `<dependencies>`: This Tag contains a List of all the Project's Dependencies. Each Dependency is defined by the GroupId, ArtifactId and Version Tags
- `<build>`: This Tag specifies the Build Settings and Configuration for the Project. It includes Elements such as Plugins, SourceDirectory, TestSourceDirectory, Resources and testResources
- `<plugins>`: This Tag contains a List of the Plugins used in the Project Build. Each Plugin is defined using the GroupId, ArtifactId and Version Tags. Plugins can be used to perform various Tasks such as Compiling Code, Running Tests, Building Distributions and Deploying Artifacts
- `<profiles>`: This Tag contains a List of Profiles that can be enabled or disabled based on certain Criteria. Each Profile can have its own Set of Dependencies, Plugins and Build Configuration
- `<repositories>`: This Tag specifies the Repositories where Maven can find Project Dependencies. Maven downloads all required Dependencies from these Repositories
- `<distributionManagement>`: This Tag is used to specify the Distribution Management Settings for the Project. It includes Elements such as Repository, SnapshotRepository, and Site

### Java Embedded Web Servers

- Java Embedded Web Servers are lightweight Web Servers that can be embedded within a Java Application. These Servers provide a convenient Way to develop, test and deploy Web Applications without the Need for a separate Web Server Installation
- In this Context, Tomcat, Jetty and Undertow are popular Java Embedded Web Servers
  - **Apache Tomcat** is an Open Source Web Server that is widely used for Developing Java Web Applications. Tomcat supports Servlets and JavaServer Pages (JSPs) and provides a simple and lightweight HTTP Server. Tomcat is easy to set up and configure and supports a range of advanced Features such as SSL, Virtual Hosting and Clustering
  - **Jetty** is an Open Source Web Server and Servlet Container for Java Applications. It is designed to be lightweight and fast, Jetty offers a modular Architecture that allows Users to customize the Server to suit their Needs. Jetty supports WebSocket, SPDY and HTTP/2 Protocols and is widely used in Embedded Applications such as IoT Devices and mobile Applications
  - **Undertow** is a Web Server designed to be lightweight and flexible. It is also a Component of WildFly Application Server. Undertow supports both Blocking and Non-Blocking I/O and provides an Embedded Servlet Container for deploying web Applications. Undertow is easy to configure and provides excellent Performance even for high-traffic Websites

### Jib

- JIB is an open-source Containerization Tool that builds optimized Docker and OCI Images for Java Applications without requiring Docker Daemon or even Docker Installation on the Developer's Machine
- It builds Container Images directly from the Java Build without the Need to package the Application in a container
- It simplifies the Creation of Docker Images and also the Push to a Registry
- It provides optimized Layering and Caching Strategies that significantly reduce the Build Time for Container Images

## Spring

### Application Context

- When Beans are created, they are stored in an Instance of ApplicationContext

### Bean

- Each Component in a Spring Application is called a Bean
- A Class is annotated as such with **@bean**, or another Annotation that inherits from it. This tells the IOC (Inversion of Control)/DI (Dependency Injection) System inside Spring to treat the Class as a Component within the System, so that it can be injected as a Dependency, or receive Dependency Injections into it via @Autowired Annotations
- The Default Bean Scope is Singleton Beans, which are created once and then reused as the Application Runs

#### Singleton Beans

- Singleton Beans are stateless and therefore do not store any Information internally

#### Prototype Beans

- Unlike Singleton Beans, each Class that depends on a Prototype Bean depends on a separate Instance of that Bean
- Each Time a Prototype Bean is injected, a new Instance of that Bean is created
- Prototype Beans are stateful, so they will store Information internally

### Annotations

- The **@SpringBootApplication** Annotation is a Combination of three Annotations: **@SpringBootConfiguration**, **@EnableAutoConfiguration** and **@ComponentScan**. It is used to indicate that a Class is a Spring Boot Application. The **@SpringBootApplication** tells Spring Boot to enable Automatic Configuration, Component Scanning, and other Features required to build a Spring Boot Application. It is typically used at the Entry Point of a Spring Boot Application, such as the Main Class
- The **@RestController** Annotation is used to indicate that a Class is a RESTful Web Service. It is a specialized Version of the **@Controller** Annotation used specifically for Building RESTful Web Services. It tells Spring that the Class should be treated as a RESTful Controller and that the Methods in the Class should return Response Data in a Format suitable for Consumption by Web Clients
- The **@GetMapping** Annotation is used to map HTTP GET Requests to a Method in a Spring Controller. It tells Spring to map a GET Request to the annotated Method. The URL Pattern for the Mapping can be specified using the Value Attribute of the Annotation

### Spring Boot Maven Plugin

- The Spring Boot Maven Plugin provides Spring Boot Support in Apache Maven
- It allows to package executable JAR or WAR Archives, run Spring Boot Applications, generate Build Information, and launch Spring Boot Applications before running Integration Tests
- The `Surefire Plugin` is used during the Test Phase of the Build Lifecycle to execute the Unit Tests of an Application
- The `Failsafe Plugin` is designed to run Integration Tests while the Surefire Plugin is designed to run Unit Tests
- The Failsafe Plugin is used during the `integration-test` and `verify` Phases of the Build Lifecycle to execute the Integration Tests
- The Failsafe Plugin will not fail the Build during the `integration-test` Phase, thus enabling the `post-integration-test` Phase to execute

### Spring Web MVC

- Spring Web MVC is a Framework for Building Web Applications using the Model-View-Controller (MVC) Design Pattern. It is Part of the Spring Framework, which is a Java Framework for Building Enterprise Applications
- The Model represents the Data and Business Logic of the Application, the View is responsible for Rendering the User Interface, and the Controller is responsible for Processing User Requests, invoking the appropriate Business Logic, and returning the Response
- Some Key features of Spring Web MVC are:
  - **Dispatcher Servlet**: This is the central Servlet that processes incoming Requests and sends them to the appropriate Controller. The DispatcherServlet is responsible for Handling all HTTP Requests and Routing them to the appropriate Controller
  - **Controllers**: Controllers are responsible for Processing User Requests and Generating the appropriate Responses. Controllers receive User Input, validate it, invoke the appropriate Business Logic, and send the Response back to the Client
  - **Views**: Views are responsible for Rendering the Response generated by the Controller. A View can be an HTML Page or any other Type of Output Format
  - **Data Binding**: Spring Web MVC provides Data Binding Capabilities that allow Developers to map incoming HTTP Requests to Java Objects and vice versa. Data Binding simplifies the Process of Handling User Input and eliminates the Need for manual Data Conversion
  - **Validation**: Spring Web MVC provides a Validation Framework that allows Developers to validate User Input and ensure that it meets certain Criteria before Processing it. This helps to reduce Errors and improve Application Reliability
  - **Interceptors**: Interceptors are used to intercept incoming Requests and outgoing Responses. They can be used for Tasks such as Logging, Security Checks or Performance Monitoring

### Spring Data JPA

- Spring Data JPA is a Library that builds on Top of JPA to provide additional Functionality and Features. It simplifies Data Access Layer Development by providing a Set of easy-to-use APIs and Annotations
- Spring Data JPA provides Repositories that contain commonly used CRUD Operations for working with Entities. These Repositories are created by extending the `CrudRepository` or `JpaRepository` Interfaces, which provide a Set of generic Methods for working with Entities. Spring Data JPA also supports Query Methods, which allow Developers to create custom Methods that execute complex Database Queries based on Method Naming Conventions
- Spring Data JPA provides support for many popular Databases by providing Auto-Configuration and Database-specific Extensions
- By using Spring Data JPA, Developers can write less Boilerplate Code, which reduces the Amount of Code needed for Database Operations

#### JPA (Jakarta Persistence API)

- JPA is a Java Specification that provides a Set of Interfaces and Annotations for mapping Java Objects to relational Databases
- JPA provides a high-level, Object-relational Mapping (ORM) API that allows Developers to interact with a Database using Java Objects. With JPA, Developers can define Mappings between Java Classes and Database Tables, and can perform CRUD (Create, Read, Update, Delete) Operations on those Objects using a simple API
- JPA also supports advanced Features such as Lazy Loading, Caching, and Transactions, making it a powerful and flexible Tool for working with Relational Databases in Java Applications. In addition, JPA is Database-agnostic, which means that it can be used with any Relational Database that has a Jdbc (Java Database Connectivity) Driver

#### Jdbc (Java Database Connectivity) Template

- Jdbc is an API that defines how a Client can access a Database
- JdbcTemplates simplifies the Use of Jdbc. It executes the Jdbc Workflow, leaving the Application Code to generate SQL and extract Results
- A DataSource Object provides a Way for Jdbc Clients to obtain a DBMS (Database Management System) Connection. This DataSource Entry points to a Connection Pool with available Connections to use

### Spring Security

- Spring Security is a Framework that provides Authentication, Authorization, and Protection against common Attacks

<p align="center">
  <img src="https://user-images.githubusercontent.com/29623199/231731914-710262ce-00ec-41cb-8630-491543264722.png" alt="Spring_Security" />
</P>

- AuthenticationManager is the API that defines how Spring Security's Filters perform Authentication
- ProviderManager is the most commonly used Implementation of the AuthenticationManager. It can choose between different AuthenticationProviders such as for Username and Password or Remember Me (JWT) for Authentication

#### Security Filter Chain

- The Client sends a Request to the Application, and the Container (i.e. a Component that can contain other Components inside itself) creates a FilterChain containing the Filter Instances and Servlet that should process the HttpServletRequest based on the Path of the request URI
- In a Spring MVC Application, the Servlet is an Instance of DispatcherServlet
- A Servlet can only handle a single HttpServletRequest and HttpServletResponse, mut more than one Filter can be used in a Chain

<p align="center">
  <img src="https://user-images.githubusercontent.com/29623199/232000181-89de4188-db19-438b-9c4f-bc1c4dc7ab30.png" alt="Security_Filter_Chain" />
</P>

### Spring Boot Actuator

- Spring Boot Actuator exposes a Bunch of Endpoints with Metrics to monitor and interact with the Application

## Flyway

- Flyway increases Reliability of Deployments by Versioning the used Database with Migrations
- Database Migrations allow to:
  - Recreate a Database from scratch
  - Make it clear at all Times what State a Database is in
  - Migrate in a deterministic Way from a current Version of the Database to a newer one

## Testcontainers

- Testcontainers for Java is a Java Library that supports JUnit Testing and provides lightweight, disposable Instances of common Databases, Selenium Web Browsers, or anything else that can be run in a Docker Container
- Testcontainers facilitate the following Types of Testing:
  - **Data Access Layer Integration Testing**: use a containerized Instance of a MySQL, PostgreSQL or Oracle Database to test the Data Access Layer Code for full Compatibility -**Application Integration Testing**: run the Application in a short-lived Test Mode with Dependencies such as Databases, Message Queues or Web Servers
  - **UI/Acceptance Testing**: use containerized Web Browsers compatible with Selenium to run automated UI Tests

## DTO (Data Transfer Object) Pattern

- DTOs are Objects that carry Data between Processes to reduce the Number of Method Calls
- DTOs are flat Data Structures that contain no Business Logic. They only contain Storage, Accessors and possibly Methods related to Serialization or Parsing
- A Mapper Component is used to transfer the Data, ensuring that the DTO and Domain Model (i.e. Entity) do not need to know about each other
- By using DTOs, the System Structure is not exposed to the Client because the Client only gets the Information in the DTO that it needs. This allows different Views of the System to be built
