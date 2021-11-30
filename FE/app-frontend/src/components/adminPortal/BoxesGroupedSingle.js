import React from "react";
import styles from "./BoxesGroupedSingle.module.css"

const BoxesGroupedSingle = (props) => {

    const boxGroup = props.box
    const selectBoxGroup = () => {
        props.selectGroupedBoxesProps(boxGroup)
    }

    return (
        <li className={styles.li}>
            <div onClick={() => selectBoxGroup()}>
                <h1 className={styles.h1}>Box: {boxGroup.boxName} / Amount: {boxGroup.amount}</h1>
            </div>
        </li>
    )
}

export default BoxesGroupedSingle