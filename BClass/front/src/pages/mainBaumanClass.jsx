import "./mainBaumanClass.css"
import React from "react";
import {useNavigate} from "react-router-dom";

const MainBaumanClass = () => {
    const navigate = useNavigate();

    return (
        <div className="main-bauman-class-wrapper">
            <div className="main-menu-logo-container">
                <div className={"menu-logo"}></div>
            </div>
            <div className="main-bauman-class-content">
                <div className="main-bauman-class-content-header">Платформа для преподавателей и студентов</div>
                <div className="main-bauman-class-content-text">Наша платформа предназначена для удобства преподавателей
                    и студентов, оптимизируя процесс изучения материала, предоставления заданий и проверки их
                    выполнения.
                </div>
                <div className="main-bauman-class-content-buttons-row">
                    <button className="main-page-content-button-login" type="submit" onClick={() => navigate("/login")}>Войти</button>
                    <button className="main-page-content-button-create-account" type="submit" onClick={() => navigate("/create-account")}>Зарегистироваться</button>
                </div>
            </div>
        </div>
    );
};

export default MainBaumanClass;