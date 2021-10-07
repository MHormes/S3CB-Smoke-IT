import axios from "axios"
import React, { useState } from "react"
import * as urls from "./../../URL"

const SelectedBoxPrice = (props) => {

    const [price, setPrice] = useState(props.selectedBoxProps.basePrice)

    var roundedPrice = price.toFixed(2)

    const changePrice = (number) => {
        const selectedBox = props.selectedBoxProps
        axios.get(urls.baseURL + urls.boxesURL + selectedBox.id + "/" + urls.boxesGetPrice, { params: { amount: number } })
            .then(res => {
                setPrice(res.data)
            })
    }

    return (
        <>
            <p>Select the amount of boxes you wish to receive:</p>
            <button onClick={() => changePrice(1)}>1</button>
            <button onClick={() => changePrice(2)}>2</button>
            <button onClick={() => changePrice(3)}>3</button>
            <button onClick={() => changePrice(4)}>4</button>
            <button onClick={() => changePrice(5)}>5</button>
            <button onClick={() => changePrice(6)}>6</button>
            <button onClick={() => changePrice(7)}>7</button>
            <button onClick={() => changePrice(8)}>8</button>
            <button onClick={() => changePrice(9)}>9</button>
            <button onClick={() => changePrice(10)}>10</button>
            <button onClick={() => changePrice(11)}>11</button>
            <button onClick={() => changePrice(12)}>12</button>
            <h1>€ {roundedPrice}</h1>
        </>
    )
}

export default SelectedBoxPrice