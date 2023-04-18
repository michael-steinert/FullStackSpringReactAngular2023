import { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { useAuthentication } from "../context/AuthenticationContext";

const ProtectedRoute = ({ children }) => {
  const navigate = useNavigate();
  const { isCustomerAuthenticated } = useAuthentication();

  useEffect(() => {
    if (!isCustomerAuthenticated()) {
      navigate("/");
    }
  });

  return isCustomerAuthenticated() ? children : null;
};

export default ProtectedRoute;
