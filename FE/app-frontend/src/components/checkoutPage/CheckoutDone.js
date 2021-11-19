import React from "react";

const CheckoutDone = (props) => {

    const orderObject = props.orderObjectProps

    return (
        <>
            <p>Thank you {orderObject.name} for placing your order</p>
            <p>You will receive {orderObject.amount} boxes, comming every {orderObject.frequency} month(s)</p>
            <p>An email with your order details will be send to {orderObject.email}</p>
            <p>This email will contain the delivery date of the boxes</p>
            <p>The boxes will be delivered to {orderObject.address} {orderObject.city} ({orderObject.postal})</p>
            <p>If you do not receive an email, or is there something wrong? Please contact us via</p>
            <p>Email: smoke_it@gmail.com or Phone: 06xxxxxxxx</p>
        </>
    )
}

export default CheckoutDone