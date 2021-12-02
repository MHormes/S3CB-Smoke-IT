import axios from "axios";
import React from "react"
import styles from "./NewsItem.module.css"
import * as urls from "./../../URL"

const NewsItem = (props) => {

    const adminLog = localStorage.getItem("adminLog")
    const jwtToken = localStorage.getItem("jwtToken")
    const message = props.messageProps;

    const deleteMessage = (messageId) => {
        axios.delete(urls.baseURL + urls.newsFeed + urls.deleteMessage + messageId, {
            headers: {
                'Authorization': jwtToken
            }
        }
        ).then(res => {
            console.log("Item has been deleted")
            props.deleteMessageProps(res.data)
        }).catch(err => {
            console.log(err)
        })
    }

    const updateMessage = () => {
        alert("No update for you")
    }

    const updateButton = () => {
        return (
            <button onClick={() => {if (window.confirm("Are you sure you wish to update message: " + message.title + "?")) {updateMessage()}}}>
                Edit message
            </button>
        )
    }

    const deleteButton = () => {
        return (
            <button onClick={() => {if (window.confirm("Are you sure you wish to delete message: " + message.title + "?")) { deleteMessage(message.id)}}}>
                Delete message
            </button>
        )
    }

    //extra option when admin
    let updateMessageButton = null
    let deleteMessagebButton = null;

    if (adminLog === "true") {
        updateMessageButton = updateButton();
        deleteMessagebButton = deleteButton();
    }

    return (
        <li>
            <p className={styles.title}>{message.title}</p>
            <p className={styles.text}>{message.text}</p><p className={styles.date}>{message.postDate}</p>
            {updateMessageButton}
            {deleteMessagebButton}


        </li>

    )

}
export default NewsItem