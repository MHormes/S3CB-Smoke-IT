import React, { useState, useEffect } from "react";
import axios from "axios";
import * as urls from "./../../URL"
import styles from "./NewsFeed.module.css"
import NewsItems from "./NewsItems"

const NewsFeed = () => {

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
        //axios.get(urls.baseURL + )
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
        console.log("News feed refreshed")
    }

    useEffect(() => {
        let mounted = true
        if (mounted) {
            refreshNews()
        }
        return () => mounted = false;
    }, [])

    const refreshButtonFunc = () => {
        return (
            <button onClick={() => refreshNews()} className={styles.button}>Refresh Feed</button>
        )
    }
    
    let refreshButton = refreshButtonFunc()

    if (!messages) return null;

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
        </div>
    )

}

export default NewsFeed
