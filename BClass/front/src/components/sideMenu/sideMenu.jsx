import React from 'react';
import MenuItem from "../menuItem/menuItem";
import "./sideMenu.css"
import {NavLink} from "react-router-dom";

const SideMenu = () => {
    return (
        <div className="side-menu">
            <div className="menu-logo-container">
                <div className={"menu-logo"}></div>
            </div>

            <div className={"menu-list-container"}>
                <NavLink className="menu-item-wrap"
                    to="/courses"
                    style={{textDecoration: "none"}}
                >
                    {({ isActive}) => (
                        <MenuItem isActive={isActive} label={'Курсы'} icon={'image'}/>
                    )}
                </NavLink>

                <NavLink className="menu-item-wrap"
                    to="/account"
                    style={{textDecoration: "none"}}
                >
                    {({ isActive}) => (
                        <MenuItem isActive={isActive} label={'Кабинет'} icon={'cabinet'}/>
                    )}
                </NavLink>
            </div>
        </div>
    );
};

export default SideMenu;