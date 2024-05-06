import React from 'react';
import "./newMaterialFileItem.css"

const NewMaterialFileItem = (props) => {

    const handleDownload = (fileUrl) => {
        fetch(fileUrl, {mode: 'no-cors'})
            .then((response) => {
                const url = window.URL.createObjectURL(new Blob([response.data]));
                const link = document.createElement('a')
                link.href = url;
                link.setAttribute('download', 'example.pdf');
                document.body.appendChild(link);
                link.click();
                document.body.removeChild(link);
            })
    }


    return (

        <div className="lesson-page-content-form-materials-files-item">
            <div className="lesson-page-content-form-materials-files-item-text-icon"
                 onClick={() => handleDownload(props.material.url)}
            >
                <div
                    className="lesson-page-content-form-materials-files-item-icon"></div>
                <div
                    className="lesson-page-content-form-materials-files-item-text">{props.material.title}
                </div>
            </div>
            <button
                className="lesson-page-content-form-materials-files-item-button"
                onClick={(e) => props.remove(props.material, e)}></button>
        </div>
    );
};

export default NewMaterialFileItem;