import { useHistory } from "react-router"

const LogoutPage = () =>{

    const history = useHistory()
    const logout = () =>{
        localStorage.removeItem("adminLog")
        history.push("/")
    }
    logout()
    return null
}

export default LogoutPage