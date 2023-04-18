import {
  Button,
  Flex,
  FormLabel,
  Heading,
  Image,
  Input,
  Link,
  Stack,
} from "@chakra-ui/react";
import { Form, Formik } from "formik";
import { useNavigate } from "react-router-dom";
import * as Yup from "yup";
import { useAuthentication } from "../../context/AuthenticationContext";
import { errorNotification } from "../../services/notification";
import { useEffect } from "react";

const CustomTextInput = ({ label, ...props }) => {
  // useField() returns [formik.getFieldProps(), formik.getFieldMeta()] which can be spread on <input>
  // The Field Meta can be used to display an Error Message if the Field is invalid and has been touched (i.e. visited)
  const [field, meta] = useField(props);
  return (
    <Box>
      <FormLabel htmlFor={props.id || props.name}>{label}</FormLabel>
      <Input className="text-input" {...field} {...props} />
      {meta.touched && meta.error ? (
        <Alert className="error" status="error" mt={2}>
          <AlertIcon />
          {meta.error}
        </Alert>
      ) : null}
    </Box>
  );
};

function LoginForm() {
  const { login } = useAuthentication();
  const navigate = useNavigate();

  return (
    <Formik
      initialValues={{ username: "", password: "" }}
      onSubmit={async (values, { setSubmitting }) => {
        setSubmitting(true);
        try {
          await login(values);
          // After successful Login redirect Customer to Dashboard
          navigate("/dashboard");
        } catch (error) {
          errorNotification(error.code, error.response.data.message);
        } finally {
          // Regardless of success or error, setSubmitting to false
          setSubmitting(false);
        }
      }}
      validateOnMount={true}
      validationSchema={Yup.object({
        username: Yup.string()
          .email("Must be valid Email")
          .required("Username is required"),
        password: Yup.string()
          .max(20, "Max Password Length is 20")
          .required("Password is required"),
      })}
    >
      {({ isValid, isSubmitting }) => (
        <Form>
          <Stack spacing={12}>
            <CustomTextInput label="Email" name="username" type="email" />
            <CustomTextInput label="Password" name="password" type="password" />
            <Button type="submit" disabled={!isValid || !isSubmitting}>
              Login
            </Button>
          </Stack>
        </Form>
      )}
    </Formik>
  );
}

export default function Login() {
  const { customer } = useAuthentication();
  const navigate = useNavigate();

  useEffect(() => {
    if (customer) {
      // If Customer is present, then redirect to Dashboard
      navigate("/dashboard");
    }
  }, []);

  return (
    <Stack minH="100vh" direction={{ base: "column", md: "row" }}>
      <Flex p={8} flex={1} justifyContent="center" alignItems="center">
        <Stack spacing={4} w="full" maxW="md">
          <Heading fontSize="2xl" mb={12}>
            Sign in to Account
          </Heading>
          <LoginForm />
          <Link href="/register" color="blue.500">Register an Account</Link>
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
}
