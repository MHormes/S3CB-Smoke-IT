import React, { useState } from "react"
import CheckoutSummary from "./CheckoutSummary"
import CheckoutForm from "./CheckoutForm"
import CheckoutPayment from "./CheckoutPayment"
import axios from "axios"
import * as urls from "./../../URL"

const CheckoutPage = (props) => {

    //get the checkout details from the local storage. In here i saved the box and subscription details.
    const checkoutDetails = JSON.parse(localStorage.getItem("checkoutDetails"))

    const [subscriptionObject, setSubscriptionObject] = useState()

    const [paymentCheck, setPaymentCheck] = useState()

    const assignSubscriptionObject = (orderDetails) => {
        setSubscriptionObject({
            boxId: checkoutDetails.boxId,
            userId: 0,
            amountBought: checkoutDetails.amount,
            frequency: checkoutDetails.frequency,
            name: orderDetails.name,
            email: orderDetails.email,
            address: orderDetails.address,
            postal: orderDetails.postal,
            city: orderDetails.city
        })
        addSubscriptionInBE(subscriptionObject)
    }

    const addSubscriptionInBE = (subscriptionObject) => {
        if (paymentCheck) {
            axios.post(urls.baseURL + urls.subscriptionURL + urls.createSub, subscriptionObject).then(res => {
                console.log(res.data)
                if (res.status === 200) {
                    props.finishCheckoutProps(subscriptionObject)
                }
            }).catch(err => {
                if (!err) {
                    alert("There seems to be an connection issue on our side. Please call 06xxxxxxxx to fix it")
                }
                else {
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
                assignSubscriptionObjectProps={assignSubscriptionObject} />
            <CheckoutPayment
                setPaymentCheckProps={setPaymentCheckInSate}
                pricePerBoxProps={checkoutDetails.basePrice}
                amountOfBoxesProps={checkoutDetails.amount} />
        </>
    )
}

export default CheckoutPage