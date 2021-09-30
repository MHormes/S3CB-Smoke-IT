import React from 'react'
import { useEffect } from "react/cjs/react.development"

const SelectedBoxPage = (props) =>{

    const selectedBox = props.selectedBoxProps
    console.log(selectedBox)

    useEffect(() => {
        const storeBox = JSON.stringify(selectedBox)
        localStorage.setItem("selectedBox", storeBox)
    })

    if(selectedBox == null) return <h1>no box selected</h1>
    return (
        <>
        <h1>{selectedBox.name}</h1>
        <p>{selectedBox.basePrice}</p>
        <p>{selectedBox.content}</p>
        <p>{selectedBox.description}</p>
        </>
    )
}

export default SelectedBoxPage