import React, { useState, useEffect } from "react";
import axios from "axios";
import * as urls from "./../../URL"
import SockJS from "sockjs-client"
import Stomp from "stompjs"
import styles from "./NewsFeed.module.css"
import NewsItems from "./NewsItems"

const NewsFeed = () => {

    const adminLog = localStorage.getItem("adminLog")

    const [stompClient, setStompClient] = useState()
    const [newMessage, setNewMessage] = useState({
        id: 0,
        title: "",
        text: ""
    })

    const [messages, setMessages] = useState([
        {
            "id": 1,
            "title": "Some title",
            "text": "This is a news message",
            "date": "29/11/2021"
        },
        {
            "id": 2,
            "title": "Another title",
            "text": "Wow news message",
            "date": "29/11/2021"
        }])

    const refreshNews = () => {
        //axios.get(urls.baseURL + urls.getMessages)
        //    .then(res => {
        //    setMessages(res.data);
        //    }).catch(err => {
        //        if (!err.status) {
        //            alert("There seems to be an connection issue on our side. Please call 06xxxxxxxx to fix it")
        //        }
        //        else {
        //            alert(err.status)
        //        }
        //   });
    }

    useEffect(() => {
        refreshNews();
        const socket = SockJS(urls.baseURL + urls.newsWebSocket)
        const stompClient = Stomp.over(socket)
        stompClient.connect({}, () => {
            stompClient.subscribe("/news/feed", (data) => {
                const receivedMessage = data.body
                const messageObject = JSON.parse(receivedMessage)
                setMessages(
                    messages => [...messages, { id: messageObject.id, title: messageObject.title, text: messageObject.text, date: messageObject.postDate }]
                )
            })
        })
        setStompClient(stompClient)
    }, [])

    //Button for refreshing the page
    const refreshButtonFunc = () => {
        return (
            <button onClick={() => refreshNews()} className={styles.button}>Refresh Feed</button>
        )
    }
    let refreshButton = refreshButtonFunc()

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

    let newNewsMessageForm = null
    if (adminLog === "true") {
        newNewsMessageForm = addMessageFields();
    }

    if (!messages) return null;

    console.log(messages)
    return (
        <div className={styles.newsFeed}>
            <p className={styles.p1}>News Feed</p>{refreshButton}
            <ul className={styles.ul}>
                {messages.map(message => (
                    <NewsItems
                        key={message.id}
                        messageProps={message}
                    />
                ))}
            </ul>
            {newNewsMessageForm}
        </div>
    )

}

export default NewsFeed
