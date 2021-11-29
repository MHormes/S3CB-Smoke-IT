import React from "react";
import BoxSingle from "./BoxSingle";
import axios from "axios";
import { useEffect, useState } from "react";
import { useHistory } from "react-router";
import * as urls from "./../../URL"
import styles from "./BoxList.module.css"

//component that holds the list of boxes when viewing all boxes
const BoxList = (props) => {

    const adminLog = localStorage.getItem("adminLog")
    const [boxes, setBoxes] = useState()
    const history = useHistory()

    useEffect(() => {
        let mounted = true
        if (mounted) {
            axios.get(urls.baseURL + urls.boxesURL)
                .then(res => {
                    setBoxes(res.data);
                }).catch(err => {
                    if (!err.status) {
                        alert("There seems to be an connection issue on our side. Please call 06xxxxxxxx to fix it")
                    }
                    else {
                        alert(err.status)
                    }
                });
        }

        return () => mounted = false;
    }, [])

    if (!boxes) return null;

    const AddButton = () => {
        return (
            <button onClick={() => history.push("/boxes/create")}>
                Add box
            </button>
        )
    }

    let addButton = null;
    if (adminLog === "true") {
        addButton = AddButton();
    }

    return (
        <>
            {addButton}
            <ul className={styles.ul}>
                {boxes.map(box => (
                    <BoxSingle
                        selectBoxProps={props.selectBoxProps}
                        getBoxToEditProps={props.getBoxToEditProps}
                        key={box.id}
                        box={box}
                        jwtTokenProps={props.jwtTokenProps} />
                ))}
            </ul>
        </>
    )
}

export default BoxList