import React, { useState } from "react"
import styles from "./LoginPage.module.css"
import jwt_decode from "jwt-decode"
import * as urls from "./../../URL"
import axios from "axios"

const RegistrationPage = (props) => {

    const [registrationDetails, setRegistrationDetails] = useState({
        username: "",
        password: "",
        email: ""
    })

    const registerToApp = (registrationDetails) => {
        axios.post(urls.baseURL + urls.registerToApp, registrationDetails)
            .then(res => {
                if(res.response.status === "200"){
                    alert("Your account creation was successful, You can use your details to login now")
                }
                console.log(res.data)
            })
            .catch(err => {
                if (!err.status) {
                    alert("There seems to be an connection issue on our side. Please call 06xxxxxxxx to fix it")
                    return
                }
                else {
                    alert("Please contact your system administrator with: " + err)
                }
            })
    }

    const onChange = e => {
        setRegistrationDetails({
            ...registrationDetails,
            [e.target.name]: e.target.value,
        })
    }

    const handleRegistrationSubmit = e => {
        e.preventDefault();
        if (registrationDetails.username.trim() && registrationDetails.password.trim() && registrationDetails.email.trim()) {
            registerToApp(registrationDetails)
        }
        else {
            alert("Please fill in all fields")
        }

    }

    return (
        <form onSubmit={handleRegistrationSubmit} className={styles.login_form}>
            <div className={styles.row}>
                <label>
                    Username:
                    <input
                        type="text"
                        name="username"
                        placeholder="Enter username"
                        value={registrationDetails.username}
                        onChange={onChange}
                    />
                </label>
            </div>
            <div className={styles.row}>
                <label>Password:
                    <input
                        type="password"
                        name="password"
                        placeholder="Enter password"
                        value={registrationDetails.password}
                        onChange={onChange}
                    />
                </label>
            </div>
            <div className={styles.row}>
                <label>Password:
                    <input
                        type="email"
                        name="email"
                        placeholder="Enter email"
                        value={registrationDetails.email}
                        onChange={onChange}
                    />
                </label>
            </div>
            <div className={styles.row}>
                <input type="submit" value="Register" />
            </div>
        </form>

    )
}

export default RegistrationPage