import React from "react";
import { NavLink } from "react-router-dom";
import styles from "./Navbar.module.css"

const Navbar = () => {
    const links = [
        {
            id: 1,
            path: "/",
            text: "Home",
        },
        {
            id: 2,
            path: "/boxes",
            text: "Boxes",
        },
        {
            id: 3,
            path: "/info",
            text: "Info",
        },
        {
            id: 4,
            path: "/contact",
            text: "Contact",
        },
        {
            id: 5,
            path: "/login",
            text: "Login",
        }
    ]

    return (
        <nav>
            <ul className={styles.nav_list}>
                {links.map(link => {
                    return (
                        <li key={link.id} className={styles.nav_item}>
                            <NavLink to={link.path} activeClassName="active-link" exact>
                                {link.text}
                            </NavLink>
                        </li>
                    )
                }
                )}
            </ul>
        </nav>
    )
}

export default Navbar