import React from "react";
import styles from "./BoxSingle.module.css";

const BoxSingle = (props) => {

    const selectBox = (name) => {
        console.log(name)
    }

    return (
        <li className={styles.box}>
            <div onClick={() => selectBox(props.box.name)}>
                {props.box.name} {props.box.basePrice}
            </div>

        </li>
    )
}

export default BoxSingle