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

    if (!orderedBox || !selectedOrder || !orderedBoxId) return null
    return (
        <>
        <p>Order: {selectedOrder.id}</p>
        <p>Box must be send every: {selectedOrder.frequency}</p>
        <p>Including this box there are: {selectedOrder.amount} boxes left in this subscription</p>
            <SelectedOrderBox
                jwtTokenProps={props.jwtTokenProps}
                orderedBoxProps={orderedBox}
            />
            <SelectedOrderShipping
                jwtTokenProps={props.jwtTokenProps}
                shippingDetailsProps={selectedOrder}
            />
        </>
    )

}

export default SelectedOrderPage