import React, { useState } from "react"
import * as urls from "./../../URL"
import axios from "axios"
import styles from "./RegistrationPage.module.css"

const RegistrationPage = (props) => {

    const [registrationDetails, setRegistrationDetails] = useState({
        username: "",
        password: "",
        email: ""
    })

    const registerToApp = (registrationDetails) => {
        axios.post(urls.baseURL + urls.registerToApp, registrationDetails)
            .then(res => {
                if (res.status === "200") {
                    alert("Your account creation was successful, You can use your details to login now")
                }
                console.log(res.data)
            })
            .catch(err => {
                if (!err) {
                    alert("There seems to be an connection issue on our side. Please call 06xxxxxxxx to fix it")
                } else if (err.response.status === 409) {
                    alert("Username is already taken")
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
        <>
            <h1 className={styles.h1}>Register for an account</h1>
            <form onSubmit={handleRegistrationSubmit} className={styles.register_form}>
                <div className={styles.row}>
                    <label>
                        Username:
                        <input
                            type="text"
                            name="username"
                            placeholder="Enter username"
                            value={registrationDetails.username}
                            onChange={onChange}
                            required
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
                            required
                        />
                    </label>
                </div>
                <div className={styles.row}>
                    <label>Email address:
                        <input
                            type="text"
                            name="email"
                            placeholder="Enter email"
                            value={registrationDetails.email}
                            onChange={onChange}
                            pattern='/^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/'
                            required
                        />
                    </label>
                </div>
                <br/>
                <div className={styles.row}>
                    <input type="submit" value="Register" />
                </div>
            </form>
        </>
    )
}

export default RegistrationPage