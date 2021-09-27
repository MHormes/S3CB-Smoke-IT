import React from "react";
import BoxSingle from "./BoxSingle";
import axios from "axios";
import { useEffect, useState } from "react";

const BoxList = () => {

    const adminLog = true;
    const [boxes, setBoxes] = useState(null);

    useEffect(() => {
        axios.get("http://localhost:8080/boxes")
            .then(res => {
                setBoxes(res.data);
            });
    })

    if (!boxes) return null;

    const AddButton = () => {
        return (
            <button onClick={null}>
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
                        key={box.id}
                        box={box} />
                ))}
            </ul>
        </>
    )
}

export default BoxList