import React from 'react';
import CardItem from "../cardItem/cardItem";
import "./cardList.css";

const CardList = ({cards, remove, title}) => {

    if (!cards.length) {
        return (
            <div className="courses-page-content-layout-header-container">
                <h1 className="courses-page-content-layout-header" style={{textAlign: "center"}}>
                    {title}
                </h1>
            </div>
        )
    }

    return (

        <div className="card-list">
            {cards.map((card, index) =>
                <CardItem remove={remove} card={card} key={index}/>
            )}
        </div>
    );
};

export default CardList;