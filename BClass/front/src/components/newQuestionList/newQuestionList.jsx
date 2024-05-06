import React, {useEffect, useRef} from 'react';
import NewQuestionItem from "../newQuestionItem/newQuestionItem";
import "./newQuestionList.css"
const NewQuestionList = ({questions, remove}) => {
    const ref = useRef(null)


    useEffect(() => {
        const lastChildElement = ref.current?.lastElementChild;
        lastChildElement?.scrollIntoView({ behavior: 'smooth' });
    }, [questions]);

    return (
        <div className="add-new-test-form-container-list"
             ref={ref}>
            {questions.map((question, index) =>
                <NewQuestionItem remove={remove} number={index + 1} question={question} key={question.id}/>
            )}
        </div>
    );
};

export default NewQuestionList;