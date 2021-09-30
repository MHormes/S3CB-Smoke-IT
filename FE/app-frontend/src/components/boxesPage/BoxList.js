import React from "react";
import BoxSingle from "./BoxSingle";
import axios from "axios";
import { useEffect, useState } from "react";
import { useHistory } from "react-router";

const BoxList = (props) => {

    const adminLog = true;
    const [boxes, setBoxes] = useState(null);
    const history = useHistory();

    useEffect(() => {
        axios.get("http://localhost:8080/boxes")
            .then(res => {
                setBoxes(res.data);
            });
    })

    if (!boxes) return null;

    const AddButton = () => {
        return (
            <button onClick={() => history.push("/boxes/createOrUpdate")}>
                Add box
            </button>
        )
    }

    let addButton = null;
    if (adminLog) {
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