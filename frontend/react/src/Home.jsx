import { Text } from "@chakra-ui/react";
import  SidebarWithHeader  from "./components/SideBar.jsx";

const Home = () => {
  return (
    <SidebarWithHeader>
      <Text fontSize="2xl">Dashboard</Text>
    </SidebarWithHeader>
  );
};

export default Home; 
