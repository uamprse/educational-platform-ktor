import React, {useState} from 'react';
import SideMenu from "../components/sideMenu/sideMenu";
import NavBar from "../components/navBar/navBar";
import {useLoaderData, useNavigate} from "react-router-dom";
import "./courses.css"
import CardList from "../components/cardList/cardList";
import MyModal from "../components/myModal/myModal";
import CourseForm from "../components/courseForm/courseForm";

const Courses = () => {
    const data = useLoaderData();
    const navigate = useNavigate();
    const [modal, setModal] = useState(false);
    const [courses, setCourses] = useState(data);

    const removeCourse = (course, e) => {
        e.stopPropagation();
        setCourses(courses.filter(c=>c.uuid !== course.uuid))
    }


    const createCourse = (newCourse) => {
        newCourse = {...newCourse, img: "https://i.ibb.co/JkW4tYy/Image-2.jpg"}
        setCourses([...courses, newCourse])
        setModal(false)
    }

    return (
        <div className="page-header">
            <SideMenu/>
            <MyModal visible={modal} setVisible={setModal}>
                <CourseForm create={createCourse}/>
            </MyModal>
            <div className="content">
                <NavBar hasText={false}/>
                <div className="courses-page-wrap">
                    <div className="courses-page-content">
                        <div className="courses-page-content-header">
                            <h1 className="courses-page-title">Курсы</h1>
                            <button className="courses-page-button-add-course" onClick={() => setModal(true)}>Добавить курс</button>
                        </div>
                        <div className="courses-page-content-layout">
                            <CardList cards={courses} remove={removeCourse} title={"На данный момент курсов нет..."}/>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default Courses;