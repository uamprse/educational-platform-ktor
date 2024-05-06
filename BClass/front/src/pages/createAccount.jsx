import React from 'react';
import {Link, useNavigate} from "react-router-dom";
import "./createAccount.css"

const CreateAccount = () => {
    const navigate = useNavigate();

    return (
        <div className="root-img">
            <div className="wrapper_create_account">
                <div className="create_account_form">
                    <h1 className="create_account_form__header">Создать аккаунт</h1>
                    <p className="create_account_form__par">Создайте аккаунт, чтобы продолжить</p>

                    <div className="create_account_form__login">
                        <div className="create_account_form__login__text">
                            <p className="create_account_form__login__par">Логин</p>
                        </div>
                        <input autoComplete="on" type="email" className="create_account_form__login__input"
                               placeholder="example@example.com"></input>
                    </div>
                    <div className="create_account_form__password">
                        <div className="create_account_form__password__text">
                            <p className="create_account_form__password__par">Пароль</p>
                        </div>
                        <input autoComplete="on" type="password" className="create_account_form__password__input"
                               placeholder="veryhardpassword228007"></input>
                    </div>

                    <div className="create_account_form__role_selector">
                        <div className="create_account_form__role_selector__text">
                            <p className="create_account_form__role_selector__par">Роль</p>
                        </div>
                        <div className="role_selector">
                            <input type="radio" name="role" id="student" value="student" checked/>
                            <label htmlFor="student" className="student">Студент</label>
                            <input type="radio" name="role" id="teacher" value="teacher"/>
                            <label htmlFor="teacher" className="teacher">Преподаватель</label>
                        </div>
                    </div>

                    <div className="create_account_form__button">
                        <button className="create_account_form__button__button" type="submit" onClick={() => navigate("/courses/my-lessons")}>Продолжить</button>
                    </div>
                    <div className="create_account_form__create">
                        <p className="create_account_form__create__text">Уже есть аккаунт?</p>
                        <Link className="create_account_form__create__link" to={"/login"}>Войти</Link>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default CreateAccount;