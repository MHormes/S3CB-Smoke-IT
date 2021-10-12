import React, { useState } from "react"
import styles from "./LoginPage.module.css"
import * as urls from "./../../URL"
import axios from "axios"

const LoginPage = (props) => {

    const [loginDetails, setLoginDetails] = useState({
        username: "",
        password: "",
    })

    const loginToApp = (loginDetails) => {
        axios.get(urls.baseURL + urls.loginToApp, { params: {username: loginDetails.username, password: loginDetails.password}})
            .then(res => {
                props.getLoginResultProps(res.data.admin);
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

    return (
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

    )
}

export default LoginPage