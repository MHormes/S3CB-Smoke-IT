import { useEffect } from "react"

const LogoutPage = (props) => {

    const logout = () => {
        props.handleLogoutProps();
    }

    useEffect(() => {
        logout()
    })
    return null
}

export default LogoutPage