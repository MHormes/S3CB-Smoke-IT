import React, { useState, useEffect } from "react"
import axios from "axios"
import * as urls from "./../../URL"
import styles from './HistorySubscriptions.module.css'

const HistorySubscriptions = (props) => {

    const subscription = props.subscriptionProps
    const [subBox, setSubBox] = useState()
    const selectSub = (subscription) => {
        localStorage.setItem("selectedSubHistory", JSON.stringify(subscription))
        props.selectSubscriptionHistoryProps()
    }

    useEffect(() => {
        let mounted = true
        if (mounted) {
            axios.get(urls.baseURL + urls.boxesURL + subscription.boxId)
                .then(res => {
                    setSubBox(res.data)
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

    if (!subBox) return null;

    return (
        <>
            <li className={styles.li} onClick={() => selectSub(subscription)}>
                <p className={styles.p}>Name: {subscription.name}</p>
                <p className={styles.p}>Box ordered: {subBox.name}</p>
                <p className={styles.p}>Amount of boxes bought: {subscription.amountBought}</p>
                <p className={styles.p}>Amount of boxes to be delivered: {subscription.amountLeft +1}</p>
                <p className={styles.p}>the total cost of this order was: {subscription.totalCost}</p>
            </li>
        </>
    )
}

export default HistorySubscriptions