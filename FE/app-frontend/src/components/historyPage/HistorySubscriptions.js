import React, { useState, useEffect } from "react"
import axios from "axios"
import * as urls from "./../../URL"
import styles from './HistorySubscriptions.module.css'

const HistorySubscriptions = (props) => {

    const subscription = props.subscriptionProps
    const [subBox, setSubBox] = useState()
    const selectSub = (subscriptionId) => {
        console.log("selected: " + subscriptionId)
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
            <li className={styles.li} onClick={() => selectSub(subscription.id)}>
                <p className={styles.p}>Name: {subscription.name}</p>
                <p className={styles.p}>Box ordered: {subBox.name}</p>
                <p className={styles.p}>Amount of boxes in subscription: {subscription.amount}</p>
            </li>
        </>
    )
}

export default HistorySubscriptions