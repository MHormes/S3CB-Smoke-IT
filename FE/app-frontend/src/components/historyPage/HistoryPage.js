import jwtDecode from "jwt-decode"
import { useState, useEffect } from "react"
import axios from "axios"
import * as urls from "./../../URL"
import styles from "./HistoryPage.module.css"
import HistorySubscriptions from "./HistorySubscriptions"

const HistoryPage = (props) => {

    const jwtToken = localStorage.getItem("jwtToken")
    const decodedJwt = jwtDecode(jwtToken)

    const [subscriptions, setSubscriptions] = useState()

    useEffect(() => {
        let mounted = true
        if (mounted) {
            axios.get(urls.baseURL + urls.subscriptionURL + decodedJwt.userId, {
                headers: {
                    'Authorization': jwtToken
                }
            }).then(res => {
                setSubscriptions(res.data);
            }).catch(err => {
                if (!err) {
                    alert("There seems to be an connection issue on our side. Please call 06xxxxxxxx to fix it")
                }
                else {
                    alert("Contact your system administrator for error: " + err)
                }
            });
        }

        return () => mounted = false;
    }, [])

    if (!subscriptions) return <h1 className={styles.h1}>Any subscriptions you have orderd on this account will appear here</h1>

    return (
        //Return needs update after userid is included in JWT token
        <>
            <h1 className={styles.h1}>Any subscriptions you have orderd on this account will appear here</h1>
            <h1 className={styles.h2}>Click on an subscription to see its details</h1>
            <ul className={styles.ul}>
                {subscriptions.map(sub => (
                    <HistorySubscriptions
                        key={sub.id}
                        subscriptionProps={sub}
                        jwtTokenProps={jwtToken}
                        selectSubscriptionHistoryProps={props.selectSubscriptionHistoryProps} />
                ))}
            </ul>
        </>
    )

}

export default HistoryPage