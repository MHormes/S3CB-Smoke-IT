import React from "react";

const BoxesGroupedSingle = (props) => {

    const boxGroup = props.box
    const selectBoxGroup = () => {
        props.selectGroupedBoxesProps(boxGroup)
    }

    return (
        <li>
            <div onClick={() => selectBoxGroup()}>
                <h1>{boxGroup.boxName} {boxGroup.amount}</h1>
            </div>
        </li>
    )
}

export default BoxesGroupedSingle