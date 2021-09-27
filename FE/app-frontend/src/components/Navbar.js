import React from "react";
import { NavLink } from "react-router-dom";

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
            <ul>
                {links.map(link => {
                    return (
                        <li key={link.id}>
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