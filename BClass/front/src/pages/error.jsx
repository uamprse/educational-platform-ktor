import React from 'react';
import SideMenu from "../components/sideMenu/sideMenu";
import NavBar from "../components/navBar/navBar";
import "./error.css";

const Error = () => {
    return (
        <div className="page-header">
            <SideMenu/>
            <div className="content">
                <NavBar hasText={false}/>
                <div className="error-page-wrap">
                    <div className="error-page-content">
                        Error 404 <br/>
                        Page Not Found
                    </div>
                </div>
            </div>
        </div>
    );
};

export default Error;