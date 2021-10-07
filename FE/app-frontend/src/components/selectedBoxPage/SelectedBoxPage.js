import React from 'react'
import SelectedBoxPrice from "./SelectedBoxPrice"
import SelectedBoxContent from "./SelectedBoxContent"

const SelectedBoxPage = (props) => {

    const selectedBox = props.selectedBoxProps

    if (selectedBox == null) return <h1>no box selected</h1>
    return (
        <>
            <h1>{selectedBox.name}</h1>
            <SelectedBoxContent
                selectedBoxProps={selectedBox} />
            <SelectedBoxPrice
                selectedBoxProps={selectedBox} />
        </>
    )
}

export default SelectedBoxPage