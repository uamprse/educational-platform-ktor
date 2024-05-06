import React from 'react';
import "./navBar.css"
import {NavLink} from "react-router-dom";

const NavBar = ({hasText= true, course}) => {
    return (
        <div className="nav-bar">
            <nav>
                {hasText === true ?
                (<><NavLink
                    to={"/courses/" + course + "/lessons"}
                    className={({isActive}) => (isActive ? "link-active" : "link")}
                >
                    Уроки
                </NavLink>
                    <NavLink
                        end
                    to={"/courses/" + course + "/analytics"}
                    className={({isActive}) => (isActive ? "link-active" : "link")}
                 >
                Аналитика
                </NavLink></>)
                    :
                    null
            }
            </nav>
            <div className="nav-bar-image"></div>
        </div>
    );
};

export default NavBar;