import React from "react";

const SingleSelectedBoxGroupPage = (props) => {

    const order = props.order
    
    return (
        <li>    
            <div>
                <p>{order.name} {order.city}</p>
            </div>
        </li>
    )
}

export default SingleSelectedBoxGroupPage