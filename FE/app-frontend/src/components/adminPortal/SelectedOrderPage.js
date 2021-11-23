import React, { useEffect } from "react";
import axios from "axios";
import * as urls from "./../../URL"
import { useState } from "react/cjs/react.development";
import SelectedOrderShipping from "./SelectedOrderShipment";
import SelectedOrderBox from "./SelectedOrderBox";

const SelectedOrderPage = (props) => {

    const selectedOrder = JSON.parse(localStorage.getItem("selectedOrder"))
    const orderedBoxId = selectedOrder.boxId
    const [orderedBox, setOrderedBox] = useState()

    useEffect(() => {
        let mounted = true
        if (mounted && orderedBoxId) {
            axios.get(urls.baseURL + urls.boxesURL + orderedBoxId)
                .then(res => {
                    setOrderedBox(res.data)
                }).catch(err => {
                    if (!err) {
                        alert("There seems to be an connection issue on our side. Please call 06xxxxxxxx to fix it")
                    }
                    else {
                        alert(err)
                    }
                });
        }

        return () => mounted = false;
    }, [])

    const setOrderAsPacked = (orderId) => {
        console.log("I have been packed")
    }

    const unsetOrderAsPacked = (orderId) => {
        console.log("I have been unpacked")
    }

    const setOrderAsSend = (orderId) => {
        console.log("I have been packed")
    }


    const packButtonNeeded = () => {
        return (
            <button onClick={() => setOrderAsPacked(selectedOrder.orderId)}>
                Set box as packed
            </button>
        )
    }
    const packButtonPacked = () => {
        return (
            <button onClick={() => unsetOrderAsPacked(selectedOrder.orderId)}>
                Remove box from packed list
            </button>
        )
    }
    let packButton = null;
    if (selectedOrder.packed != true) {
        packButton = packButtonNeeded();
    } else {
        packButton = packButtonPacked();
    }

    if (!orderedBox || !selectedOrder || !orderedBoxId) return null
    return (
        <>
            <p>Order id:{selectedOrder.orderId}. For subscription with id: {selectedOrder.subscriptionId}</p>
            <p>Box must be send every: {selectedOrder.frequency} months</p>
            <p>Including this box there are: {selectedOrder.amountLeft + 1} boxes left in this subscription</p>
            <SelectedOrderBox
                orderedBoxProps={orderedBox}
            />
            <SelectedOrderShipping
                shippingDetailsProps={selectedOrder}
            />
            {packButton} <button onClick={() => setOrderAsSend(selectedOrder.orderId)}>Send box</button>
        </>
    )
}

export default SelectedOrderPage