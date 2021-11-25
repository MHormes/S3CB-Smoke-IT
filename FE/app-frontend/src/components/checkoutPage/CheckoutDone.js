import React from "react";

const CheckoutDone = (props) => {

    const subscriptionObject = props.subscriptionObjectProps

    return (
        <>
            <p>Thank you {subscriptionObject.name} for placing your order</p>
            <p>You will receive {subscriptionObject.amountBought} boxes, coming every {subscriptionObject.frequency} month(s)</p>
            <p>An email with your order details will be send to {subscriptionObject.email}</p>
            <p>This email will contain the delivery date of the boxes</p>
            <p>The boxes will be delivered to {subscriptionObject.address} {subscriptionObject.city} ({subscriptionObject.postal})</p>
            <p>If you do not receive an email, or is there something wrong? Please contact us via</p>
            <p>Email: smoke_it@gmail.com or Phone: 06xxxxxxxx</p>
        </>
    )
}

export default CheckoutDone