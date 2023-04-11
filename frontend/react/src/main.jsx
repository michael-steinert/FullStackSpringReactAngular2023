import React from "react"
import ReactDOM from "react-dom/client"
import App from "./App"
import "./index.css"
import {ChakraProvider, createStandaloneToast} from "@chakra-ui/react"

const {ToastContainer} = createStandaloneToast()

const root = ReactDOM.createRoot(document.getElementById("root"))

root.render(
    <React.StrictMode>
        <ChakraProvider>
            <App/>
            <ToastContainer/>
        </ChakraProvider>
    </React.StrictMode>
)
