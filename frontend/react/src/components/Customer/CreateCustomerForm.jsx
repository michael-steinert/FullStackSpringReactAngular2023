import {
  Alert,
  AlertIcon,
  Box,
  Button,
  FormLabel,
  Input,
  Select,
  Stack,
} from "@chakra-ui/react";
import { Form, Formik, useField } from "formik";
import * as Yup from "yup";
import { saveCustomer } from "../../services/client.js";
import {
  errorNotification,
  successNotification,
} from "../../services/notification.js";

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

const CustomSelect = ({ label, ...props }) => {
  const [field, meta] = useField(props);
  return (
    <Box>
      <FormLabel htmlFor={props.id || props.name}>{label}</FormLabel>
      <Select {...field} {...props} />
      {meta.touched && meta.error ? (
        <Alert className="error" status="error" mt={2}>
          <AlertIcon />
          {meta.error}
        </Alert>
      ) : null}
    </Box>
  );
};

export default function CreateCustomerForm({ fetchCustomers }) {
  return (
    <Formik
      initialValues={{
        name: "",
        email: "",
        password: "",
        age: 0,
        gender: "",
      }}
      validateOnMount={true}
      validationSchema={Yup.object({
        name: Yup.string()
          .max(15, "Must be 15 Characters or less")
          .required("Name required"),
        email: Yup.string()
          .email("Must be 20 Characters or less")
          .required("Email required"),
        password: Yup.string()
          .max(7, "Must be 7 Characters or more")
          .max(20, "Must be 20 Characters or less")
          .required("Password required"),
        age: Yup.number()
          .min(1, "Must be at least 1 Year of Age")
          .max(100, "Must be less than 100 Years of Age")
          .required(),
        gender: Yup.string()
          .oneOf(["MALE", "FEMALE"], "Invalid Gender")
          .required("Gender required"),
      })}
      onSubmit={async (customerFormValues, { setSubmitting }) => {
        try {
          setSubmitting(true);
          const response = await saveCustomer(customerFormValues);
          console.log(response);
          successNotification(
            "Customer saved",
            `${customerFormValues.name} was successfully saved`
          );
          // Fetch new Customers after one was added
          fetchCustomers();
        } catch (error) {
          console.error(error);
          errorNotification(error.code, error.response.data.message);
        } finally {
          setSubmitting(false);
        }
      }}
    >
      {({ isValid, isSubmitting }) => (
        <Form>
          <Stack spacing="24px">
            <CustomTextInput
              label="Name"
              name="name"
              type="text"
              placeholder="Bruno"
            />
            <CustomTextInput
              label="Email Address"
              name="email"
              type="email"
              placeholder="bruno@mail.com"
            />
            <CustomTextInput label="Password" name="password" type="password" />
            <CustomTextInput
              label="Age"
              name="age"
              type="number"
              placeholder="14"
            />
            <CustomSelect label="Gender" name="gender">
              <option value="">Select Gender</option>
              <option value="MALE">Male</option>
              <option value="FEMALE">Female</option>
            </CustomSelect>
            <Button disabled={!isValid || isSubmitting} type="submit">
              Submit
            </Button>
          </Stack>
        </Form>
      )}
    </Formik>
  );
}
