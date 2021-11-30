import React from "react"
import * as urls from "./../../URL"
import styles from "./SelectedBoxContent.module.css"

const SelectedBoxContent = (props) => {

    const selectedBox = props.selectedBoxProps
    const contentList = selectedBox.content.split(",");
    console.log(selectedBox.imagePath)
    const contentItems = contentList.map((content) =>
        <li className={styles.li} key={content}>{content}</li>
    )
    //{selectedBox.imagePath}
    return (
        <>
            <h1 className={styles.h1}>{selectedBox.name}</h1>
            <img className={styles.img} src={urls.imageWebServer + selectedBox.name + ".png"} alt={selectedBox.name + " image could not be loaded"} />
            <p className={styles.p1}>{selectedBox.description}</p>
            <p className={styles.p1}>This box contains items like:</p>
            <ul className={styles.ul}>
                {contentItems}
            </ul>
        </>
    )
}

export default SelectedBoxContent