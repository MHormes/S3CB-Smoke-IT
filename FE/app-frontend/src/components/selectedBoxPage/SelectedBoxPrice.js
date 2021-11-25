import axios from "axios"
import React, { useState } from "react"
import * as urls from "./../../URL"
import * as func from "./amountButtonFunction"

const SelectedBoxPrice = (props) => {

    const [price, setPrice] = useState(props.selectedBoxProps.basePrice)
    const selectedBox = props.selectedBoxProps;
    const [monthSelection, setMonthSelection] = useState(-1)
    const [amountSelected, setAmountSelected] = useState(0)
    var roundedPrice = price.toFixed(2)


    const continueToCheckout = () => {
        const checkoutDetails = {
            boxId: selectedBox.id,
            boxName: selectedBox.name,
            boxContent: selectedBox.content,
            boxDetails: selectedBox.details,
            frequency: monthSelection,
            amount: amountSelected,
            price: roundedPrice
        }
        if (monthSelection >= 0 && amountSelected >= 1) {
            localStorage.setItem("checkoutDetails", JSON.stringify(checkoutDetails));
            props.getCheckoutDetailsProps(checkoutDetails)
        }
        else {
            alert("Please select a amount and frequency before continuing")
        }
    }



    const changePrice = (number) => {
        setAmountSelected(number)
        axios.get(urls.baseURL + urls.boxesURL + selectedBox.id + urls.boxesGetPrice, { params: { amount: number } })
            .then(res => {
                setPrice(res.data)
            }).catch(err =>{
                if(err.status == null){
                    alert("There seems to be an connection issue on our side. Please call 06xxxxxxxx to fix it")
                }
                else{
                    alert(err.status)
                }
            })
    }

    return (
        <>
            <p>Select the totoal amount of boxes you wish to receive:</p>
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


            <p>Select the frequence of the delivery:</p>
            <button onClick={() => setMonthSelection(0)} disabled={func.button1(amountSelected)}>1 box</button>
            <button onClick={() => setMonthSelection(1)} disabled={func.button1m(amountSelected)}>Once month</button>
            <button onClick={() => setMonthSelection(2)} disabled={func.button2(amountSelected)}>Once every 2 months</button>
            <button onClick={() => setMonthSelection(4)} disabled={func.button3(amountSelected)}>Once every quarter</button>
            <button onClick={() => setMonthSelection(6)} disabled={func.button6(amountSelected)}>Once every 6 months</button>

            <h1>€ {roundedPrice}</h1>
            <button onClick={continueToCheckout}>Continue to checkout</button>
        </>
    )
}

export default SelectedBoxPrice