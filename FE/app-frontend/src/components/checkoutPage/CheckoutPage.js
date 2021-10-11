import React from "react"
import CheckoutSummary from "./CheckoutSummary"

const CheckoutPage = (props) => {

    var checkoutDetails = props.checkoutDetailsProps
    console.log(checkoutDetails)
    return (
        <>
            <h1>Checkout</h1>
            <CheckoutSummary
                checkoutDetailsProps={checkoutDetails}
            />
        </>
    )
}

export default CheckoutPage