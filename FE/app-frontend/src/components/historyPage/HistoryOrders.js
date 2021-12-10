import React, { useEffect, useState } from "react"
import axios from "axios";
import * as urls from "./../../URL"
import HistoryOrdersSingle from "./HistoryOrdersSingle"
import styles from "./HistoryOrders.module.css"

const HistoryOrders = (props) => {
    const subscription = JSON.parse(localStorage.getItem("selectedSubHistory"))
    const jwtToken = localStorage.getItem("jwtToken")
    console.log(subscription)
    const [orders, setOrders] = useState();

    useEffect(() => {
        let mounted = true
        if (mounted) {
            axios.get(urls.baseURL + urls.subscriptionURL + urls.ordersURLAll + subscription.id, {
                headers: {
                    "Authorization": jwtToken
                }
            })
                .then(res => {
                    setOrders(res.data)
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

    if (!orders) return null;

    return (
        <>
            <h1>These orders belong to subscription for {subscription.name}</h1>
            <p className={styles.p}>This page shows all previously send orders</p>
            <p className={styles.p}>The last order in the list still needs to be send</p>
            <ul>
                {orders.map(order => (
                    <HistoryOrdersSingle
                        key={order.orderId}
                        order={order}
                        subscription={subscription}
                    />
                ))}
            </ul>
        </>
    )

}

export default HistoryOrders
