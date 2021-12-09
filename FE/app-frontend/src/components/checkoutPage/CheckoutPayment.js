import React from "react";

const CheckoutPayment = (props) => {

    const price = (props.pricePerBoxProps * props.amountOfBoxesProps)
    const handlePayment = () => {
        console.log("paid")
        props.setPaymentCheckProps(true, price);
    }

    return (
        <>
            <p>Please pay {price} euros</p>
            <button onClick={handlePayment}>I have paid</button>
        </>
    )

}

export default CheckoutPayment