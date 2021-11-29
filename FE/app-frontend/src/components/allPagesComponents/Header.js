import React from "react"
import { useHistory } from "react-router"
import logo from "./../../images/logo.png"
import styles from "./Header.module.css"

const Header = () =>{

    const history = useHistory()

    return (
        <img src={logo} alt="Smoke-It" className={styles.img} onClick={() => history.push("/")}></img>
    )
}

export default Header