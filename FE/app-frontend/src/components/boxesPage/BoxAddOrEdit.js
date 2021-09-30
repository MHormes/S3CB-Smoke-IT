import React from 'react'

const BoxAddOrEdit = (props) =>{
    const boxToEdit = props.boxToEditProps

    if(boxToEdit == null) return <h1>adding a new box</h1>
    return (
        <>
        <h1>editing an existing box</h1>
        </>
    )
}

export default BoxAddOrEdit