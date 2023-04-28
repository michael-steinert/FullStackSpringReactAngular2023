# Full Stack Application with AWS, Spring, React and Angular

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

### CI/CD (Continuous Integration/Continuous Delivery)

- CI/CD is an Approach to Software Development that aims to improve the Speed, Efficiency and Reliability of Software Delivery
- It involves frequent code Integration, automated Testing and continuous Deployment of Software Changes into the Production Environment
- The following four Steps of a CI/CD Pipeline work together to ensure that Software Changes are automatically tested, integrated and deployed into Production Environment. This Automation helps to reduce Errors, increase Efficiency and improve overall Software Quality
  - **Code Integration**: In this Step, Developers commit their Code Changes to a remote Repository where the Code is merged into the main Codebase. The Purpose of this Step is to ensure that the Code Changes are compatible with the Rest of the Codebase and do not break the Build
  - **Automated Testing**: This Step is where the integrated Code is automatically tested. This involves Running a Series of Tests to ensure that the Code Changes are functional, meet the expected Quality Standards and are free of Defects. This Step helps to identify Problems early in the Development Process, allowing Developers to fix them quickly and efficiently
  - **Continuous Deployment**: In this Step, the tested Code Changes are automatically and continuously deployed to a Staging Environment for further Testing. The Aim of this Step is to ensure that the Software is continuously updated with the latest Code Changes, so that new Features and Functionality can be delivered to Users quickly and efficiently
  - **Production Deployment**: In this Step, the Code Changes are deployed into the Production Environment. This is where the Code Changes are released to End Users. This Step involves Monitoring the Production Environment, ensuring that the Software is running smoothly, and Identifying and Resolving any Issues that arise
- By Adopting a CI/CD Pipeline, Development Teams can achieve faster Release Cycles, reduce the Risk of Software Defects, and improve the User Experience
- The Key Benefits of CI/CD are as follows:
- **Faster Release Cycles**: By Automating the Testing and Deployment Process, CI/CD enables Development Teams to release Software more frequently and respond quickly to Customer Needs
- **Improved Quality**: Automated Testing ensures that Software Changes do not introduce new Defects or Problems, improving the overall Quality of the Software
- **Increased Collaboration**: Frequent Code Integration and Testing requires Developers to work closely together, leading to better Collaboration and Communication
- **Reduced Risk**: Continuous Delivery enables Developers to identify and fix Problems quickly, reducing the Risk of major Failures and Downtime
- **Cost-Effective**: CI/CD reduces the Amount of manual Work required to deploy Software Changes, saving Time and reducing Costs

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

#### AWS Infrastructure

<p align="center">
  <img src="https://user-images.githubusercontent.com/29623199/232732441-d6c3df4a-853b-4e2a-a701-526c4d8e9bc3.png" alt="AWS_Infrastructure" />
</P>

#### AWS improved Infrastructure

<p align="center">
  <img src="https://user-images.githubusercontent.com/29623199/232767127-f3681c09-40d2-4adb-8f48-b30f12fbef20.png" alt="AWS_Improved_Infrastructure" />
</P>

#### AWS IAM (Identity and Access Management)

- An AWS IAM Policy is a Document that defines Permissions for an IAM Identity, which can be a User, Group, or Role. The Policy is essentially a Set of Rules that determine what Actions an Identity is allowed or denied to perform on AWS Resources
- An IAM Policy consists of one or more Policy Statements, each of which contains a List of Actions, Resources, and Conditions that define the Permissions for the Identity. For Example, a Policy Statement might allow a User to read Objects in a particular S3 Bucket, but deny the Ability to delete them
- IAM Policies can be attached to IAM Identities, meaning that the Permissions defined in the Policy apply to the Identity. Policies can also be used in Combination with other AWS Services, such as AWS Organizations and Amazon SNS, to provide granular Access Control across Resources and Services
- AWS provides a Number of predefined Policies that cover common Use Cases, such as Read-only Access to Resources or Full Access to a particular Service

##### AWS IAM Policy Simulator

- The AWS IAM Policy Simulator is a Web-based Tool provided by AWS that allows Users to simulate the Effects of IAM Policies to verify that the Policies provide the intended Level of Access to AWS Resources
- The IAM Policy Simulator works by allowing Users to create hypothetical Scenarios in which an IAM Policy is applied to an AWS Resource, and then determine what Actions the associated IAM Identity can or can not perform on that Resource. It provides a detailed Report of the Access that the Identity would have in the Scenario, which can be used to identify and troubleshoot Issues with the IAM Policy
- It also allows Users to test the impact of multiple Policies on a single Resource and view the combined Results of those Policies

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

#### Amazon Route 53

- Amazon Route 53 is a highly available and scalable Cloud DNS (Domain Name System) Web service

#### AWS Elastic Beanstalk

- Elastic Beanstalk is a Service for Deploying and Scaling Web Applications and Services
- It handles automatically the Deployment of the uploaded Code - from Capacity Provisioning (Infrastructure), Load Balancing and automatic Scaling to Application Health Monitoring

#### AWS ALB (Application Load Balancer)

- A Load Balancer is a Device or Software Component that distributes incoming Network Traffic across multiple Servers or Resources
- Its primary Function is to optimize Resource Usage, maximize Throughput, minimize Response Time and avoid Overloading any one Resource
- Some of the Benefits of Load Balancers are:
  - **Increased Reliability**: Load Balancing can help ensure high Availability and Uptime by distributing Traffic across multiple Servers or Resources. If one Server fails, the Load Balancer can automatically redirect Traffic to another Server
  - **Improved Scalability**: Load Balancing can help scale up or down as needed by Adding or Removing Servers or Resources from the Pool. This can help improve Performance and handle sudden Spikes in Traffic
  - **Better Performance**: Load Balancing can help distribute Traffic more evenly across Resources, which can help reduce Response Time and increase Throughput
  - **Simplified Maintenance**: Load Balancing can help simplify Maintenance Tasks such as Software Updates, Hardware Replacements and Backups by allowing Servers or Resources to be taken offline without disrupting Traffic
  - **Improved Security**: Load Balancing can help improve Security by providing a single Point of Entry for Traffic, which can help prevent Attacks such as Distributed Denial of Service (DDoS) Attacks

#### Amazon ECS (Elastic Container Service)

- Amazon ECS is a highly scalable and fast Container Management Service that simplifies Running, Terminating and Managing Containers in a Cluster

#### Amazon EC2 (Elastic Compute Cloud)

- Amazon EC2 provides Cloud Computing with a choice of the latest Processors, Storage, Networks, Operating Systems and Pricing Models to meet User Workload Requirements

#### Amazon S3 (Simple Storage Service)

- Amazon S3 is a Cloud-based Object Storage Service provided by AWS. It allows Users to store and retrieve Data from anywhere on the Internet at any Time, with high Durability, Availability, and Scalability
- It offers features such as Versioning, Lifecycle Policies, multi-region Replication, and Access Control, allowing Users to manage their Data securely and efficiently
- It is also integrated with other AWS Services, such as Amazon EC2, making it a powerful and versatile Storage Solution for many Types of Workloads

##### S3 Bucket

- An S3 Bucket is a logical Container for Storing Objects in Amazon S3. It is a universal Namespace that can be used to store and retrieve any Amount of Data, from anywhere on the Internet, at any Time
- S3 Buckets can be used to store a wide variety of Data Types. Each Item stored in an S3 Bucket is uniquely identified by a Key that can be used to retrieve the Item
- S3 Buckets can be created and managed using the AWS Management Console, AWS CLI, or programmatically using AWS SDK
- Users can set Bucket-Level Permissions to control Access to Objects stored in the Bucket, and can also use Bucket Policies to grant or restrict Access to specific Users or Groups

## Docker

- Docker is a Tool for Containerizing and Deploying Applications that provides a standardized Way to Package Applications, along with their Dependencies and Runtime Environment, into a Container that can be easily distributed and run on different Environments
- Docker also allows the Building, Running and Shipping of Applications
- Containerizing means to build and deploy Applications that run in Containers
- To make the Containerization Process even more efficient, Registries have been introduced to allow Developers to store and manage their Container Images
- It ensures that local Development is the same as in any Environment
- Containers are an Abstraction at the Application Layer that packages Code and Dependencies together
- Multiple Containers can run on the same Machine, sharing the OS Kernel with other Containers, each running as an isolated Process in User Space

### Docker Image

- A Docker Image is a lightweight, self-contained, executable Package that contains everything needed to run an Application, including the Application code, Runtime, Libraries and System Tools
- Docker Images are created using a Dockerfile, which is a Script that specifies the Steps to build the Image
- To build a Docker Image, a Dockerfile must first be created, specifying the Base Image, any additional Dependencies, and the Commands to install and configure the Application. Once the Dockerfile is created, the Docker `build` Command can be used to build the Image
- The Docker `build` Command takes the Path to the Dockerfile and uses it to build the Image. These Command first reads the Dockerfile, downloads any necessary Dependencies, and builds an Image from the specified Configuration. The resulting Image can then be used to run Containers of it

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

## API (Application Programming Interface)

- An API is a Set of Protocols, Routines and Tools used to build Software Applications. An API defines how different Software Components should interact with each other. It provides a standard Way for different Software Applications to communicate and share Data
- APIs can be divided into different Types, such as RESTful APIs, SOAP APIs and GraphQL APIs. Each Type has its own Set of Characteristics and is suitable for different Use Cases.
- APIs are used for a variety of Purposes, such as Integrating with Third-Party Services, Building Web and Mobile Applications, Creating Software Libraries, and Automating Tasks

## JWT (JSON Web Token)

- JWT is an open Standard that defines a compact and self-contained Way to securely transfer Information between Parties as a JSON Object
- The Information in the JWT can be verified and trusted because it is digitally signed. JWTs can be signed using a Secret (using the HMAC Algorithm) or a public/private Key Pair using RSA or ECDSA

### Authorization

- Authorization is the most common Scenario for using the JWT. Once the User is logged in, each subsequent Request includes the JWT, allowing the User to access Routes, Services and Resources that are permitted with that JWT
- Single Sign On is a Feature where JWT is widely used today due to its low Overhead and Ability to be easily deployed across Domains

### Information Exchange

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

## URL (Uniform Resource Locator)

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

## SSL (Secure Socket Layer) and TLS (Transport Layer Security)

- SSL and TLS are cryptographic Protocols used to secure Communications over the Internet. They establish a secure Channel between a Client and a Server, allowing sensitive Information to be transmitted without the Risk of Interception or Tampering by unauthorized third Parties
  - The Following is a simplified Explanation of how SSL/TLS works:
  - The Client sends a Request to the Server to initiate an SSL/TLS Session
  - The Server responds with its digital Certificate, which contains its Public Key
  - The Client verifies the Certificate's Authenticity by checking the Certificate Chain that links the Server's Certificate to a trusted Root Certificate Authority
  - The Client generates a random Session Key and encrypts it with the Server's Public Key
  - The Server decrypts the Session Key using its Private Key
  - The Client and Server use the Session Key to encrypt and decrypt Data exchanged during the Session
  - At the End of the Session, the Session Key is discarded and the Process begins again for the next Session
- This Process ensures that only the Client and Server can read the Data exchanged during the Session
- SSL/TLS also provides Data Integrity by using Hash Functions to ensure that the Data has not been altered in Transit. This helps to prevent Attacks such as Man-in-the-Middle Attacks, where an Attacker intercepts and modifies Data as it passes between the Client and Server
