import './App.css';
import {RouterProvider} from "react-router-dom";
import {AuthContext} from "./context/AuthContext";
import React, {useEffect, useState} from "react";
import {router} from "./router/router";
import Error from "./pages/error";

export default function App() {
    const [isAuth, setIsAuth] = useState(false)
    const [isLoading, setIsLoading] = useState(true)
    const [authData, setAuthData] = useState({userUuid: "123", name: "Ковалева", surname: "Дарья", patronymic: "Максимовна", role: "Преподаватель"})
    useEffect(() => {
        if(localStorage.getItem('auth')) {
            setIsAuth(true)
        }
        setIsLoading(false);
    }, []);

    if (isLoading) return <></>

    return (
        <AuthContext.Provider value={{
            isAuth,
            setIsAuth,
            isLoading,
            setIsLoading,
            authData,
            setAuthData
        }}>
            <RouterProvider router={router(isAuth,authData,setAuthData)} fallbackElement={<Error />} />
        </AuthContext.Provider>
    );
}