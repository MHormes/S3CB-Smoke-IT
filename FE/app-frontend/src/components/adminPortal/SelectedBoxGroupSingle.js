import React from "react";
import styles from "./SelectedBoxGroupSingle.module.css"

const SelectedBoxGroupSingle = (props) => {

    const order = props.order
    const selectOrderFunction = () => {
        localStorage.setItem("selectedOrder", JSON.stringify(order))
        props.selectOrderProps()
    }

    return (
        <li className={styles.li}>
            <div onClick={selectOrderFunction}>
                <h1 className={styles.h1}>Customer name: {order.name} / To be delivered on: {order.deliveryDate}</h1>
            </div>
        </li>
    )
}

export default SelectedBoxGroupSingle