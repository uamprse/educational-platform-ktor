import React from 'react';
import SideMenu from "../components/sideMenu/sideMenu";
import NavBar from "../components/navBar/navBar";
import "./analyz.css"
import {useParams} from "react-router-dom";

const Analyz = () => {
    const params = useParams();
    return (
            <div className="page-header">
                <SideMenu/>
                <div className="content">
                    <NavBar course={params.course}/>
                    <div className="analyz-page-content"></div>
                </div>
            </div>
    );
};

export default Analyz;