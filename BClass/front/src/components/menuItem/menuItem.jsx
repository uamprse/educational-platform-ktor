import React from 'react';
import "./menuItem.css"
import {useNavigate} from "react-router-dom";

const MenuItem = ({isActive, label, key, icon}) => {
    const navigate = useNavigate();

    return (
        isActive ?
                (<>
                    <div className="menu-item-left"></div>
                    <div className="menu-item">
                        <div className="menu-item-icon" style={{content: `var(--${icon})`} }></div>
                        <div className="menu-item-text" onClick={() => navigate("/my-lessons")}>{label}</div>
                    </div>
                </>)
                :
                (<>
                    <div className="menu-item-left" style={{background:"white"}}></div>
                    <div className="menu-item" style={{background:"white", color:"black"}}>
                        <div className="menu-item-icon" style={{content: `var(--${icon}-black)`}}></div>
                        <div className="menu-item-text">{label}</div>
                    </div>
                </>)
    );
};

export default MenuItem;