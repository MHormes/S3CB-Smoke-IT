import React from "react";
import { NavLink } from "react-router-dom";
import styles from "./Navbar.module.css"

const Navbar = () => {

    const logged = localStorage.getItem("adminLog")

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

    const linksLogged = [
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
            path: "/logout",
            text: "Logout",
        }
    ]

    const linksAdmin = [
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
            path: "/adminPortal",
            text: "Admin Portal",
        },
        {
            id: 6,
            path: "/logout",
            text: "Logout",
        }
    ]

    if(logged === null){
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
    else if(logged === "false"){
        return (
            <nav>
                <ul className={styles.nav_list}>
                    {linksLogged.map(link => {
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
    else{
        return (
            <nav>
                <ul className={styles.nav_list}>
                    {linksAdmin.map(link => {
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
    
}

export default Navbar