import React, { useState } from "react"
import styles from "./LoginPage.module.css"
import jwt_decode from "jwt-decode"
import * as urls from "./../../URL"
import axios from "axios"
import { useHistory } from "react-router"

const LoginPage = (props) => {
    
    const history = useHistory()

    const [loginDetails, setLoginDetails] = useState({
        username: "",
        password: "",
    })

    const loginToApp = (loginDetails) => {
        axios.post(urls.baseURL + urls.loginToApp, loginDetails)
            .then(res => {
                var jwtToken = res.data.Authorization
                var decodedJWT = jwt_decode(jwtToken)
                console.log(decodedJWT)
                props.handleLoginProps(decodedJWT.role, jwtToken)
            })
            .catch(err => {
                if (!err) {
                    alert("There seems to be an connection issue on our side. Please call 06xxxxxxxx to fix it")
                    return
                }
                else if (err.response.status === 403) {
                    alert("Your login details are incorrect")
                    return
                }
                else {
                    alert("Please contact your system administrator with: " + err)
                }
            })
    }

    const onChange = e => {
        setLoginDetails({
            ...loginDetails,
            [e.target.name]: e.target.value,
        })
    }

    const handleLoginsubmit = e => {
        e.preventDefault();
        if (loginDetails.username.trim() && loginDetails.password.trim()) {
            loginToApp(loginDetails)
        }
        else {
            alert("Please supply both a username and password")
        }

    }

    const registerRedirect = () => {
        history.push("/register")
    }

    return (
        <>
        <h1>Login</h1>
            <form onSubmit={handleLoginsubmit} className={styles.login_form}>
                <div className={styles.row}>
                    <label>
                        Username:
                        <input
                            type="text"
                            name="username"
                            placeholder="Enter username"
                            value={loginDetails.username}
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
                            value={loginDetails.password}
                            onChange={onChange}
                        />
                    </label>
                </div>
                <div className={styles.row}>
                    <input type="submit" value="Login" />
                </div>
            </form>
            <button onClick={registerRedirect}>Register instead</button>
        </>
    )
}

export default LoginPage