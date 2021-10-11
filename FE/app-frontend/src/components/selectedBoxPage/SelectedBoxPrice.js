import axios from "axios"
import React, { useState } from "react"
import * as urls from "./../../URL"
import * as func from "./amountButtonFunction"

const SelectedBoxPrice = (props) => {

    const [price, setPrice] = useState(props.selectedBoxProps.basePrice)
    const selectedBox = props.selectedBoxProps;
    const [monthSelection, setMonthSelection] = useState(0)
    const [amountSelected, setAmountSelected] = useState(0)
    var roundedPrice = price.toFixed(2)
    

    const continueToCheckout = () => {
        const checkoutDetails = {
            boxName: selectedBox.name,
            boxContent: selectedBox.content,
            boxDetails: selectedBox.details,
            duration: monthSelection,
            amount: amountSelected,
            price: roundedPrice
        }
        props.getCheckoutDetailsProps(checkoutDetails)
    }



    const changePrice = (number) => {
        setAmountSelected(number)
        axios.get(urls.baseURL + urls.boxesURL + selectedBox.id + "/" + urls.boxesGetPrice, { params: { amount: number } })
            .then(res => {
                setPrice(res.data)
            })
    }

    return (
        <>
            <p>Select the duration of your subscription:</p>
            <button onClick={() => setMonthSelection(1)}>1</button>
            <button onClick={() => setMonthSelection(2)}>2</button>
            <button onClick={() => setMonthSelection(3)}>3</button>
            <button onClick={() => setMonthSelection(4)}>4</button>
            <button onClick={() => setMonthSelection(5)}>5</button>
            <button onClick={() => setMonthSelection(6)}>6</button>
            <button onClick={() => setMonthSelection(7)}>7</button>
            <button onClick={() => setMonthSelection(8)}>8</button>
            <button onClick={() => setMonthSelection(9)}>9</button>
            <button onClick={() => setMonthSelection(10)}>10</button>
            <button onClick={() => setMonthSelection(11)}>11</button>
            <button onClick={() => setMonthSelection(12)}>12</button>

            <p>Select the amount of boxes you wish to receive in the selected timeframe:</p>
            <button onClick={() =>changePrice(1)} disabled={func.button1(monthSelection)}>1</button>
            <button onClick={() => changePrice(2)} disabled={func.button2(monthSelection)}>2</button>
            <button onClick={() => changePrice(3)} disabled={func.button3(monthSelection)}>3</button>
            <button onClick={() => changePrice(4)} disabled={func.button4(monthSelection)}>4</button>
            <button onClick={() => changePrice(5)} disabled={func.button5(monthSelection)}>5</button>
            <button onClick={() => changePrice(6)} disabled={func.button6(monthSelection)}>6</button>
            <button onClick={() => changePrice(7)} disabled={func.button7(monthSelection)}>7</button>
            <button onClick={() => changePrice(8)} disabled={func.button8(monthSelection)}>8</button>
            <button onClick={() => changePrice(9)} disabled={func.button9(monthSelection)}>9</button>
            <button onClick={() => changePrice(10)} disabled={func.button10(monthSelection)}>10</button>
            <button onClick={() => changePrice(11)} disabled={func.button11(monthSelection)}>11</button>
            <button onClick={() => changePrice(12)} disabled={func.button12(monthSelection)}>12</button>
            <h1>€ {roundedPrice}</h1>
            <button onClick={continueToCheckout}>Continue to checkout</button>
        </>
    )
}

export default SelectedBoxPrice