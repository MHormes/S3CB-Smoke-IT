import React from "react"

const CheckoutSummary = () => {

    const checkoutDetails = JSON.parse(localStorage.getItem("checkoutDetails"))

    return (
        <>
            <p>Your order summarized:</p>
            <p>Box selected: {checkoutDetails.boxName}</p>
            <p>Content like: {checkoutDetails.boxContent}</p>
            <p>The duration of the subscription: {checkoutDetails.duration}</p>
            <p>The amount of boxes: {checkoutDetails.amount}</p>
            <p>The price per box: {checkoutDetails.price}</p>
        </>
    )
}

export default CheckoutSummary