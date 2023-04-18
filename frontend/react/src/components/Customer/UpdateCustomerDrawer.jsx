import {
  Button,
  Drawer,
  DrawerBody,
  DrawerCloseButton,
  DrawerContent,
  DrawerFooter,
  DrawerHeader,
  DrawerOverlay,
  useDisclosure,
} from "@chakra-ui/react";
import UpdateCustomerForm from "./UpdateCustomerForm.jsx";

const AddIcon = () => "+";

const CloseIcon = () => "x";

const UpdateCustomerDrawer =({
  fetchCustomers,
  customerId,
  initialValues,
}) => {
  const { isOpen, onOpen, onClose } = useDisclosure();
  return (
    <>
      <Button
        bg="gray.200"
        color="black"
        rounded="full"
        _hover={{
          transform: "translateY(-2px)",
          boxShadow: "lg",
        }}
        onClick={onOpen}
      >
        Update Customer
      </Button>
      <Drawer isOpen={isOpen} onClose={onClose} size="xl">
        <DrawerOverlay />
        <DrawerContent>
          <DrawerCloseButton />
          <DrawerHeader>Update current Customer</DrawerHeader>
          <DrawerBody>
            <UpdateCustomerForm
              fetchCustomers={fetchCustomers}
              customerId={customerId}
              initialValues={initialValues}
            />
          </DrawerBody>
          <DrawerFooter>
            <Button
              leftIcon={<CloseIcon />}
              colorScheme="teal"
              onClick={onClose}
            >
              Close
            </Button>
          </DrawerFooter>
        </DrawerContent>
      </Drawer>
    </>
  );
}

export default UpdateCustomerDrawer;