import React, { useState } from "react";
import { Redirect, Route, Switch, useHistory } from "react-router";
import NewsFeed from "./homePage/NewsFeed";
import WelcomeContent from "./homePage/WelcomeContent";
import BoxList from "./boxesPage/BoxList";
import Header from "./allPagesComponents/Header";
import Navbar from "./allPagesComponents/Navbar";
import SelectedBoxPage from "./selectedBoxPage/SelectedBoxPage";
import BoxAdd from "./boxesPage/BoxAdd";
import BoxEdit from "./boxesPage/BoxEdit";
import LoginPage from "./logAndRegPage/LoginPage";
import LogoutPage from "./logAndRegPage/LogoutPage";
import CheckoutPage from "./checkoutPage/CheckoutPage";
import CheckoutPayment from "./checkoutPage/CheckoutPayment";
import AboutUsPage from "./infoPage/AboutUsPage";
import Cookies from "universal-cookie";

const MainContainer = () => {

    const history = useHistory()
    const cookies = new Cookies()

    //method to handle login and setting the adminlog for admin functions
    const [adminLog, setAdminLog] = useState()
    const handleLogin = (loginResult, jwtToken) => {
        if (loginResult === "ADMIN") {
            localStorage.setItem("adminLog", true)
            setAdminLog(true)
        }
        else {
            localStorage.setItem("adminLog", false)
            setAdminLog(false)
        }
        cookies.set("jwtToken", jwtToken)
        history.push("/")
    }

    const handleLogout = () => {
        localStorage.removeItem("adminLog")
        setAdminLog("")
        cookies.remove("jwtToken")
        history.push("/")
    }


    const [selectedBox, setSelectedBox] = useState()
    const getSelectedBox = (box) => {
        setSelectedBox(box)
        history.push("/boxes/selectedBox")
    }

    const [checkoutDetails, setCheckoutDetails] = useState()
    const getCheckoutDetails = (details) => {
        setCheckoutDetails(details)
        history.push("/boxes/selectedBox/checkout")
    }

    const [boxToEdit, setBoxToEdit] = useState();
    const getBoxToEdit = (box) => {
        setBoxToEdit(box)
        history.push("/boxes/update")
    }


    return (
        <>
            <Header />
            <Navbar
                adminLogProps={adminLog} />
            <Switch>
                <Route exact path="/">
                    <NewsFeed />
                    <WelcomeContent />
                </Route>
                <Route exact path="/boxes">
                    <BoxList
                        getSelectedBoxProps={getSelectedBox}
                        getBoxToEditProps={getBoxToEdit}
                        jwtTokenProps={cookies.get("jwtToken")}
                    />
                </Route>
                <Route exact path="/boxes/selectedBox">
                    <SelectedBoxPage
                        selectedBoxProps={selectedBox}
                        getCheckoutDetailsProps={getCheckoutDetails}
                    />
                </Route>
                <Route exact path="/boxes/selectedBox/checkout">
                    <CheckoutPage
                        checkoutDetailsProps={checkoutDetails}
                    />
                </Route>
                <Route path="/boxes/selectedBox/checkout/payment">
                    <CheckoutPayment
                    />
                </Route>
                <Route excact path="/aboutUs">
                    <AboutUsPage />
                </Route>
                <Route path="/aboutUs/edit">
                </Route>
                <Route path="/login">
                    <LoginPage
                        handleLoginProps={handleLogin}
                    />
                </Route>
                <Route path="/logout">
                    <LogoutPage
                        handleLogoutProps={handleLogout}
                    />
                </Route>
                {localStorage.getItem("adminLog") === "true" ?
                    <Route path="/boxes/create">
                        <BoxAdd
                            jwtTokenProps={cookies.get("jwtToken")} />
                    </Route>
                    :
                    <Redirect to="/" />
                }
                {localStorage.getItem("adminLog") === "true" && boxToEdit != null ?
                    <Route path="/boxes/update">
                        <BoxEdit
                            boxToEditProps={boxToEdit}
                            jwtTokenProps={cookies.get("jwtToken")}
                        />
                    </Route>
                    :
                    <Redirect to="/" />
                }
            </Switch>
        </>

    )

}

export default MainContainer