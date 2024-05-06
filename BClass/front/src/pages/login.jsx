import React, {useContext} from 'react';
import {Link, useNavigate} from "react-router-dom";
import "./login.css";
import {AuthContext} from "../context/AuthContext";

const Login = () => {
    const navigate = useNavigate();
    const {setIsAuth} = useContext(AuthContext);
    return (
        <div className="root-img">
            <div className="wrapper">
                <div className="log_in_form">
                    <div className="log_in_form__text">
                        <h1 className="log_in_form__header">Войти в аккаунт</h1>
                        <p className="log_in_form__par">Пожалуйста введите свои данные, чтобы продолжить</p>
                    </div>
                    <div className="log_in_form__login">
                        <p className="log_in_form__login__par">Логин</p>
                        <input autoComplete="on" type="email" className="log_in_form__login__input"
                               placeholder="example@example.com"></input>
                    </div>
                    <div className="log_in_form__password">
                        <div className="log_in_form__password__text ">
                            <p className="log_in_form__password__par">Пароль</p>
                        </div>
                        <input autoComplete="on" type="password" className="log_in_form__password__input"
                               placeholder="veryhardpassword228007"></input>
                    </div>
                    <div className="log_in_form__button">
                        <button className="log_in_form__button__button" type="submit"
                                onClick={(e) => {
                                    e.preventDefault();
                                    setIsAuth(true);
                                    localStorage.setItem('auth', 'true');
                                    navigate('/courses', {replace: true});
                                }}>Войти
                        </button>
                    </div>
                    <div className="log_in_form__create">
                        <p className="log_in_form__create__text">Нет аккаунта?</p>
                        <Link className="log_in_form__create__link" to={"/create-account"}>Создать аккаунт</Link>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default Login;