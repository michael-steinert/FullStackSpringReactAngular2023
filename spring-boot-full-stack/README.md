# Spring Boot Full Stack

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
- 