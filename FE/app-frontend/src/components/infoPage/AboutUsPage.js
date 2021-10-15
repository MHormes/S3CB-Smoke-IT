import React from "react"
import { useHistory } from "react-router"

const InfoPage = () => {

    const adminLog = localStorage.getItem("adminLog")
    const history = useHistory()

    const EditButton = () => {
        return (
            <button onClick={() => history.push("/info/update")}>
                Edit information
            </button>
        )
    }

    let editButton = null
    if(adminLog === "true"){
        editButton = EditButton()
    }

    return (
        <>
        {editButton}
        <p>This is the information page. Here we user can view important stuff, and admins can edit</p>
        </>
    )
}

export default InfoPage