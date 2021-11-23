import React from "react";

const SelectedBoxGroupSingle = (props) => {

    const order = props.order
    const selectOrderFunction = () => {
        localStorage.setItem("selectedOrder", JSON.stringify(order))
        props.selectOrderProps()
    }

    return (
        <li>
            <div onClick={selectOrderFunction}>
                <p>{order.name} {order.city}</p>
            </div>
        </li>
    )
}

export default SelectedBoxGroupSingle