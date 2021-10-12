import React, { useState } from "react";
import { Route, Switch, useHistory } from "react-router";
import NewsFeed from "./mainPage/NewsFeed";
import WelcomeContent from "./mainPage/WelcomeContent";
import BoxList from "./boxesPage/BoxList";
import Header from "./allPagesComponents/Header";
import Navbar from "./allPagesComponents/Navbar";
import SelectedBoxPage from "./selectedBoxPage/SelectedBoxPage";
import BoxAdd from "./boxesPage/BoxAdd";
import BoxEdit from "./boxesPage/BoxEdit";
import LoginPage from "./logAndRegPage/LoginPage";
import LogoutPage from "./logAndRegPage/LogoutPage";
import CheckoutPage from "./checkoutPage/CheckoutPage";

const MainContainer = () => {

    const history = useHistory()

    //method to handle login and setting the adminlog for admin functions
    const handleLogin = (loginResult) => {
        localStorage.setItem("adminLog", loginResult)
        history.push("/")
    }

    const [selectedBox, setSelectedBox] = useState();
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
            <Navbar />
            <Switch>
                <Route exact path="/">
                    <NewsFeed />
                    <WelcomeContent />
                </Route>

                <Route exact path="/boxes">
                    <BoxList
                        getSelectedBoxProps={getSelectedBox}
                        getBoxToEditProps={getBoxToEdit}
                    />
                </Route>
                <Route exact path="/boxes/selectedBox">
                    <SelectedBoxPage
                        selectedBoxProps={selectedBox}
                        getCheckoutDetailsProps={getCheckoutDetails}
                    />
                </Route>
                <Route path="/boxes/selectedBox/checkout">
                    <CheckoutPage
                        checkoutDetailsProps={checkoutDetails}
                    />
                </Route>
                <Route path="/boxes/create">
                    <BoxAdd />
                </Route>
                <Route path="/boxes/update">
                    <BoxEdit
                        boxToEditProps={boxToEdit}
                    />
                </Route>
                <Route path="/login">
                    <LoginPage
                        getLoginResultProps={handleLogin}
                    />
                </Route>
                <Route path="/logout">
                    <LogoutPage />
                </Route>
            </Switch>
        </>

    )

}

export default MainContainer