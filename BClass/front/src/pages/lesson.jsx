import React, {useState} from 'react';
import {useNavigate, useParams} from "react-router-dom";
import "./lesson.css";
import SideMenu from "../components/sideMenu/sideMenu";
import NavBar from "../components/navBar/navBar";
import LessonList from "../components/lessonList/lessonList";
import NewMaterialFileList from "../components/newMaterialFileList/newMaterialFileList";

const LessonPage = () => {
    const params = useParams();

    const navigate = useNavigate();

    const [materials, setMaterials] = useState([]);

    const removeMaterial = (material, e) => {
        e.stopPropagation();
        setMaterials(materials.filter(l=>l.id !== material.id))
    }

    const addMaterial = (e) => {
        e.preventDefault();
        const newMaterial = {
            id: Date.now(),
            title: `Лекция ${materials.length + 1}.pdf`,
            url: "https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf"
        }
        setMaterials([...materials, newMaterial])
    }


    const [lessons, setLessons] = useState([]);

    const removeLesson = (lesson, e) => {
        e.stopPropagation();
        setLessons(lessons.filter(l=>l.id !== lesson.id))
    }

    const addLesson = (e) => {
        e.preventDefault();
        const newLesson = {
            id: Date.now(),
            title: `Тест ${lessons.length + 1}`
        }
        setLessons([...lessons, newLesson])
    }




    return (
            <div className="page-header">
                <SideMenu/>
                <div className="content">
                    <NavBar course={params.course}/>
                    <div className="lesson-page-content">

                        <div className="lessons-page-content-container">
                            <div className="lesson-page-content-header">Урок 1</div>
                            <div className="lesson-page-content-form">
                                <div className="lesson-page-content-form-container">
                                    <div className="lesson-page-content-form-row">
                                        <div className="lesson-page-content-form-description">
                                            <div className="lesson-page-content-form-description-header">Описание</div>
                                            <textarea className="lesson-page-content-form-description-input"></textarea>
                                            <div className="lesson-page-content-form-description-button-container">
                                                <button
                                                    className="lesson-page-content-form-description-button">Редактировать
                                                </button>
                                            </div>

                                        </div>

                                        <div className="lesson-page-content-form-materials">
                                            <div className="lesson-page-content-form-materials-header">Материалы</div>

                                            <NewMaterialFileList remove={removeMaterial} title={"..."}  materials={materials}/>
                                            <div className="lesson-page-content-form-materials-button-container">
                                                <button className="lesson-page-content-form-materials-button"
                                                        type="submit"
                                                        onClick={(e) => addMaterial(e)}>Добавить
                                                </button>
                                            </div>

                                        </div>
                                    </div>

                                    <div className="lesson-page-content-form-tests">
                                    <div className="lesson-page-content-form-tests-header">Тесты</div>
                                        <div className="lesson-page-content-form-tests-files-list">
                                            <LessonList remove={removeLesson} title={"На данный момент тестов нет..."} lessons={lessons}/>
                                        </div>
                                        <div className="lesson-page-content-form-test-button-container">
                                            <button className="lesson-page-content-form-test-button" onClick={addLesson}>Добавить тест
                                            </button>

                                            <button className="lesson-page-content-form-test-button"
                                                    onClick={() => navigate("add-new-test")}>Добавить тест
                                            </button>
                                        </div>
                                    </div>
                                    <div className="lesson-page-content-form-button-container">
                                    <button className="lesson-page-content-button"
                                                onClick={() => navigate("/courses")}>Сохранить
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
    );
};

export default LessonPage;