import react from "react";

const SelectedOrderBox = (props) => {
    const orderedBox = props.orderedBoxProps

    return(
        <>
        <h1>Packing details:</h1>
        <p>Box to send: {orderedBox.name}</p>
        <p>It contains: {orderedBox.content}</p>
        </>
    )
}

export default SelectedOrderBox