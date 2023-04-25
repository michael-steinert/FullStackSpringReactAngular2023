import {
  Alert,
  AlertIcon,
  Box,
  Button,
  FormLabel,
  Input,
  Stack,
  VStack,
} from "@chakra-ui/react";
import { Form, Formik, useField } from "formik";
import { useCallback } from "react";
import { useDropzone } from "react-dropzone";
import * as Yup from "yup";
import {
  customerImageUrl,
  updateCustomer,
  uploadImage,
} from "../../services/client.js";
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

const ImageDropzone = ({ customerId, fetchCustomers }) => {
  const onDrop = useCallback(async (acceptedFiles) => {
    try {
      const formData = new FormData();
      formData.append("file", acceptedFiles[0]);
      await uploadImage(customerId, formData);
      successNotification("Success", "Image uploaded");
      fetchCustomers();
    } catch (error) {
      errorNotification("Error", "Image not uploaded");
      console.error(error);
    }
  }, []);
  const { getRootProps, getInputProps, isDragActive } = useDropzone({ onDrop });

  return (
    <Box
      w="100%"
      textAlign="center"
      border="dashed"
      borderColor="gray.300"
      borderRadius="lg"
      rounded="md"
      p={4}
      {...getRootProps()}
    >
      <input {...getInputProps()} />
      {isDragActive ? (
        <p>Drop the Image here</p>
      ) : (
        <p>Drag 'n' drop Image here, or click to select Image</p>
      )}
    </Box>
  );
};

const UpdateCustomerForm = ({ fetchCustomers, customerId, initialValues }) => {
  return (
    <>
      <VStack spacing={2} mb={2}>
        <Image
          src={customerImageUrl(customerId)}
          borderRadius="full"
          boxSize="150px"
          objectFit="cover"
        />
        <ImageDropzone
          customerId={customerId}
          fetchCustomers={fetchCustomers}
        />
      </VStack>
      <Formik
        initialValues={initialValues}
        validateOnMount={true}
        validationSchema={Yup.object({
          name: Yup.string()
            .max(15, "Must be 15 Characters or less")
            .required("Name required"),
          email: Yup.string()
            .email("Must be 20 Characters or less")
            .required("Email required"),
          age: Yup.number()
            .min(1, "Must be at least 1 Year of Age")
            .max(100, "Must be less than 100 Years of Age")
            .required(),
        })}
        onSubmit={async (customerFormValues, { setSubmitting }) => {
          try {
            setSubmitting(true);
            const response = await updateCustomer(
              customerId,
              customerFormValues
            );
            console.log(response);
            successNotification(
              "Customer updated",
              `${customerFormValues.name} was successfully updated`
            );
            // Fetch Customers after one was updated
            fetchCustomers();
          } catch (error) {
            console.error(error);
            errorNotification(error.code, error.response.data.message);
          } finally {
            setSubmitting(false);
          }
        }}
      >
        {({ isValid, isSubmitting, dirty }) => (
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
              <CustomTextInput
                label="Age"
                name="age"
                type="number"
                placeholder="14"
              />
              <Button
                disabled={!(isValid && dirty) || isSubmitting}
                type="submit"
              >
                Submit
              </Button>
            </Stack>
          </Form>
        )}
      </Formik>
    </>
  );
};

export default UpdateCustomerForm;
