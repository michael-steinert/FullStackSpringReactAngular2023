# Angular

- Angular uses two-way Binding to reflect Changes in the User Interface in the Data Model and vice versa
- This eliminates the Need for manual Synchronization between the User Interface the Data Model
- Angular makes it easier to implement dynamic and interactive User Interfaces

## Components

- Every Angular App has at least one Component, called the Root Component, which connects the Component Hierarchy to the Page Document Object Model (DOM)
- Each Component has a Class containing the Logic in TypeScript Format and a View in an HTML Template
- Components have three Main Parts: Templates, Services and Dependencies, and Directives
- Templates define the View of the Page and provide Logic through Directives and Data Binding that links Application Data to the DOM of the Page
- Data Binding has two Types: Event Binding and Property Binding
- Event Binding responds to Changes in Events, while Property Binding changes based on Data in the Application
- Services and Dependencies use Dependency Injection to allow access to Services by Subscribing to them. They act as Delegates to the Service and can be injected into a Component
- Metadata is a Set of Information about Data, and for each Component, Metadata includes a View Template that combines ordinary HTML with Angular Directives and Binding, as well as Service Class Metadata, which provides Information for Angular to make it available to Components through Dependency Injection
- Directives such as ngFor, ngIf, ngSwitch, or Custom Directives, are used within a Component. They provide Instructions to the Compiler to manipulate the DOM when Rendering the Template

<p align="center">
  <img src="https://user-images.githubusercontent.com/29623199/233357273-9775ddcd-1441-46c4-a48d-a8aa6a581b45.png" alt="Angular_Fundamentals" />
</P>

## Modules

- A Module is a Container that organizes related Components, Directives, Pipes, and Services, along with their Configurations
- A Module helps to manage and reuse Code by Dividing it into Logical Units
- The Root Module is the first Module in any Angular Application and is responsible for Importing and Configuring other Modules and for Bootstrapping the Application
- Additional Modules can be created to further organize the Code, such as a Module for a specific Feature of the Application or a Module for a shared Set of Components, Directives, and Services
- Modules can also be imported and exported using the ngModule Decorator, allowing Components, Directives, Pipes, and Services to be used in other Modules
- Modules also provide a Mechanism for Lazy Loading, which can improve the Performance of the Application and reduce Initial Load Times

## Angular CLI

- The Angular CLI can be run without globally Installation as following

```console
npx -p @angular/cli ng g c MyComponent
```
