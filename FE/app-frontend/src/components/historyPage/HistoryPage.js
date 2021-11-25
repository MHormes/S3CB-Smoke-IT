import jwtDecode from "jwt-decode"
import react,  { useState, useEffect } from "react"
import Cookies from "universal-cookie"
import axios from "axios"
import * as urls from "./../../URL"

const HistoryPage = () => {

    const cookies = new Cookies()
    const jwtToken = cookies.get("jwtToken")
    const decodedJwt = jwtDecode(jwtToken)
    
    const [subscriptions, setSubscriptions] = useState()
    
    useEffect(() => {
        let mounted = true
        if (mounted) {
            axios.get(urls.baseURL + urls.subscriptionURL + decodedJwt.userId, {
                headers: {
                    'Authorization': jwtToken
                }
            }).then(res => {
                setSubscriptions(res.data);
            }).catch(err => {
                if (!err) {
                    alert("There seems to be an connection issue on our side. Please call 06xxxxxxxx to fix it")
                }
                else {
                    alert("Contact your system administrator for error: " + err)
                }
            });
        }

        return () => mounted = false;
    }, [])

    if(!subscriptions) return <p>Any subscriptions you have orderd on this account will appear here (Page will give E500)</p>

    return(
        //Return needs update after userid is included in JWT token
        <>
        
        </>
    )

}

export default HistoryPage