import React, { useState, useEffect } from "react"
import axios from "axios"
import * as urls from "./../../URL"
import Cookies from "universal-cookie";
import BoxesGroupedSingle from "./BoxesGroupedSingle"

const BoxesGroupedPage = (props) => {

    const cookies = new Cookies()
    const jwtToken = cookies.get("jwtToken")
    const [groupedBoxes, setGroupedBoxes] = useState()

    useEffect(() => {
        let mounted = true
        if (mounted) {
            axios.get(urls.baseURL + urls.subscriptionURL + urls.ordersGrouped, {
                headers: {
                    'Authorization': jwtToken
                }
            }).then(res => {
                setGroupedBoxes(res.data);
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

    if (!groupedBoxes) return null

    return (
        <>
        <h1>All subscriptions grouped by box type</h1>
            <ul>
                {groupedBoxes.map(boxGroup => (
                    <BoxesGroupedSingle
                        selectGroupedBoxesProps={props.selectGroupedBoxesProps}
                        key={boxGroup.boxID}
                        box={boxGroup} />
                ))}
            </ul>
        </>
    )
}
export default BoxesGroupedPage