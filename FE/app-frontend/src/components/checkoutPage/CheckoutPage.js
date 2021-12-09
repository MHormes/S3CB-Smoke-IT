import React, { useState } from "react"
import CheckoutSummary from "./CheckoutSummary"
import CheckoutForm from "./CheckoutForm"
import CheckoutPayment from "./CheckoutPayment"
import axios from "axios"
import * as urls from "./../../URL"
import jwtDecode from "jwt-decode"

const CheckoutPage = (props) => {

    //get the checkout details from the local storage. In here i saved the box and subscription details.
    const checkoutDetails = JSON.parse(localStorage.getItem("checkoutDetails"))
    console.log(checkoutDetails)
    const [paymentCheck, setPaymentCheck] = useState()
    const [totalPrice, setTotalPrice] = useState()

    //User id when not logged
    let userId = 0
    if (localStorage.getItem("jwtToken")) {
        const jwtToken = localStorage.getItem("jwtToken")
        const decodedJwt = jwtDecode(jwtToken)
        userId = decodedJwt.userId
    }

    const assignSubscriptionObject = (orderDetails) => {
        const subscriptionObject = {
            boxId: checkoutDetails.boxId,
            userId: userId,
            amountBought: checkoutDetails.amount,
            frequency: checkoutDetails.frequency,
            totalPrice: totalPrice,
            name: orderDetails.name,
            email: orderDetails.email,
            address: orderDetails.address,
            postal: orderDetails.postal,
            city: orderDetails.city
        }
        addSubscriptionInBE(subscriptionObject)
    }

    const addSubscriptionInBE = (subscriptionObject) => {
        if (paymentCheck) {
            axios.post(urls.baseURL + urls.subscriptionURL + urls.createSub, subscriptionObject)
                .then(res => {
                    console.log(res.data)
                    if (res.status === 200) {
                        props.finishCheckoutProps(res.data)
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

    const setPaymentCheckInSate = (bool, price) => {
        setPaymentCheck(bool)
        setTotalPrice(price)
    }

    return (
        <>
            <h1>Checkout</h1>
            <CheckoutSummary
                checkoutDetailsProps={checkoutDetails} />
            <CheckoutForm
                assignSubscriptionObjectProps={assignSubscriptionObject}
            />
            <CheckoutPayment
                setPaymentCheckProps={setPaymentCheckInSate}
                pricePerBoxProps={checkoutDetails.price}
                amountOfBoxesProps={checkoutDetails.amount} />
        </>
    )
}

export default CheckoutPage