import axios from "axios";
import React from "react";
import { useHistory } from "react-router";
import styles from "./BoxSingle.module.css";

const BoxSingle = (props) => {

    const singleBox = props.box;
    const history = useHistory();

    const selectBox = (selectedBox) => {
        props.getSelectedBoxProps(selectedBox);
    }

    const deleteBox = (boxToDelete) => {
        var URL = "http://localhost:8080/boxes/delete/" + boxToDelete.id;
        axios.delete(URL).then(res => console.log(res));
        history.push("/boxes");
    }

    const updateBox = (boxToUpdate) => {
        props.getBoxToEditProps(boxToUpdate);
    }
    return (
        <li>
            <div className={styles.box} onClick={() => selectBox(singleBox)}>
                {singleBox.name} {singleBox.basePrice}
            </div>
            <button onClick={() => { if (window.confirm("Are you sure you wish to delete the " + singleBox.name + "?")) { deleteBox(singleBox) } }}>
                delete
            </button>
            <button onClick={() => { if (window.confirm("Update the " + singleBox.name + "?")) { updateBox(singleBox) } }}>
                update
            </button>
        </li>
    )


}

export default BoxSingle