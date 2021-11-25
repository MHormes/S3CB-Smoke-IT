import React, { useEffect } from "react";
import axios from "axios";
import * as urls from "./../../URL"
import { useState } from "react/cjs/react.development";
import SelectedOrderShipping from "./SelectedOrderShipment";
import SelectedOrderBox from "./SelectedOrderBox";
import Cookies from "universal-cookie";

const SelectedOrderPage = () => {

    const cookies = new Cookies()
    const [selectedOrder, setSelectedOrder] = useState(JSON.parse(localStorage.getItem("selectedOrder")))
    const jwtToken = cookies.get("jwtToken")
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
                        alert("Contact your system administrator with: " + err)

                    }
                });
        }

        return () => mounted = false;
    }, [])

    const toggleOrderPacked = (orderId) => {
        axios.put(urls.baseURL + urls.subscriptionURL + urls.ordersURL + urls.ordersPack + orderId, null, {
            headers: {
                'Authorization': jwtToken
            }
        }
        ).then(res => {
            localStorage.setItem("selectedOrder", JSON.stringify(res.data))
            setSelectedOrder(res.data)
        }).catch(err => {
            if (!err) {
                alert("There seems to be an connection issue on our side. Please call 06xxxxxxxx to fix it")
            }
            else {
                alert("Contact your system administrator for error: " + err)
            }
        })
    }

    const setOrderAsSend = (orderId) => {
        if(selectedOrder.packed){
            axios.put(urls.baseURL + urls.subscriptionURL + urls.ordersURL + urls.ordersSend + orderId, null, {
                headers: {
                    'Authorization': jwtToken
                }
            }
            ).then(res => {
                localStorage.setItem("selectedOrder", JSON.stringify(res.data))
                setSelectedOrder(res.data)
            }).catch(err => {
                if (!err) {
                    alert("There seems to be an connection issue on our side. Please call 06xxxxxxxx to fix it")
                }
                else {
                    alert("Contact your system administrator for error: " + err)
                }
            })
        }else{
            alert("The order has to be packed before it can be send")
        }
        
    }


    const packButtonNeeded = () => {
        return (
            <button onClick={() => toggleOrderPacked(selectedOrder.orderId)}>
                Set box as packed
            </button>
        )
    }
    const packButtonPacked = () => {
        return (
            <button onClick={() => toggleOrderPacked(selectedOrder.orderId)}>
                Remove box from packed list
            </button>
        )
    }
    let packButton = null;
    if (selectedOrder.packed !== true) {
        packButton = packButtonNeeded();
    } else {
        packButton = packButtonPacked();
    }

    if (!orderedBox || !selectedOrder || !orderedBoxId) return null
    return (
        <>
            <p>Order id:{selectedOrder.orderId}. For subscription with id: {selectedOrder.subscriptionId}</p>
            <p>Box must be send every: {selectedOrder.frequency} month(s)</p>
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