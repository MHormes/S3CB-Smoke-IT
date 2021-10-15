import React from "react"
import CheckoutSummary from "./CheckoutSummary"
import CheckoutForm from "./CheckoutForm"

const CheckoutPage = () => {

    const checkoutDetails = JSON.parse(localStorage.getItem("checkoutDetails"))
    return (
        <>
            <h1>Checkout</h1>
            <CheckoutSummary
                checkoutDetailsProps={checkoutDetails}
            />
            <CheckoutForm />
        </>
    )
}

export default CheckoutPage