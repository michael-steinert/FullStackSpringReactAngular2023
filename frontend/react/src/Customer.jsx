import { Spinner, Text, Wrap, WrapItem } from "@chakra-ui/react";
import { useEffect, useState } from "react";
import CreateCustomerDrawer from "./components/Customer/CreateCustomerDrawer.jsx";
import CardWithImage from "./components/Customer/CustomerCard.jsx";
import SidebarWithHeader from "./components/SideBar.jsx";
import { getCustomers } from "./services/client.js";
import { errorNotification } from "./services/notification.js";

const Customer = () => {
  const [customers, setCustomers] = useState([]);
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState("");

  useEffect(() => {
    fetchCustomers();
  }, []);

  const fetchCustomers = async () => {
    setIsLoading(true);
    try {
      const response = await getCustomers();
      setCustomers(response.data);
    } catch (error) {
      console.error(error);
      errorNotification(error.code, error.response.data.message);
      setIsLoading(error);
    } finally {
      setIsLoading(false);
    }
  };

  if (isLoading) {
    return (
      <SidebarWithHeader>
        <Spinner />
      </SidebarWithHeader>
    );
  }

  if (error) {
    return (
      <SidebarWithHeader>
        <CreateCustomerDrawer fetchCustomers={fetchCustomers} />
        <Text mt={4}>Error occurred</Text>
      </SidebarWithHeader>
    );
  }

  if (customers.length <= 0) {
    return (
      <SidebarWithHeader>
        <CreateCustomerDrawer fetchCustomers={fetchCustomers} />
        <Text mt={4}>No Customers available</Text>
      </SidebarWithHeader>
    );
  }

  return (
    <SidebarWithHeader>
      <CreateCustomerDrawer fetchCustomers={fetchCustomers} />
      <Wrap justify="center" spacing="42px">
        {customers.map((customer, index) => (
          <WrapItem key={index}>
            <CardWithImage {...customer} fetchCustomers={fetchCustomers} />
          </WrapItem>
        ))}
      </Wrap>
    </SidebarWithHeader>
  );
};

export default Customer;
