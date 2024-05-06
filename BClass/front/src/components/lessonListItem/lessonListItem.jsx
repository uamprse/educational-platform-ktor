import React from 'react';
import {useNavigate} from "react-router-dom";
import "./lessonListItem.css";

const LessonListItem = (props) => {
    const navigate = useNavigate();

    return (
        <div className="post" onClick={() => navigate(`${props.lesson.id}`)}>
            <div className="post__content">
                <div className="my-lesson-list-item-text">{props.lesson.title}</div>
                <div>
                    {props.lesson.body}
                </div>
            </div>
            <div className="post__btns">
                <button className="my-lesson-remove-button" onClick={(e) => props.remove(props.lesson, e)}></button>
            </div>
        </div>
    );
};

export default LessonListItem;