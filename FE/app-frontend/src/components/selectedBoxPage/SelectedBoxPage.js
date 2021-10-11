import React from 'react'
import SelectedBoxPrice from "./SelectedBoxPrice"
import SelectedBoxContent from "./SelectedBoxContent"

const SelectedBoxPage = (props) => {

    const selectedBox = JSON.parse(localStorage.getItem("selectedBox"))

    if (selectedBox == null) return <h1>no box selected</h1>
    return (
        <>
            <h1>{selectedBox.name}</h1>
            <SelectedBoxContent
                selectedBoxProps={selectedBox} />
            <SelectedBoxPrice
                selectedBoxProps={selectedBox}
                getCheckoutDetailsProps={props.getCheckoutDetailsProps} />
        </>
    )
}

export default SelectedBoxPage