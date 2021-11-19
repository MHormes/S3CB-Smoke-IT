import React, {useState, useEffect} from "react";
import * as urls from "./../../URL";
import axios from "axios";
import SingleSelectedBoxGroupPage from "./SingleSelectedBoxGroupPage"

const SelectedBoxGroupPage = (props) => {

    const jwtToken = props.jwtTokenProps
    const [orders, setOrders] = useState()

    useEffect(() => {
        let mounted = true
        if (mounted) {
            axios.get(urls.baseURL + urls.ordersURL + urls.ordersGrouped+props.boxIdForOrderGroupProps, {
                headers: {
                    'Authorization': jwtToken
                }
            }).then(res => {
                setOrders(res.data);
            }).catch(err => {
                if (err == null) {
                    alert("There seems to be an connection issue on our side. Please call 06xxxxxxxx to fix it")
                }
                else {
                    alert(err.status)
                }
            });
        }

        return () => mounted = false;
    })

    if (!orders) return null

    return(
        <>
        <ul>
                {orders.map(order => (
                    <SingleSelectedBoxGroupPage
                    key={order.id} 
                    order={order}/>
                ))}
            </ul>
        </>
    )
}

export default SelectedBoxGroupPage