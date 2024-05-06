import React, {useState} from 'react';
import MyInput from "../UI/input/MyInput";

const LessonForm = ({create}) => {
    const [lesson, setlesson] = useState({title: ''})

    const addNewlesson = (e) => {
        e.preventDefault()
        const newlesson = {
            ...lesson, id: Date.now()
        }
        create(newlesson)
        setlesson({title: ''})
    }

    return (
        <form>
            <MyInput
                value={lesson.title}
                onChange={e => setlesson({...lesson, title: e.target.value})}
                type="text"
                placeholder="Название Урока"
            />
            <button onClick={addNewlesson}>Создать Урок</button>
        </form>
    );
};

export default LessonForm;