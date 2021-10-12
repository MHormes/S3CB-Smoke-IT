import React from "react";
import BoxSingle from "./BoxSingle";
import axios from "axios";
import { useEffect, useState } from "react";
import { useHistory } from "react-router";
import * as urls from "./../../URL"

//component that holds the list of boxes when viewing all boxes
const BoxList = (props) => {

    const adminLog = localStorage.getItem("adminLog")
    const [boxes, setBoxes] = useState()
    const history = useHistory()

    useEffect(() => {
        axios.get(urls.baseURL + urls.boxesURL)
            .then(res => {
                setBoxes(res.data);
            });
    })

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
            <ul>
                {boxes.map(box => (
                    <BoxSingle
                        getSelectedBoxProps={props.getSelectedBoxProps}
                        getBoxToEditProps={props.getBoxToEditProps}
                        key={box.id}
                        box={box} />
                ))}
            </ul>
        </>
    )
}

export default BoxList