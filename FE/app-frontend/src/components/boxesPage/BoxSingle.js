import axios from "axios";
import React from "react";
import { useHistory } from "react-router";
import styles from "./BoxSingle.module.css";
import * as urls from "./../../URL";

const BoxSingle = (props) => {

    const adminLog = localStorage.getItem("adminLog")

    const jwtToken = props.jwtTokenProps
    const singleBox = props.box
    const history = useHistory()

    const selectBox = (selectedBox) => {
        localStorage.setItem("selectedBox", JSON.stringify(selectedBox))
        props.selectBoxProps()
    }

    //Element for update button 
    const UpdateButton = () => {
        return (
            <button onClick={() => { if (window.confirm("Update the " + singleBox.name + "?")) { updateBox(singleBox) } }} data-cy='update-button-boxes'>
                update
            </button>
        )
    }
    //only show if admin is logged
    let updateButton = null
    if (adminLog === "true") {
        updateButton = UpdateButton();
    }
    //method to continue to the update page
    const updateBox = (boxToUpdate) => {
        props.getBoxToEditProps(boxToUpdate);
    }

    //Element for delete button 
    const DeleteButton = () => {
        return (
            <button onClick={() => { if (window.confirm("Are you sure you wish to delete the " + singleBox.name + "?")) { deleteBox(singleBox) } }} data-cy='delete-button-boxes'>
                delete
            </button>
        )
    }
    //only show if admin is logged
    let deleteButton = null
    if (adminLog === "true") {
        deleteButton = DeleteButton();
    }
    //method to perform the delete
    const deleteBox = (boxToDelete) => {
        var URL = urls.baseURL + urls.boxesDeleteURL + boxToDelete.id;
        axios.delete(URL, {
            headers: {
                'Authorization': jwtToken
            }
        }).then(res => {
            if (res.status === 200) {
                console.log("Delete was successfull")
            } else {
                alert(res.status)
            }
        }).catch(err => {
            if (err == null) {
                alert("There seems to be an connection issue on our side. Please call 06xxxxxxxx to fix it")
            }
            else {
                alert(err)
            }
        })
        history.push("/boxes");
    }


    return (
        <li className={styles.li} data-cy='box-list-item'>
            <div className={styles.box} onClick={() => selectBox(singleBox)}>
                <img src={urls.imageWebServer + singleBox.name + ".png"} className={styles.image} alt={singleBox.name + " image could not be loaded"} />
                <p className={styles.p1}>{singleBox.name} {singleBox.basePrice}</p>
            </div>
            {deleteButton}
            {updateButton}
        </li>
    )


}

export default BoxSingle