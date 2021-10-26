import React, { useState } from "react"
import { useHistory } from "react-router"

const CheckoutForm = () => {

    const history = useHistory()

    const [orderDetails, setOrderDetails] = useState({
        name: "",
        email: "",
        address: "",
        postal: "",
        city: ""
    })

    const onChange = e => {
        setOrderDetails({
            ...orderDetails,
            [e.target.name]: e.target.value
        })
    }

    const handleSubmit = e => {
        e.preventDefault()
        if(orderDetails.name.trim() && orderDetails.email.trim() && orderDetails.address.trim() && orderDetails.postal.trim() && orderDetails.city.trim()){
            history.push("/boxes/selectedBox/checkout/payment")
        }
        else{
            alert("Please fill in all fields")
        }
        
    }

    return (
        <>
            <form onSubmit={handleSubmit}>
                <h1>Enter your shipping details</h1>
                <label>
                    Full name:
                    <input
                        type="text"
                        name="name"
                        placeholder="e.g. John Doe"
                        value={orderDetails.name}
                        onChange={onChange} />
                </label>
                <br />
                <label>
                    Email address:
                    <input
                        type="text"
                        name="email"
                        placeholder="e.g. john.doe@gmail.com"
                        value={orderDetails.email}
                        onChange={onChange} />
                </label>
                <br />
                <label>
                    Address + number:
                    <input
                        type="text"
                        name="address"
                        placeholder="e.g. Rachelsmolen 10"
                        value={orderDetails.address}
                        onChange={onChange} />
                </label>
                <br />
                <label>
                    Postal code:
                    <input
                        type="text"
                        name="postal"
                        placeholder="e.g. 1234 AB"
                        value={orderDetails.postal}
                        onChange={onChange} />
                </label>
                <br />
                <label>
                    City:
                    <input
                        type="text"
                        name="city"
                        placeholder="e.g. Eindhoven"
                        value={orderDetails.city}
                        onChange={onChange} />
                </label>
                <br />
                <input type="submit" value="Continue to payment" />
            </form>
        </>
    )

}

export default CheckoutForm