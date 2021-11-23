import React, { useState, useEffect } from "react"
import axios from "axios"
import * as urls from "./../../URL"
import BoxesGroupedSingle from "./BoxesGroupedSingle"

const BoxesGroupedPage = (props) => {

    const jwtToken = props.jwtTokenProps
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
                    alert(err.status)
                }
            });
        }

        return () => mounted = false;
    }, [])

    if (!groupedBoxes) return null

    return (
        <>
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