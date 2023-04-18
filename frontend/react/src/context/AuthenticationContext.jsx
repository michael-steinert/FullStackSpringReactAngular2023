import jwtDecode from "jwt-decode";
import { createContext, useContext, useEffect, useState } from "react";
import { login as performLogin } from "../services/client";

const AuthenticationContext = createContext({});

const AuthenticationProvider = ({ children }) => {
  const [customer, setCustomer] = useState(null);

  useEffect(() => {
    setCustomerFromJwt();
  }, []);

  const setCustomerFromJwt = () => {
    const jwt = localStorage.getItem("access-token");
    if (jwt) {
      const decodedJwt = jwtDecode(jwt);
      setCustomer({
        username: decodedJwt.sub,
        roles: decodedJwt.scopes,
      });
    }
  };

  // const login = async (customerCredentials) => {
  //   return new Promise((resolve, reject) => {
  //     performLogin(customerCredentials)
  //       .then((response) => {
  //         const jwt = response.headers["authorization"];
  //         setCustomer({
  //           username: jwt.sub,
  //           roles: token.scopes,
  //         });
  //         resolve(response);
  //       })
  //       .catch((error) => {
  //         reject(error);
  //       });
  //   });
  // };

  const login = async (customerCredentials) => {
    try {
      const response = await performLogin(customerCredentials);
      const jwt = response.headers["authorization"];
      // Save JWT into Local Storage
      localStorage.setItem("access-token", jwt);
      setCustomerFromJwt();
      return response;
    } catch (error) {
      throw error;
    }
  };

  const isCustomerAuthenticated = () => {
    const jwt = localStorage.getItem("access-token");
    if (!jwt) {
      return false;
    }
    const { exp: expiration } = jwtDecode(jwt);
    // Expired JWT, therefore logout Customer
    if (Date.now() > expiration * 1000) {
      logout();
      return false;
    }
    return true;
  };

  const logout = () => {
    localStorage.removeItem("access-token");
    setCustomer(null);
  };

  return (
    <AuthenticationProvider
      value={{
        customer,
        login,
        isCustomerAuthenticated,
        logout,
        setCustomerFromJwt,
      }}
    >
      {children}
    </AuthenticationProvider>
  );
};

export const useAuthentication = () => useContext(AuthenticationContext);

export default AuthenticationProvider;
