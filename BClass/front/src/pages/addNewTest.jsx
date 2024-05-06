import React, {useState} from "react";
import "./addNewTest.css"
import {useNavigate} from "react-router-dom";
import NewQuestionList from "../components/newQuestionList/newQuestionList";

const question = {text: "text"}


const AddNewTest = () => {

    const navigate = useNavigate();

    const [questions, setQuestions] = useState([])

    const removeQuestion = (question, e) => {
        e.stopPropagation();
        setQuestions(questions.filter(q => q.id !== question.id))
    }

    const addQuestion = (e) => {
        e.preventDefault();
        const newQuestion = {
            id: Date.now(),
            title: `Урок ${questions.length + 1}`,
        }
        setQuestions([...questions, newQuestion])
    }


    return (
        <div className="add-new-test-wrapp">
            <div className="add-new-test-form">
                <div className="add-new-test-form-container">
                    <div className="add-new-test-form-container-static-content">
                        <div className="add-new-test-form-header">Добавить новый тест</div>
                        <div className="add-new-test-form-content">
                            <div className="add-new-test-form-content-input-subject-text">
                                <div className="add-new-test-form-content-input-subject-text-title">
                                    <p className="add-new-test-form-content-text-title">Введите тему теста</p>
                                </div>
                                <input autoComplete="on" className="add-new-test-form-content-input-subject-text-input"
                                       placeholder="Тема теста"></input>
                            </div>
                            <div className="add-new-test-form-content-input-deadline">
                                <div className="add-new-test-form-content-input-deadline-title">
                                    <p className="add-new-test-form-content-deadline-title">Дата дедлайна</p>
                                </div>
                                <input type={"datetime-local"} autoComplete="on"
                                       className="add-new-test-form-content-input-deadline-input"
                                       placeholder="Дедлайн"></input>
                            </div>
                        </div>
                    </div>
                    <NewQuestionList remove={removeQuestion} questions={questions}/>
                    <div className="add-new-test-form-container-button">
                        <button className="add-new-question-test-button"
                                onClick={(e) => addQuestion(e)}>Добавить вопрос
                        </button>
                    </div>
                    <div className="add-new-test-button-container">
                        <button className="add-new-test-button" type="submit"
                                onClick={() => navigate(-1)}>Добавить тест
                        </button>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default AddNewTest;