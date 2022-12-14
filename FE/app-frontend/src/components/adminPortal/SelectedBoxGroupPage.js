import React, { useState, useEffect } from "react";
import * as urls from "./../../URL";
import axios from "axios";
import styles from "./SelectedBoxGroupPage.module.css"
import SelectedBoxGroupSingle from "./SelectedBoxGroupSingle"

const SelectedBoxGroupPage = (props) => {

    const jwtToken = props.jwtTokenProps
    const selectedBoxGroupName = props.selectedBoxGroupNameProps
    const [orders, setOrders] = useState()

    useEffect(() => {
        let mounted = true
        if (mounted) {
            axios.get(urls.baseURL + urls.subscriptionURL + urls.ordersGrouped + props.boxIdForOrderGroupProps, {
                headers: {
                    'Authorization': jwtToken
                }
            }).then(res => {
                setOrders(res.data);
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
    
    if (!orders) return null

    return (
        <>
        <h1 className={styles.h1}>All orders for box {selectedBoxGroupName}</h1>
            <ul className={styles.order}>
                {orders.map(order => (
                    <SelectedBoxGroupSingle
                        key={order.orderId}
                        order={order}
                        selectOrderProps={props.selectOrderProps} />
                ))}
            </ul>
        </>
    )
}

export default SelectedBoxGroupPage