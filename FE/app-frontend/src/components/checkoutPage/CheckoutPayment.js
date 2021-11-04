import React from "react";

const CheckoutPayment = (props) => {

    const handlePayment = () =>{
        console.log("paid")
        props.setPaymentCheckProps(true);
    }

    return (
        <>
            <p>Please pay</p>
            <button onClick={handlePayment}>I have paid</button>
        </>
    )

}

export default CheckoutPayment