import React from "react"

const SelectedBoxContent = (props) => {

    const selectedBox = props.selectedBoxProps
    const contentList = selectedBox.content.split(",");
    const contentItems = contentList.map((content) =>
        <li key={content}>{content}</li>
    )

    return (
        <>
        <img src={selectedBox.image} alt={selectedBox.name+" image could not be loaded"}/>
            <p>{selectedBox.description}</p>
            <ul>
                {contentItems}
            </ul>

            <p>{selectedBox.content}</p>
        </>
    )
}

export default SelectedBoxContent