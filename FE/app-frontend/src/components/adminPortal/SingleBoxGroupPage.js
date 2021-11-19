import React from "react";

const SingleBoxGroupPage = (props) => {

    const boxGroup = props.box
    const selectBoxGroup = () => {
        props.selectGroupedBoxesProps(boxGroup.boxID)
    }

    return (
        <li>
            <div onClick={() => selectBoxGroup()}>
                <h1>{boxGroup.boxName} {boxGroup.amount}</h1>
            </div>
        </li>
    )
}

export default SingleBoxGroupPage