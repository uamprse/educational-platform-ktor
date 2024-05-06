import React from 'react';
import {useNavigate} from "react-router-dom";
import "./cardItem.css"

const CardItem = (props) => {
    const navigate = useNavigate();
    return (
        <div className="card-item">
            <div className="card-item-img-container">
                <div className="card-item-bin" onClick={(e) => props.remove(props.card, e)}></div>
                <img src={props.card.img} className="card-item-image" alt={props.card.name}/>
            </div>
            <div className="card-item-body">
                <div className="card-item-body-header">
                    {props.card.title}
                </div>
                <button className="card-item-body-button" onClick={() => navigate(props.card.uuid+'/lessons')}>
                    Перейти
                </button>
            </div>
        </div>
    );
};

export default CardItem;