import axios from "axios";

const getAuthenticationConfig = () => ({
  headers: {
    Authorization: `Bearer ${localStorage.getItem("access-token")}`,
  },
});

export const getCustomers = async () => {
  try {
    return await axios.get(
      `${import.meta.env.VITE_API_BASE_URL}/api/v1/customers`,
      getAuthenticationConfig()
    );
  } catch (error) {
    throw error;
  }
};

export const saveCustomer = async (customer) => {
  try {
    return await axios.post(
      `${import.meta.env.VITE_API_BASE_URL}/api/v1/customers`,
      customer
    );
  } catch (error) {
    throw error;
  }
};

export const updateCustomer = async (id, updatedCustomer) => {
  try {
    return await axios.put(
      `${import.meta.env.VITE_API_BASE_URL}/api/v1/customers/${id}`,
      updatedCustomer,
      getAuthenticationConfig()
    );
  } catch (error) {
    throw error;
  }
};

export const deleteCustomer = async (id) => {
  try {
    return await axios.delete(
      `${import.meta.env.VITE_API_BASE_URL}/api/v1/customers/${id}`,
      getAuthenticationConfig()
    );
  } catch (error) {
    throw error;
  }
};

export const login = async (customerCredentials) => {
  try {
    return await axios.post(
      `${import.meta.env.VITE_API_BASE_URL}/api/v1/authentication/login`,
      customerCredentials
    );
  } catch (error) {
    throw error;
  }
};
