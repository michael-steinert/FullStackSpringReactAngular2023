# React Application

- React is a JavaScript Library for Building User Interfaces
- It allows Developers to create reusable UI Components and manage the State of those Components in a simple and efficient Way
- It is based on a Component-based Architecture. Each component can have its own State, which determines how it should be rendered at any given Time
- It also uses a virtual DOM (Document Object Model), which is a lightweight Copy of the real DOM. This allows React to efficiently make UI Updates without having to redraw the entire Page

## Dependencies

- In a `npm` Project, Dependencies and DevDependencies refer to two different Types of Packages that a Project may require
  - Dependencies are Packages that the Project needs to run. They are Packages that are needed at Runtime and are therefore essential for the Project to work properly
  - DevDependencies are Packages that are only needed during Development. They are Packages that are used for Testing, Linting or Building the Project, but are not needed at Runtime
- Listing Packages as DevDependencies rather than Dependencies can help keep the Project's Dependencies lean and focused on what is actually needed to run the Application
- This can reduce the overall size of the Dependencies installed when the Application is deployed, as well as reducing the Potential for Security Vulnerabilities

## Hooks

- Hooks allow Developers to reuse stateful Logic across multiple Components. This is achieved by defining the stateful Logic in a Custom Hook, which can then be used in any Component that needs that Logic
- Each built-in Hook serves a specific Purpose and provides a Way to handle different Aspects of stateful Logic
  - **useState**: Allows Components to manage their own State. It returns a State Value and a Function to update that Value
  - **useEffect**: Allows Components to handle Side Effects, such as Fetching Data or Manipulating the DOM, after Rendering. It takes a Callback Function and an optional Array of Dependencies, and executes the Callback Function after Rendering and whenever the Dependencies change
  - **useContext**: Allows Components to access Context Data, such as Theme or Localization Information, without having to pass it through multiple Levels of Props
  - **useReducer**: Allows Components to manage complex State Logic, such as handling multiple related Values or updating State based on previous State

## Provider Pattern

- The Provider pattern is a Way of managing State and providing Data to components that need it, without having to pass Props through multiple Layers of Components
- It involves Creating a Provider Component that wraps around the entire Application and provides a Context that can be accessed by any Component within that Provider's Scope

# CORS (Cross-Origin Resource Sharing)

- CORS is a Security Feature implemented in Web Browsers to prevent malicious Websites from making unauthorized Requests to the Resources of another Website
- When a Website makes a Request to another Domain (i.e. another Origin), the Browser checks that the Server hosting the requested Resource allows the requesting Domain to access it. This is done using HTTP Headers sent with the Request and Response
- If the Server allows the requesting Domain to access the Resource, the Browser allows the Request to proceed and returns the requested Data to the original Website. If the Server does not allow the requesting Domain to access the Resource, the Browser blocks the Request and returns an CORS Error
- The CORS Mechanism is important because it prevents malicious Websites from stealing sensitive Information or performing Actions on behalf of a User on another Website

# Formik (Form Validation)

- Formik keeps Track of Values, Errors, Fields visited, orchestrates Validation and handles Submission
- This means less Time is spent on wiring State and Change Handlers and more Time is used to focus on Business Logic
- It keeps the Form State inherently local and ephemeral to the Component, and does not use external State Management Libraries
- This also makes Formik easy to deploy incrementally and keeps Bundle Size to a Minimum
- Yup is used to build Validation Schemas

## Yup (Schema Builder)

- Yup is a Schema Builder for Runtime Value Parsing and Validation.
- It allows to define a Schema, transform a Value to match it, assert the Form of an existing Value, or both
- Yup Schemas are highly expressive and allow the Modelling of complex, interdependent Validations or Value Transformations

# Local Storage

- Local Storage is a Web Browser Feature that allows Web Applications to store Data on a User's Device. This Data is stored locally on the Device, as opposed to being stored on a Remote Server
- Local storage is a Way for web applications to store Data, such as User Preferences or Application State, for later Retrieval
- It is based on the Key-Value Pair Data Structure, where Data is stored in a simple String Format. Web Applications can store Data in Local Storage using the Browser's JavaScript API. Data stored in Local Storage persists even after the User closes the Browser or restarts the Device
- One of the Advantages of Local Storage is that it allows Web Applications to store larger Amounts of Data than Cookies, another common Mechanism for Storing Data on a User's Device
- Local Storage also provides better Security than Cookies because it is not sent to the Server with every HTTP Request
- One of the Disadvantages of Local Storage is that Data stored in it can only be accessed by the Domain that created it, so a Web Application can not access Data stored by another Application. In addition, Local Storage is not designed to store sensitive Data such as Passwords, as it is not encrypted and can be accessed by anyone with Access to the Device

# Docker

| Command                                                                                        | Description                       |
| ---------------------------------------------------------------------------------------------- | --------------------------------- |
| docker build ./Dockerfile -t michaelxsteinert/react-frontend --build-arg api_base_url=URL:PORT | Build a Image from the Dockerfile |
| docker push michaelxsteinert/react-frontend                                                    | Push Image into Repository        |
