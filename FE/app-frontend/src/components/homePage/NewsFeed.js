import React, { useState, useEffect } from "react";
import axios from "axios";
import * as urls from "./../../URL"
import SockJS from "sockjs-client"
import Stomp from "stompjs"
import styles from "./NewsFeed.module.css"
import NewsItems from "./NewsItems"

//THIS PAGE IS A WORKING WEBSOCKET EXAMPLE. THE NEWS FEED IS SUBSCRIBED TO AN ENDPOINT AND WILL UPDATE WHEN A NEW MESSAGE IS POSTED
const NewsFeed = () => {

    const adminLog = localStorage.getItem("adminLog")

    const [stompClient, setStompClient] = useState()
    const [newMessage, setNewMessage] = useState({
        id: 0,
        title: "",
        text: ""
    })

    const [messages, setMessages] = useState()

    //Method to make axios call for news items
    const refreshNews = () => {
        axios.get(urls.baseURL + urls.newsFeed)
           .then(res => {
           setMessages(res.data);
           }).catch(err => {
               if (!err.status) {
                   alert("There seems to be an connection issue on our side. Please call 06xxxxxxxx to fix it")
               }
               else {
                   alert(err.status)
               }
          });
    }

    useEffect(() => {
        //call axios method
        refreshNews();
        //initialze connection with be
        const socket = SockJS(urls.baseURL + urls.newsWebSocket)
        const stompClient = Stomp.over(socket)
        stompClient.connect({}, () => {
            stompClient.subscribe("/news/feed", (data) => {
                //when new message posted, update list of messags
                const receivedMessage = data.body
                const messageObject = JSON.parse(receivedMessage)
                setMessages(messageObject)
            })
        })
        setStompClient(stompClient)
    }, [])

    //On change for posting news message form
    const onChange = e => {
        setNewMessage({
            ...newMessage,
            [e.target.name]: e.target.value,
        })
    }

    //submit method for news messages
    const sendNewsMessage = e => {
        e.preventDefault();
        stompClient.send("/app/postNews", {}, JSON.stringify({ 'text': newMessage.text, 'title': newMessage.title }))
        setNewMessage({
            title: "",
            text: ""
        })
    }

    //Form for posting new news message
    const addMessageFields = () => {
        return (
            <form>
                <div>
                    <label>
                        Title:
                        <input
                            type="text"
                            name="title"
                            placeholder="Enter a title"
                            value={newMessage.title}
                            onChange={onChange}
                        />
                    </label>
                </div>
                <div>
                    <label>Text:
                        <input
                            type="text"
                            name="text"
                            placeholder="Enter some text"
                            value={newMessage.text}
                            onChange={onChange}
                        />
                    </label>
                </div>
                <div>
                    <button value="Post message" onClick={sendNewsMessage} />
                </div>
            </form>
        )
    }

    //extra option when admin
    let newNewsMessageForm = null
    let updateMessageButton = null
    let deleteMessagebButton = null;
    if (adminLog === "true") {
        newNewsMessageForm = addMessageFields();
    }

    if (!messages) return null;

    return (
        <div className={styles.newsFeed}>
            <p className={styles.p1}>News Feed</p>
            <ul className={styles.ul}>
                {messages.map(message => (
                    <NewsItems
                        key={message.id}
                        messageProps={message}
                    />
                ))}
                {updateMessageButton}
                {deleteMessagebButton}
            </ul>
            {newNewsMessageForm}
        </div>
    )

}

export default NewsFeed
