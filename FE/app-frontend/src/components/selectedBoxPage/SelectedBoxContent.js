import React from "react"
import * as urls from "./../../URL"

const SelectedBoxContent = (props) => {

    const selectedBox = props.selectedBoxProps
    const contentList = selectedBox.content.split(",");
    console.log(selectedBox.imagePath)
    const contentItems = contentList.map((content) =>
        <li key={content}>{content}</li>
    )
    //{selectedBox.imagePath}
    return (
        <>

            <img src={urls.imageWebServer + selectedBox.name + ".png"} alt={selectedBox.name + " image could not be loaded"} />
            <p>{selectedBox.description}</p>
            <ul>
                {contentItems}
            </ul>

            <p>{selectedBox.content}</p>
        </>
    )
}

export default SelectedBoxContent