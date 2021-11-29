import React from "react"
import styles from "./NewsItem.module.css"

const NewsItem = (props) => {

    const message = props.messageProps;

    return (
        <li>
            <p className={styles.title}>{message.title}</p>
            <p className={styles.text}>{message.text}</p>
            <p className={styles.date}>{message.date}</p>
        </li>

    )

}
export default NewsItem