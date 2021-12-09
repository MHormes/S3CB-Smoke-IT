import React, { useState } from "react"
import { useHistory } from "react-router"
import jwtDecode from "jwt-decode"

const CheckoutForm = (props) => {

    const history = useHistory()
    const jwtToken = localStorage.getItem("jwtToken")
    let decodedJWT = null
    if(jwtToken != null){
        decodedJWT = jwtDecode(jwtToken)
    }

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
        if (orderDetails.name.trim() && orderDetails.email.trim() && orderDetails.address.trim() && orderDetails.postal.trim() && orderDetails.city.trim()) {
            props.assignSubscriptionObjectProps(orderDetails)
        }
        else {
            alert("Please fill in all fields")
        }
    }

    const goToLogin = () => {
        localStorage.setItem("fromCheckout", true)
        history.push("/login")
    }

    const goToRegistration = () => {
        localStorage.setItem("fromCheckout", true)
        history.push("/register")
    }

    const notLoggedElement = () => {
        return (
            <>
                <br />
                <p>Logging in/creating an account will save your order to your account</p>
                <br />
                <button onClick={() => goToLogin()} >Login</button><button onClick={() => goToRegistration()}>Register instead</button>
            </>
        )
    }

    const isLoggedElement = () => {
        return (
            <>
                <p>You are currently logged in as: {decodedJWT.sub}</p>
            </>
        )
    }

    let notLogged = null;
    let isLogged = null;
    if (jwtToken === null) {
        notLogged = notLoggedElement()
    } else {
        isLogged = isLoggedElement()
    }

    return (
        <>
            {notLogged}
            <form onSubmit={handleSubmit}>
                <h1>Enter your shipping details</h1>
                {isLogged}
                <label>
                    Full name:
                    <input
                        type="text"
                        name="name"
                        placeholder="e.g. John Doe"
                        value={orderDetails.name}
                        onChange={onChange}
                        required />
                </label>
                <br />
                <label>
                    Email address:
                    <input
                        type="text"
                        name="email"
                        placeholder="e.g. john.doe@gmail.com"
                        value={orderDetails.email}
                        onChange={onChange}
                        required
                        pattern='/^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/' />
                </label>
                <br />
                <label>
                    Address + number:
                    <input
                        type="text"
                        name="address"
                        placeholder="e.g. Rachelsmolen 10"
                        value={orderDetails.address}
                        onChange={onChange}
                        required />
                </label>
                <br />
                <label>
                    Postal code:
                    <input
                        type="text"
                        name="postal"
                        placeholder="e.g. 1234 AB"
                        value={orderDetails.postal}
                        onChange={onChange}
                        required
                        pattern="^[0-9]{4}[A-z]{2}$" />
                </label>
                <br />
                <label>
                    City:
                    <input
                        type="text"
                        name="city"
                        placeholder="e.g. Eindhoven"
                        value={orderDetails.city}
                        onChange={onChange}
                        required />
                </label>
                <br />
                <input type="submit" value="Finish your order" />
            </form>
        </>
    )

}

export default CheckoutForm