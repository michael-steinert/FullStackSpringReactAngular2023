# React

- React is a JavaScript Library for Building User Interfaces
- It allows Developers to create reusable UI Components and manage the State of those Components in a simple and
  efficient Way
- It is based on a Component-based Architecture. Each component can have its own State, which determines how it should
  be rendered at any given Time
- It also uses a virtual DOM (Document Object Model), which is a lightweight Copy of the real DOM. This allows React to
  efficiently make UI Updates without having to redraw the entire Page

## Dependencies

- In a `npm` Project, Dependencies and DevDependencies refer to two different Types of Packages that a Project may
  require
    - Dependencies are Packages that the Project needs to run. They are Packages that are needed at Runtime and are
      therefore essential for the Project to work properly
    - DevDependencies are Packages that are only needed during Development. They are Packages that are used for Testing,
      Linting or Building the Project, but are not needed at Runtime
- Listing Packages as DevDependencies rather than Dependencies can help keep the Project's Dependencies lean and focused
  on what is actually needed to run the Application
- This can reduce the overall size of the Dependencies installed when the Application is deployed, as well as reducing
  the Potential for Security Vulnerabilities

## Hooks

- Hooks allow Developers to reuse stateful Logic across multiple Components. This is achieved by defining the stateful
  Logic in a Custom Hook, which can then be used in any Component that needs that Logic
- Each built-in Hook serves a specific Purpose and provides a Way to handle different Aspects of stateful Logic
    - __useState__: Allows Components to manage their own State. It returns a State Value and a Function to update that
      Value
    - __useEffect__: Allows Components to handle Side Effects, such as Fetching Data or Manipulating the DOM, after
      Rendering. It takes a Callback Function and an optional Array of Dependencies, and executes the Callback Function
      after Rendering and whenever the Dependencies change
    - __useContext__: Allows Components to access Context Data, such as Theme or Localisation Information, without
      having to pass it through multiple Levels of Props
    - __useReducer__: Allows Components to manage complex State Logic, such as handling multiple related Values or
      updating State based on previous State