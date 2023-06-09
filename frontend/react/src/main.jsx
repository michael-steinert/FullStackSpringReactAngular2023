import { ChakraProvider, createStandaloneToast } from "@chakra-ui/react";
import React from "react";
import ReactDOM from "react-dom/client";
import { RouterProvider, createBrowserRouter } from "react-router-dom";
import Customer from "./Customer";
import Home from "./Home";
import Login from "./components/Login/Login";
import ProtectedRoute from "./components/ProtectedRoute";
import Register from "./components/Register/Register";
import AuthenticationProvider from "./context/AuthenticationContext";
import "./index.css";

const { ToastContainer } = createStandaloneToast();

const root = ReactDOM.createRoot(document.getElementById("root"));
const router = createBrowserRouter([
  {
    path: "/",
    element: <Login />,
  },
  {
    path: "/register",
    element: <Register />,
  },
  {
    path: "/dashboard",
    element: (
      <ProtectedRoute>
        <Home />
      </ProtectedRoute>
    ),
  },
  {
    path: "dashboard/customers",
    element: (
      <ProtectedRoute>
        <Customer />
      </ProtectedRoute>
    ),
  },
]);

root.render(
  <React.StrictMode>
    <ChakraProvider>
      <AuthenticationProvider>
        <RouterProvider router={router} />
      </AuthenticationProvider>
      <ToastContainer />
    </ChakraProvider>
  </React.StrictMode>
);
