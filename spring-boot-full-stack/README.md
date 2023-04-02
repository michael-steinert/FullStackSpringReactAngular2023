# Spring Boot Full Stack

## DevOps

- DevOps is a Set of Practices that combines Software Development (Dev) and Information Technology Operations (Ops) to
  shorten the System Development Lifecycle while Delivering Features, Fixes and Updates frequently and reliably
- The objectives of DevOps are:
    - Improved Collaboration between Development and Operations Teams
    - Faster Delivery of Software and Updates to Customers
    - Increased Reliability and Stability of Software and Infrastructure
    - Increased Efficiency and Cost Savings using Cloud Technology
- The DevOps Lifecycle consists of the following Stages: Planning, Development, Testing, Deployment and Monitoring and
  Logging
- Each Stage of the DevOps Lifecycle is iterative, with Feedback Loops between Stages to ensure continuous Improvement
- DevOps is a Set of the following Practices:
    - Continuous Integration (CI): The Practice of regularly merging Code Changes into a central Repository, which is
      then automatically built and tested
    - Continuous Delivery (CD): The Practice of automating the entire Software Release Process, from Building and
      Testing to Deployment and Infrastructure Provisioning
    - Infrastructure as Code (IaC): The Practice of Managing and Provisioning Infrastructure Resources using Code and
      Automation Tools
    - Monitoring and Logging: The Practice of continuously Monitoring and Logging Software and Infrastructure
      Performance to identify Problems and Opportunities for Improvement

## Docker

- Docker is a Platform for Building, Running and Shipping Applications
- It allows Developers to easily build and deploy Applications that run in Containers
- It ensures that local Development is the same as in any Environment
- Containers are an Abstraction at the Application Layer that packages Code and Dependencies together
- Multiple Containers can run on the same Machine, sharing the OS Kernel with other Containers, each running as an
  isolated Process in User Space

## Jib

- JIB is an open-source Containerization Tool that builds optimized Docker and OCI Images for Java Applications without
  requiring Docker Daemon or even Docker Installation on the Developer's Machine
- It builds Container Images directly from the Java Build without the Need to package the Application in a container
- It simplifies the Creation of Docker Images and also the Push to a Registry
- It provides optimized Layering and Caching Strategies that significantly reduce the Build Time for Container Images

### Docker Commands

| Command                               | Description                                               |
|---------------------------------------|-----------------------------------------------------------|
| docker ps                             | List running Containers                                   |
| docker ps -a                          | 	List all Containers (including stopped ones)             |
| docker images                         | List Images                                               |
| docker run <image>                    | Run a Container from an Image                             |
| docker stop <container>               | 	Stop a running Container                                 |
| docker rm <container>                 | Remove a stopped Container                                |
| docker rmi <image>                    | 	Remove an Image                                          |
| docker build <path/to/dockerfile>     | 	Build an Image from a Dockerfile                         |
| docker tag <image> <new_name:tag>     | 	Tag an Image with a new Name and optional Tag            |
| docker login                          | 	Log in to a Docker Registry                              |
| docker push <image>                   | Push an Image to a Docker Registry                        |
| docker pull <image>                   | Pull an Image from a Docker Registry                      |
| docker exec -it <container> <command> | 	Execute a Command in a running Container                 |
| docker-compose up                     | Start all Services defined in a `docker-compose.yml` File |
| docker-compose down                   | 	Stop all Services defined in a `docker-compose.yml` File |

## POM (Project Object Model)

- The POM is an XML File used by Apache Maven to manage a Project's Dependencies, Build Configuration, and other
  Project-related Metadata
- The POM contains Information about the Project such as its Name, Version, Dependencies, Plugins, Repositories, and
  Build Configuration. It serves as a Descriptor for the Project and is used by Maven to understand the Project
  Structure and its Dependencies
- Maven uses `pom.xml` to manage the Project Lifecycle, including Tasks such as Compiling, Testing, Packaging and
  Deployment. It also uses the `pom.xml` to resolve Dependencies, which allows it to automatically download and manage
  the necessary Libraries and Frameworks

### Maven Tags

- `<project>`: This is the Root Element of the `pom.xml` File, and contains all the other Elements of the File. It also
  specifies the Coordinates of the Project, such as the GroupId, ArtifactId and Version
- `<dependencies>`: This Tag contains a List of all the Project's Dependencies. Each Dependency is defined by the
  GroupId, ArtifactId and Version Tags
- `<build>`: This Tag specifies the Build Settings and Configuration for the Project. It includes Elements such as
  Plugins, SourceDirectory, TestSourceDirectory, Resources and testResources
- `<plugins>`: This Tag contains a List of the Plugins used in the Project Build. Each Plugin is defined using the
  GroupId, ArtifactId and Version Tags. Plugins can be used to perform various Tasks such as Compiling Code, Running
  Tests, Building Distributions and Deploying Artefacts
- `<profiles>`: This Tag contains a List of Profiles that can be enabled or disabled based on certain Criteria. Each
  Profile can have its own Set of Dependencies, Plugins and Build Configuration
- `<repositories>`: This Tag specifies the Repositories where Maven can find Project Dependencies. Maven downloads all
  required Dependencies from these Repositories
- `<distributionManagement>`: This Tag is used to specify the Distribution Management Settings for the Project. It
  includes Elements such as Repository, SnapshotRepository, and Site

### Java Embedded Web Servers

- Java Embedded Web Servers are lightweight Web Servers that can be embedded within a Java Application. These Servers
  provide a convenient Way to develop, test and deploy Web Applications without the Need for a separate Web Server
  Installation
- In this Context, Tomcat, Jetty and Undertow are popular Java Embedded Web Servers
    - __Apache Tomcat__ is an Open Source Web Server that is widely used for Developing Java Web Applications. Tomcat
      supports Servlets and JavaServer Pages (JSPs) and provides a simple and lightweight HTTP Server. Tomcat is easy to
      set up and configure and supports a range of advanced Features such as SSL, Virtual Hosting and Clustering
    - __Jetty__ is an Open Source Web Server and Servlet Container for Java Applications. It is designed to be
      lightweight and fast, Jetty offers a modular Architecture that allows Users to customise the Server to suit their
      Needs. Jetty supports WebSocket, SPDY and HTTP/2 Protocols and is widely used in Embedded Applications such as IoT
      Devices and mobile Applications
    - __Undertow__ is a Web Server designed to be lightweight and flexible. It is also a Component of WildFly
      Application Server. Undertow supports both Blocking and Non-Blocking I/O and provides an Embedded Servlet
      Container for deploying web Applications. Undertow is easy to configure and provides excellent Performance even
      for high-traffic Websites

### API (Application Programming Interface)

- An API is a Set of Protocols, Routines and Tools used to build Software Applications. An API defines how different
  Software Components should interact with each other. It provides a standard Way for different Software Applications to
  communicate and share Data
- APIs can be divided into different Types, such as RESTful APIs, SOAP APIs and GraphQL APIs. Each Type has its own Set
  of Characteristics and is suitable for different Use Cases.
- APIs are used for a variety of Purposes, such as Integrating with Third-Party Services, Building Web and Mobile
  Applications, Creating Software Libraries, and Automating Tasks

## Spring

### Application Context

- When Beans are created, they are stored in an Instance of ApplicationContext

### Bean

- Each Component in a Spring Application is called a Bean
- A Class is annotated as such with __@bean__, or another Annotation that inherits from it. This tells the IOC (
  Inversion of Control)/DI (Dependency Injection) System inside Spring to treat the Class as a Component within the
  System, so that it can be injected as a Dependency, or receive Dependency Injections into it via @Autowired
  Annotations
- The Default Bean Scope is Singleton Beans, which are created once and then reused as the Application Runs

#### Singleton Beans

- Singleton Beans are stateless and therefore do not store any Information internally

#### Prototype Beans

- Unlike Singleton Beans, each Class that depends on a Prototype Bean depends on a separate Instance of that Bean
- Each Time a Prototype Bean is injected, a new Instance of that Bean is created
- Prototype Beans are stateful, so they will store Information internally

### Annotations

- The __@SpringBootApplication__ Annotation is a Combination of three Annotations: __@SpringBootConfiguration__, *
  *@EnableAutoConfiguration** and __@ComponentScan__. It is used to indicate that a Class is a Spring Boot Application.
  The __@SpringBootApplication__ tells Spring Boot to enable Automatic Configuration, Component Scanning, and other
  Features required to build a Spring Boot Application. It is typically used at the Entry Point of a Spring Boot
  Application, such as the Main Class
- The __@RestController__ Annotation is used to indicate that a Class is a RESTful Web Service. It is a specialised
  Version of the __@Controller__ Annotation used specifically for Building RESTful Web Services. It tells Spring that
  the Class should be treated as a RESTful Controller and that the Methods in the Class should return Response Data in a
  Format suitable for Consumption by Web Clients
- The __@GetMapping__ Annotation is used to map HTTP GET Requests to a Method in a Spring Controller. It tells Spring to
  map a GET Request to the annotated Method. The URL Pattern for the Mapping can be specified using the Value Attribute
  of the Annotation

### Spring Boot Maven Plugin

- The Spring Boot Maven Plugin provides Spring Boot Support in Apache Maven
- It allows to package executable JAR or WAR Archives, run Spring Boot Applications, generate Build Information, and
  launch Spring Boot Applications before running Integration Tests
- The `Surefire Plugin` is used during the Test Phase of the Build Lifecycle to execute the Unit Tests of an Application
- The `Failsafe Plugin` is designed to run Integration Tests while the Surefire Plugin is designed to run Unit Tests
- The Failsafe Plugin is used during the `integration-test` and `verify` Phases of the Build Lifecycle to execute the
  Integration Tests
- The Failsafe Plugin will not fail the Build during the `integration-test` Phase, thus enabling
  the `post-integration-test` Phase to execute

### Spring Web MVC

- Spring Web MVC is a Framework for Building Web Applications using the Model-View-Controller (MVC) Design Pattern. It
  is Part of the Spring Framework, which is a Java Framework for Building Enterprise Applications
- The Model represents the Data and Business Logic of the Application, the View is responsible for Rendering the User
  Interface, and the Controller is responsible for Processing User Requests, invoking the appropriate Business Logic,
  and returning the Response
- Some Key features of Spring Web MVC are:
    - __Dispatcher Servlet__: This is the central Servlet that processes incoming Requests and sends them to the
      appropriate Controller. The DispatcherServlet is responsible for Handling all HTTP Requests and Routing them to
      the appropriate Controller
    - __Controllers__: Controllers are responsible for Processing User Requests and Generating the appropriate
      Responses. Controllers receive User Input, validate it, invoke the appropriate Business Logic, and send the
      Response back to the Client
    - __Views__: Views are responsible for Rendering the Response generated by the Controller. A View can be an HTML
      Page or any other Type of Output Format
    - __Data Binding__: Spring Web MVC provides Data Binding Capabilities that allow Developers to map incoming HTTP
      Requests to Java Objects and vice versa. Data Binding simplifies the Process of Handling User Input and eliminates
      the Need for manual Data Conversion
    - __Validation__: Spring Web MVC provides a Validation Framework that allows Developers to validate User Input and
      ensure that it meets certain Criteria before Processing it. This helps to reduce Errors and improve Application
      Reliability
    - __Interceptors__: Interceptors are used to intercept incoming Requests and outgoing Responses. They can be used
      for Tasks such as Logging, Security Checks or Performance Monitoring

### Spring Data JPA

- Spring Data JPA is a Library that builds on Top of JPA to provide additional Functionality and Features. It simplifies
  Data Access Layer Development by providing a Set of easy-to-use APIs and Annotations
- Spring Data JPA provides Repositories that contain commonly used CRUD Operations for working with Entities. These
  Repositories are created by extending the `CrudRepository` or `JpaRepository` Interfaces, which provide a Set of
  generic Methods for working with Entities. Spring Data JPA also supports Query Methods, which allow Developers to
  create custom Methods that execute complex Database Queries based on Method Naming Conventions
- Spring Data JPA provides support for many popular Databases by providing Auto-Configuration and Database-specific
  Extensions
- By using Spring Data JPA, Developers can write less Boilerplate Code, which reduces the Amount of Code needed for
  Database Operations

#### JPA (Jakarta Persistence API)

- JPA is a Java Specification that provides a Set of Interfaces and Annotations for mapping Java Objects to relational
  Databases
- JPA provides a high-level, Object-relational Mapping (ORM) API that allows Developers to interact with a Database
  using Java Objects. With JPA, Developers can define Mappings between Java Classes and Database Tables, and can perform
  CRUD (Create, Read, Update, Delete) Operations on those Objects using a simple API
- JPA also supports advanced Features such as Lazy Loading, Caching, and Transactions, making it a powerful and flexible
  Tool for working with Relational Databases in Java Applications. In addition, JPA is Database-agnostic, which means
  that it can be used with any Relational Database that has a JDBC (Java Database Connectivity) Driver

#### PSQL Commands

| Command                                                                                                                                                         | Description                 |
|-----------------------------------------------------------------------------------------------------------------------------------------------------------------|-----------------------------|
| docker ps --format="ID\t{{.ID}}\nNAME\t{{.Names}}\nIMAGE\t{{.Image}}\nPORTS\t{{.Ports}}\nCOMMAND\t{{.Command}}\nCREATED\t{{.CreatedAt}}\nSTATUS\t{{.Status}}\n" | List formatted Containers   |
| docker exec -it postgres bash                                                                                                                                   | Execute a Bash in Container |
| psql -U postgres                                                                                                                                                | Connect to PSQL             |
| psql -U postgres -d customer                                                                                                                                    | Connect to PSQL             |
| \q                                                                                                                                                              | Quit PSQL                   |
| \l                                                                                                                                                              | List Databases              |
| \c                                                                                                                                                              | Connect to a Database       |
| \dt                                                                                                                                                             | List Tables of Database     |

#### JDBC (Java Database Connectivity) Template

- JDBC is an API that defines how a Client can access a Database
- JDBCTemplates simplifies the Use of JDBC. It executes the JDBC Workflow, leaving the Application Code to generate SQL
  and extract Results
- A DataSource Object provides a Way for JDBC Clients to obtain a DBMS (Database Management System) Connection. This
  DataSource Entry points to a Connection Pool with available Connections to use

## JSON (JavaScript Object Notation)

- JSON is a lightweight Data Interchange Format that is easy for Humans to read and write, and easy for Machines to
  parse and generate
- JSON is used as an Alternative to XML for Transferring Data between a Server and a Web Application. It is a Text
  Format that is completely Language independent. JSON is based on a Subset of the JavaScript Programming Language
- JSON Data is represented as Key-Value Pairs, where the Keys are Strings and the Values can be any Data Type, such as
  Strings, Numbers, Booleans, Arrays, or other JSON Objects

## HTTP (Hypertext Transfer Protocol)

- HTTP is an Application Layer Protocol used to transfer Data over the Internet. It is the Foundation of the World Wide
  Web and is used to transfer Resources such as HTML Documents, Images, Videos and other Types of Files
- HTTP works on a Client-Server Model, where the Client sends Requests to the Server and the Server responds to those
  Requests. HTTP uses specific Methods to specify the Action that the Client wants the Server to perform
- The most common Methods are:
    - __GET__: retrieves Information from the Server
    - __POST__: sends Data to the Server
    - __PUT__: updates an existing Resource on the Server
    - __DELETE__: removes a Resource from the Server
- HTTP uses a Uniform Resource Identifier (URI) to identify the Resource the Client wants to access. The URI consists of
  a Scheme, a Domain Name or IP address, and a Path to the Resource on the Server
- HTTP also uses a Set of Status Codes to indicate the Outcome of a Request
- The most common Status Codes are:
    - __1xx (Informational)__: This Class of Status Codes indicates that the Request has been received and the Server is
      continuing to process it
    - __2xx (Successful)__: This Class of Status Codes indicates that the Request was successful and the Server has
      delivered the requested Content
    - __3xx (Redirection)__: This Class of Status Codes indicates that the Client needs to take further Action to
      complete the Request
    - __4xx (Client Error)__: This Class of Status Codes indicates that the Request contains bad Syntax or can not be
      fulfilled by the Server
    - __5xx (Server Error)__: This Class of Status Codes indicates that the Server encountered an Error while Attempting
      to fulfil the Request
- HTTP is a stateless Protocol, meaning that each Request is independent of all previous Requests. To maintain State,
  Web Applications use Techniques such as Cookies and Sessions

### URL (Uniform Resource Locator)

- A URL is the Address of a Resource on the Internet, such as a Web Page, Image, or File
- A URL consists of several Parts, which are:
    - __Protocol__: This indicates the Protocol used to access the Resource, such as HTTP, HTTPS, FTP, or others. The
      Protocol is followed by a Colon and two forward Slashes
    - __Domain Name or IP Address__: This identifies the Server on which the Resource is located. The Domain Name is a
      Human-readable Name, while the IP Address is a numerical Address
    - __Port Number__: This is the Port Number to which the Request should be sent. This is optional and is usually
      omitted if the default Port for the Protocol is used
    - __Path__: This is the Location of the Resource on the server, such as a File or Directory Path
    - __Query String__: This contains additional Parameters used to refine the Request or pass Data to the Server. It
      starts with a Question Mark and consists of Key-Value Pairs separated by Ampersands
    - __Fragment Identifier__: This identifies a specific Part of the Resource, such as a Section of a Web Page. It
      starts with a Hash Symbol followed by a Fragment Identifier

## Flyway

- Flyway increases Reliability of Deployments by Versioning the used Database with Migrations
- Database Migrations allow to:
    - Recreate a Database from scratch
    - Make it clear at all Times what State a Database is in
    - Migrate in a deterministic Way from a current Version of the Database to a newer one

## Testcontainers

- Testcontainers for Java is a Java Library that supports JUnit Testing and provides lightweight, disposable Instances
  of common Databases, Selenium Web Browsers, or anything else that can be run in a Docker Container
- Testcontainers facilitate the following Types of Testing:
    - __Data Access Layer Integration Testing__: use a containerized Instance of a MySQL, PostgreSQL or Oracle Database
      to test the Data Access Layer Code for full Compatibility
      -__Application Integration Testing__: run the Application in a short-lived Test Mode with Dependencies such as
      Databases, Message Queues or Web Servers
    - __UI/Acceptance Testing__: use containerized Web Browsers compatible with Selenium to run automated UI Tests
