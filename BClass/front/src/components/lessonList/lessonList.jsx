import React from 'react';
import LessonListItem from '../lessonListItem/lessonListItem';
import './lessonList.css';

const LessonList = ({lessons, remove, title}) => {

    if (!lessons.length) {
        return (
            <div className="my-lessons-header-container">
                <h1 className="my-lessons-list-header" style={{textAlign: "center"}}>
                    {title}
                </h1>
            </div>
        )
    }

    return (
        <div className="my-lesson-list-container">
                {lessons.map((lesson, index) =>
                    <LessonListItem remove={remove} number={index + 1} lesson={lesson} key={lesson.id}/>
                )}
        </div>
    );
};

export default LessonList;