import { Flex, Heading, Image, Link, Stack } from "@chakra-ui/react";
import { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { useAuthentication } from "../../context/AuthenticationContext";
import CreateCustomerForm from "../Customer/CreateCustomerForm.jsx";

const Register = () => {
  const { customer, setCustomerFromJwt } = useAuthentication();
  const navigate = useNavigate();

  useEffect(() => {
    if (customer) {
      // If Customer is present, then redirect to Dashboard
      navigate("/dashboard/customers");
    }
  }, []);

  return (
    <Stack minH="100vh" direction={{ base: "column", md: "row" }}>
      <Flex p={8} flex={1} justifyContent="center" alignItems="center">
        <Stack spacing={4} w="full" maxW="md">
          <Heading fontSize="2xl" mb={12}>
            Register an Account
          </Heading>
          <CreateCustomerForm
            onCreatedCustomer={(jwt) => {
              // Save JWT into Local Storage
              localStorage.setItem("access-token", jwt);
              setCustomerFromJwt();
              navigate("/dashboard/customers");
            }}
          />
          <Link href="/" color="blue.500">
            Login with Account
          </Link>
        </Stack>
      </Flex>
      <Flex
        flex={1}
        p={10}
        flexDirection="column"
        alignItems="center"
        justifyContent="center"
        bgGradient={{ sm: "linear(to-r, blue.600, purple.600)" }}
      >
        <Text fontSize="6xl" color="white" fontWeight="bold" mb={4}>
          <Link href="#">Visit Site</Link>
        </Text>
        <Image
          alt="Login Image"
          objectFit="scale-down"
          src={
            "https://images.unsplash.com/photo-1486312338219-ce68d2c6f44d?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1352&q=80"
          }
        />
      </Flex>
    </Stack>
  );
};

export default Register;
