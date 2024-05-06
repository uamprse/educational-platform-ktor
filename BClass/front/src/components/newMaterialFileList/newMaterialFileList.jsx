import React from 'react';
import LessonListItem from "../lessonListItem/lessonListItem";
import "./newMaterialFileList.css"
import NewMaterialFileItem from "../newMaterialFile/newMaterialFileItem";

const NewMaterialFileList = ({materials, remove}) => {

    if (!materials.length) {
        return (
            <div className="lesson-page-content-form-materials-files-form">
                <div className="lessons-header-container">
                    <h1 className="lessons-list-header" style={{textAlign: "center"}}>
                        Файлы отстутствуют...
                    </h1>
                </div>
            </div>
        )
    }

    return (
        <div className="lesson-page-content-form-materials-files-form">
            {materials.map((material, index) =>
                <NewMaterialFileItem remove={remove} number={index + 1}  material={material} key={material.id}/>
            )}
        </div>
    );
};

export default NewMaterialFileList;