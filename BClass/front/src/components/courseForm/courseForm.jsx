import React, {useState} from 'react';
import MyInput from "../UI/input/MyInput";

const CourseForm = ({create}) => {
    const [course, setCourse] = useState({title: '', desc: ''})

    const addNewCourse = (e) => {
        e.preventDefault()
        const newcourse = {
            ...course, id: Date.now()
        }
        create(newcourse)
        setCourse({name: '', desc: ''})
    }

    return (
        <form>
            <MyInput
                value={course.name}
                onChange={e => setCourse({...course, name: e.target.value})}
                type="text"
                placeholder="Название Курса"
            />

            <MyInput
                value={course.desc}
                onChange={e => setCourse({...course, desc: e.target.value})}
                type="text"
                placeholder="Описание Курса"
            />
            <button onClick={addNewCourse}>Создать Пост</button>
        </form>
    );
};

export default CourseForm;