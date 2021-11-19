import React, { useState } from "react"
import CheckoutSummary from "./CheckoutSummary"
import CheckoutForm from "./CheckoutForm"
import CheckoutPayment from "./CheckoutPayment"
import axios from "axios"
import * as urls from "./../../URL"

const CheckoutPage = (props) => {

    //get the checkout details from the local storage. In here i saved the box and subscription details.
    const checkoutDetails = JSON.parse(localStorage.getItem("checkoutDetails"))


    const [orderObject, setOrderObject] = useState()

    const [paymentCheck, setPaymentCheck] = useState()

    const assignOrderObject = (orderDetails) => {
        setOrderObject({
            boxId: checkoutDetails.boxId,
            userId: 0,
            amount: checkoutDetails.amount,
            frequency: checkoutDetails.frequency,
            name: orderDetails.name,
            email: orderDetails.email,
            address: orderDetails.address,
            postal: orderDetails.postal,
            city: orderDetails.city
        })
        addOrderInBE(orderObject)
    }

    const addOrderInBE = (orderObject) => {
        if (paymentCheck) {
            axios.post(urls.baseURL+urls.ordersURL+urls.placeOrder, orderObject).then(res=>{
                console.log(res.data)
                if (res.status === 200) {
                    props.finishCheckoutProps(orderObject)
                }
            }).catch(err => {
                if(err == null){
                    alert("There seems to be an connection issue on our side. Please call 06xxxxxxxx to fix it")
                }
                else{
                    alert(err)
                }
            })
        } else {
            alert("Please pay before finishing checkout")
        }
    }

    const setPaymentCheckInSate = (value) => {
        setPaymentCheck(value);
    }

    return (
        <>
            <h1>Checkout</h1>
            <CheckoutSummary
                checkoutDetailsProps={checkoutDetails} />
            <CheckoutForm
                assignOrderObjectProps={assignOrderObject} />
            <CheckoutPayment
                setPaymentCheckProps={setPaymentCheckInSate}
                pricePerBoxProps={checkoutDetails.basePrice} 
                amountOfBoxesProps={checkoutDetails.amount}/>
        </>
    )
}

export default CheckoutPage