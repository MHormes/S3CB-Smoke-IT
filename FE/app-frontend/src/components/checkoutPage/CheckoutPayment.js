import React from "react";

const CheckoutPayment = () => {

    const handlePayment = () =>{
        console.log("paid")
    }

    return (
        <>
            <p>Please pay</p>
            <button onClick={()=> handlePayment}>I have paid</button>
        </>
    )

}

export default CheckoutPayment