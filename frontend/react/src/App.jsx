import { Spinner, Text, Wrap, WrapItem } from "@chakra-ui/react";
import SidebarWithHeader from "./components/SideBar.jsx";
import { getCustomers } from "./services/client.js";
import { useEffect, useState } from "react";
import CardWithImage from "./components/Card.jsx";
import CreateCustomerDrawer from "./components/CreateCustomerDrawer.jsx";
import { errorNotification } from "./services/notification.js";

const App = () => {
  const [customers, setCustomers] = useState([]);
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState("");

  useEffect(() => {
    fetchCustomers();
  }, []);

  const fetchCustomers = () => {
    setIsLoading(true);
    getCustomers()
      .then((response) => {
        setCustomers(response.data);
      })
      .catch((error) => {
        console.error(error);
        errorNotification(error.code, error.response.data.message);
        setIsLoading(error);
      })
      .finally(() => {
        setIsLoading(false);
      });
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
            <CardWithImage
              {...customer}
              imageNumber={index}
              fetchCustomers={fetchCustomers}
            />
          </WrapItem>
        ))}
      </Wrap>
    </SidebarWithHeader>
  );
};

export default App;
