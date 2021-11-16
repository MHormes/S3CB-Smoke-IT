import React, { useState } from "react"
import CheckoutSummary from "./CheckoutSummary"
import CheckoutForm from "./CheckoutForm"
import CheckoutPayment from "./CheckoutPayment"
import axios from "axios"
import * as urls from "./../../URL"
import { useHistory } from "react-router"

const CheckoutPage = () => {

    //get the checkout details from the local storage. In here i saved the box and subscription details.
    const checkoutDetails = JSON.parse(localStorage.getItem("checkoutDetails"))

    const history = useHistory();

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
            axios.post(urls.baseURL+urls.placeOrder, orderObject).then(res=>{
                console.log(res.data)
                if (res.status === 200) {
                    console.log("Order successfull")
                    history.push("/")
                }
            }).catch(err => {
                alert(err.response.data)
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
                setPaymentCheckProps={setPaymentCheckInSate} />
        </>
    )
}

export default CheckoutPage