import {Spinner, Text, Wrap, WrapItem} from "@chakra-ui/react";
import SidebarWithHeader from "./components/SideBar.jsx";
import {getCustomers} from "./services/client.js";
import {useEffect, useState} from "react";
import CardWithImage from "./components/Card.jsx";

const App = () => {
    const [customers, setCustomers] = useState([])
    const [isLoading, setIsLoading] = useState(false)
    useEffect(() => {
        setIsLoading(true)
        getCustomers().then(response => {
            setCustomers(response.data)
        }).catch(error => {
            console.error(error)
        }).finally(() => {
            setIsLoading(false)
        })
    }, [])
    if (isLoading) {
        return (
            <SidebarWithHeader>
                <Spinner/>
            </SidebarWithHeader>
        )
    }
    if (customers.length <= 0) {
        return (
            <SidebarWithHeader>
                <Text>No Customers available</Text>
            </SidebarWithHeader>
        )
    }
    return (
        <SidebarWithHeader>
            <Wrap justify="center" spacing="42px">
                {customers.map((customer, index) => (
                    <WrapItem key={index}>
                        <CardWithImage
                            {...customer}
                            imageNumber={index}
                        ></CardWithImage>
                    </WrapItem>
                ))}
            </Wrap>
        </SidebarWithHeader>
    );
}

export default App
