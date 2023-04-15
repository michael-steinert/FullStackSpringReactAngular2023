# Spring Boot Full Stack

## DevOps

- DevOps is a Set of Practices that combines Software Development (Dev) and Information Technology Operations (Ops) to shorten the System Development Lifecycle while Delivering Features, Fixes and Updates frequently and reliably
- The objectives of DevOps are:
  - Improved Collaboration between Development and Operations Teams
  - Faster Delivery of Software and Updates to Customers
  - Increased Reliability and Stability of Software and Infrastructure
  - Increased Efficiency and Cost Savings using Cloud Technology
- The DevOps Lifecycle consists of the following Stages: Planning, Development, Testing, Deployment and Monitoring and Logging
- Each Stage of the DevOps Lifecycle is iterative, with Feedback Loops between Stages to ensure continuous Improvement
- DevOps is a Set of the following Practices:
  - Continuous Integration (CI): The Practice of regularly merging Code Changes into a central Repository, which is then automatically built and tested
  - Continuous Delivery (CD): The Practice of automating the entire Software Release Process, from Building and Testing to Deployment and Infrastructure Provisioning
  - Infrastructure as Code (IaC): The Practice of Managing and Provisioning Infrastructure Resources using Code and Automation Tools
  - Monitoring and Logging: The Practice of continuously Monitoring and Logging Software and Infrastructure Performance to identify Problems and Opportunities for Improvement

<p align="center">
  <img src="https://user-images.githubusercontent.com/29623199/229474154-d8132aea-d456-4a68-8bd1-7644b63e00d1.png" alt="CI_CD" />
</P>

### Bastion Host

- A Bastion Host is a secure Gateway Server that acts as a single Point of Access to Servers and Resources on a private Network. It is typically deployed on a public Subnet of a Virtual Private Cloud (VPC) in AWS
- The Bastion Host provides a secure Way for Administrators to connect to Servers and Resources on a private Network from outside the Intermediary between the public Internet and the private Network, providing an additional Layer of Security by enforcing Access Controls and Monitoring Traffic

## GitHub Actions

- GitHub Actions automates all Software Workflows with CI/CD
- It allows to build, test and deploy Code directly from the GitHub Repository

### Service Containers

- Service Containers are Docker Containers that provide an easy and portable Way to host Services that might be needed to test or run the Application in a Workflow
- Service Containers can be configured for each Job in a Workflow. GitHub creates a new Docker Container for each Service configured in the Workflow, and destroys the Service Container when the Job completes
- Steps in a Job can communicate with any Service Containers that are Part of the same Job

### Workflow

- A Workflow is an automated Process that can be added to a Repository
- Workflows consist of one or more Jobs, and can be scheduled or triggered by an Event, and can be used to build, test, package, release, or deploy Software Code on GitHub
- Events can be triggered by Push or Pull Requests, Webhooks or manual and scheduled Events
- A Job is a Set of Steps running in parallel
- A Step is a single Task that can execute Commands in a Job. The task can be an Action or a Shell Command
- An Action is a Set of standalone Commands that are combined in Steps to create a Job

### Workflow Testing

- `act` is a powerful Tool that can be used with GitHub Actions to quickly test and refine a Continuous Integration and Continuous Delivery (CI/CD) Pipeline
- It uses Docker Containers locally to run Steps directly in GitHub Actions, so it allows Developers to run independent Stages in a Pipeline, and it generally improves the Feedback Loop when Pipelines are built with GitHub Actions

| Command     | Description                                                    |
| ----------- | -------------------------------------------------------------- |
| act -l      | List all the Actions in the YAML File                          |
| act push -l | List Actions for a specific Event (for example the Push Event) |
| act push    | Run the Workflow as if a specific Push to Master Event occured |
| act -j test | Run a specific Job                                             |

## Cloud Computing

- Cloud Computing refers to a Network of remote Servers hosted on the Internet that are used to store, manage and process Data, rather than a local Server. These Servers are typically owned and operated by third-party Companies
- It provides a wide Range of Services, such as Computing, Storage, Databases, Networking, Machine Learning, and more, that can be accessed by Individuals or Organizations over the Internet
- It allows Users to access their Data and Applications over the Internet
- One Benefit of Cloud Computing is Scalability, which means that Users can easily adjust the Amount of Computing Resources, Storage and other Services they use as they need them, without having to invest in expensive Hardware and Infrastructure
- Cloud Computing also offers high Availability and Reliability, as Data is stored in multiple Locations and can be easily recovered in the Event of a Disaster or Hardware Failure

### AWS (Amazon Web Services)

- AWS offers a wide range of Services, including Computing, Storage, Databases, Analytics, Networking, Machine Learning, Security, and more. Some of the most popular AWS Services include:
  - Amazon EC2 (Elastic Compute Cloud),
  - Amazon S3 (Simple Storage Service),
  - Amazon RDS (Relational Database Service),
  - and Amazon Lambda (a serverless computing platform)
- AWS offers a Pay-per-Use Pricing Model, which means Users only pay for the Resources they use. This makes it a cost-effective Option for Organizations of all Sizes, from Startups to large Enterprises

<p align="center">
  <img src="https://user-images.githubusercontent.com/29623199/229475736-831ba9af-7782-4315-903e-3a7842342489.png" alt="AWS_Infrastructure" />
</P>

#### Security Group

- A Security Group controls the Traffic from the Internet (i.e. 0.0.0.0) that can enter and leave the Resources to which it is associated
- A Security Group that is associated with an EC2 Instance, it controls Traffic inbound to and outbound from that Instance
- Security Groups can only be associated with Resources in the VPC for which it is created
- When a VPC is created, it comes with a default Security Group, but it is possible to create additional Security Groups for each VPC

<p align="center">
  <img src="https://user-images.githubusercontent.com/29623199/231418699-ae4457da-cf5a-472c-821b-59264a5869e3.png" alt="AWS_Security_Groups" />
</P>

#### Amazon VPC (Virtual Private Cloud)

- Amazon VPC gives complete Control over the virtual Network Environment, including Resource Placement, Connectivity and Security
- Amazon VPC can create public and private Subnets. In the Case of public Subnets, Services are accessible to all via the Internet, while in the case of private Subnets, Services are only accessible within the Subnet

#### AWS Elastic Beanstalk

- Elastic Beanstalk is a Service for Deploying and Scaling Web Applications and Services
- It handles automatically the Deployment of the uploaded Code - from Capacity Provisioning, Load Balancing and automatic Scaling to Application Health Monitoring

#### Amazon ECS (Elastic Container Service)

- Amazon ECS is a highly scalable and fast Container Management Service that simplifies Running, Terminating and Managing Containers in a Cluster

#### Amazon EC2 (Elastic Compute Cloud)

- Amazon EC2 provides Cloud Computing with a choice of the latest Processors, Storage, Networks, Operating Systems and Pricing Models to meet User Workload Requirements

## Docker

- Docker is a Platform for Building, Running and Shipping Applications
- It allows Developers to easily build and deploy Applications that run in Containers
- It ensures that local Development is the same as in any Environment
- Containers are an Abstraction at the Application Layer that packages Code and Dependencies together
- Multiple Containers can run on the same Machine, sharing the OS Kernel with other Containers, each running as an isolated Process in User Space

## Jib

- JIB is an open-source Containerization Tool that builds optimized Docker and OCI Images for Java Applications without requiring Docker Daemon or even Docker Installation on the Developer's Machine
- It builds Container Images directly from the Java Build without the Need to package the Application in a container
- It simplifies the Creation of Docker Images and also the Push to a Registry
- It provides optimized Layering and Caching Strategies that significantly reduce the Build Time for Container Images

### Docker Commands

| Command                               | Description                                               |
| ------------------------------------- | --------------------------------------------------------- |
| docker ps                             | List running Containers                                   |
| docker ps -a                          | List all Containers (including stopped ones)              |
| docker images                         | List Images                                               |
| docker run <image>                    | Run a Container from an Image                             |
| docker stop <container>               | Stop a running Container                                  |
| docker rm <container>                 | Remove a stopped Container                                |
| docker rmi <image>                    | Remove an Image                                           |
| docker build <path/to/dockerfile>     | Build an Image from a Dockerfile                          |
| docker tag <image> <new_name:tag>     | Tag an Image with a new Name and optional Tag             |
| docker login                          | Log in to a Docker Registry                               |
| docker push <image>                   | Push an Image to a Docker Registry                        |
| docker pull <image>                   | Pull an Image from a Docker Registry                      |
| docker exec -it <container> <command> | Execute a Command in a running Container                  |
| docker-compose up                     | Start all Services defined in a `docker-compose.yml` File |
| docker-compose down                   | Stop all Services defined in a `docker-compose.yml` File  |

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

### API (Application Programming Interface)

- An API is a Set of Protocols, Routines and Tools used to build Software Applications. An API defines how different Software Components should interact with each other. It provides a standard Way for different Software Applications to communicate and share Data
- APIs can be divided into different Types, such as RESTful APIs, SOAP APIs and GraphQL APIs. Each Type has its own Set of Characteristics and is suitable for different Use Cases.
- APIs are used for a variety of Purposes, such as Integrating with Third-Party Services, Building Web and Mobile Applications, Creating Software Libraries, and Automating Tasks

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

#### PSQL Commands

| Command                                                                                                                                                         | Description                 |
| --------------------------------------------------------------------------------------------------------------------------------------------------------------- | --------------------------- |
| docker ps --format="ID\t{{.ID}}\nNAME\t{{.Names}}\nIMAGE\t{{.Image}}\nPORTS\t{{.Ports}}\nCOMMAND\t{{.Command}}\nCREATED\t{{.CreatedAt}}\nSTATUS\t{{.Status}}\n" | List formatted Containers   |
| docker exec -it postgres bash                                                                                                                                   | Execute a Bash in Container |
| psql -U postgres                                                                                                                                                | Connect to PSQL             |
| psql -U postgres -d customer                                                                                                                                    | Connect to PSQL             |
| \q                                                                                                                                                              | Quit PSQL                   |
| \l                                                                                                                                                              | List Databases              |
| \c                                                                                                                                                              | Connect to a Database       |
| \dt                                                                                                                                                             | List Tables of Database     |

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

#### JWT (JSON Web Token)

- JWT is an open Standard that defines a compact and self-contained Way to securely transfer Information between Parties as a JSON Object
- The Information in the JWT can be verified and trusted because it is digitally signed. JWTs can be signed using a Secret (using the HMAC Algorithm) or a public/private Key Pair using RSA or ECDSA

##### Authorization

- Authorization is the most common Scenario for using the JWT. Once the User is logged in, each subsequent Request includes the JWT, allowing the User to access Routes, Services and Resources that are permitted with that JWT
- Single Sign On is a Feature where JWT is widely used today due to its low Overhead and Ability to be easily deployed across Domains

##### Information Exchange

- JWTs are a common Way to securely transfer Information between Parties. Because JWTs can be signed there, the Recipient can be sure that the Senders are who they say they are. And because the Signature is calculated using the Header and Payload of the JWT, it can be verified that the Content has not been tampered with

## JSON (JavaScript Object Notation)

- JSON is a lightweight Data Interchange Format that is easy for Humans to read and write, and easy for Machines to parse and generate
- JSON is used as an Alternative to XML for Transferring Data between a Server and a Web Application. It is a Text Format that is completely Language independent. JSON is based on a Subset of the JavaScript Programming Language
- JSON Data is represented as Key-Value Pairs, where the Keys are Strings and the Values can be any Data Type, such as Strings, Numbers, Booleans, Arrays, or other JSON Objects

## HTTP (Hypertext Transfer Protocol)

- HTTP is an Application Layer Protocol used to transfer Data over the Internet. It is the Foundation of the World Wide Web and is used to transfer Resources such as HTML Documents, Images, Videos and other Types of Files
- HTTP works on a Client-Server Model, where the Client sends Requests to the Server and the Server responds to those Requests. HTTP uses specific Methods to specify the Action that the Client wants the Server to perform
- The most common Methods are:
  - **GET**: retrieves Information from the Server
  - **POST**: sends Data to the Server
  - **PUT**: updates an existing Resource on the Server
  - **DELETE**: removes a Resource from the Server
- HTTP uses a Uniform Resource Identifier (URI) to identify the Resource the Client wants to access. The URI consists of a Scheme, a Domain Name or IP address, and a Path to the Resource on the Server
- HTTP also uses a Set of Status Codes to indicate the Outcome of a Request
- The most common Status Codes are:
  - **1xx (Informational)**: This Class of Status Codes indicates that the Request has been received and the Server is continuing to process it
  - **2xx (Successful)**: This Class of Status Codes indicates that the Request was successful and the Server has delivered the requested Content
  - **3xx (Redirection)**: This Class of Status Codes indicates that the Client needs to take further Action to complete the Request
  - **4xx (Client Error)**: This Class of Status Codes indicates that the Request contains bad Syntax or can not be fulfilled by the Server
  - **5xx (Server Error)**: This Class of Status Codes indicates that the Server encountered an Error while Attempting to fulfil the Request
- HTTP is a stateless Protocol, meaning that each Request is independent of all previous Requests. To maintain State, Web Applications use Techniques such as Cookies and Sessions

### URL (Uniform Resource Locator)

- A URL is the Address of a Resource on the Internet, such as a Web Page, Image, or File
- A URL consists of several Parts, which are:
  - **Protocol**: This indicates the Protocol used to access the Resource, such as HTTP, HTTPS, FTP, or others. The Protocol is followed by a Colon and two forward Slashes
  - **Domain Name or IP Address**: This identifies the Server on which the Resource is located. The Domain Name is a Human-readable Name, while the IP Address is a numerical Address
  - **Port Number**: This is the Port Number to which the Request should be sent. This is optional and is usually omitted if the default Port for the Protocol is used
  - **Path**: This is the Location of the Resource on the server, such as a File or Directory Path
  - **Query String**: This contains additional Parameters used to refine the Request or pass Data to the Server. It starts with a Question Mark and consists of Key-Value Pairs separated by Ampersands
  - **Fragment Identifier**: This identifies a specific Part of the Resource, such as a Section of a Web Page. It starts with a Hash Symbol followed by a Fragment Identifier

## SSH Keys

- SSH Keys are a Form of Authentication used in the Secure Shell (SSH) Protocol to establish a secure Connection between two Computers
- When a User connects to an SSH Server, the Server asks them to authenticate themselves. Typically, this involves entering a Username and Password. However, SSH Keys provide an Alternative and more secure Method of Authentication
- An SSH Key is a Pair of cryptographic Keys, consisting of a public Key and a private Key. The public Key is stored on the SSH Server, while the private Key is stored on the User's Computer. When a User connects to the Server, the Server sends a Challenge Message that the User's Computer signs using the private Key and sends back to the Server. The Server verifies the Signature against the corresponding public Key, and if the Signature is valid, the Connection is established

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
