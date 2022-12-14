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
import RegistrationPage from "./logAndRegPage/RegistrationPage";
import CheckoutPage from "./checkoutPage/CheckoutPage";
import HistoryPage from "./historyPage/HistoryPage";
import AboutUsPage from "./infoPage/AboutUsPage";
import BoxesGroupedPage from "./adminPortal/BoxesGroupedPage";
import CheckoutDone from "./checkoutPage/CheckoutDone";
import SelectedBoxGroupPage from "./adminPortal/SelectedBoxGroupPage";
import SelectedOrderPage from "./adminPortal/SelectedOrderPage";
import HistoryOrders from "./historyPage/HistoryOrders";

const MainContainer = () => {

    const history = useHistory()

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
        localStorage.setItem("jwtToken", jwtToken)
        if (localStorage.getItem("fromCheckout")) {
            history.push("/boxes/selectedBox/checkout")
            localStorage.removeItem("fromCheckout")
        } else {
            history.push("/")
            window.location.reload();
        }
    }

    const handleLogout = () => {
        localStorage.removeItem("adminLog")
        setAdminLog("")
        localStorage.removeItem("jwtToken")
        history.push("/")
    }

    const selectBox = () => {
        history.push("/boxes/selectedBox")
    }

    const [checkoutDetails, setCheckoutDetails] = useState()
    const getCheckoutDetails = (details) => {
        setCheckoutDetails(details)
        history.push("/boxes/selectedBox/checkout")
    }

    const [subscriptionObject, setSubscriptionObject] = useState()
    const finishCheckout = (subscriptionObject) => {
        setSubscriptionObject(subscriptionObject)
        localStorage.removeItem("checkoutDetails")
        history.push("/boxes/selectedBox/checkout/finish")
    }

    const [boxToEdit, setBoxToEdit] = useState();
    const getBoxToEdit = (box) => {
        setBoxToEdit(box)
        history.push("/boxes/update")
    }

    const [boxIdForOrderGroup, setBoxIdForOrderGroup] = useState()
    const [selectedBoxGroupName, setSelectedBoxGroupName] = useState()
    const selectGroupedBoxes = (boxGroup) => {
        setBoxIdForOrderGroup(boxGroup.boxID)
        setSelectedBoxGroupName(boxGroup.boxName)
        history.push("/adminPortal/selectedGroup")
    }

    const selectOrderAdminPortal = () => {
        history.push("/adminPortal/selectedOrder")
    }

    const selectSubscriptionHistory = () => {
        history.push("/history/selectSub")
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
                        selectBoxProps={selectBox}
                        getBoxToEditProps={getBoxToEdit}
                        jwtTokenProps={localStorage.getItem("jwtToken")}
                    />
                </Route>
                <Route exact path="/boxes/selectedBox">
                    <SelectedBoxPage
                        getCheckoutDetailsProps={getCheckoutDetails}
                    />
                </Route>
                <Route exact path="/boxes/selectedBox/checkout">
                    <CheckoutPage
                        checkoutDetailsProps={checkoutDetails}
                        finishCheckoutProps={finishCheckout}
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
                <Route path="/register">
                    <RegistrationPage
                    />
                </Route>
                <Route exact path="/adminPortal/selectedOrder">
                    <SelectedOrderPage
                    />
                </Route>
                {localStorage.getItem("jwtToken") &&
                    <Route exact path="/history">
                        <HistoryPage
                        selectSubscriptionHistoryProps={selectSubscriptionHistory}
                        />
                    </Route>
                }
                {localStorage.getItem("jwtToken") &&
                    <Route exact path="/history/selectSub">
                        <HistoryOrders 
                        />
                    </Route>
                }
                {subscriptionObject != null &&
                    <Route exact path="/boxes/selectedBox/checkout/finish">
                        <CheckoutDone
                            subscriptionObjectProps={subscriptionObject}
                        />
                    </Route>
                }
                {localStorage.getItem("adminLog") === "true" &&
                    <Route exact path="/adminPortal">
                        <BoxesGroupedPage
                            selectGroupedBoxesProps={selectGroupedBoxes}
                        />
                    </Route>
                }
                {localStorage.getItem("adminLog") === "true" ?
                    <Route exact path="/adminPortal/selectedGroup">
                        <SelectedBoxGroupPage
                            jwtTokenProps={localStorage.getItem("jwtToken")}
                            boxIdForOrderGroupProps={boxIdForOrderGroup}
                            selectedBoxGroupNameProps={selectedBoxGroupName}
                            selectOrderProps={selectOrderAdminPortal}
                        />
                    </Route>
                    :
                    <Redirect to="/" />
                }
                {localStorage.getItem("adminLog") === "true" ?
                    <Route path="/boxes/create">
                        <BoxAdd
                            jwtTokenProps={localStorage.getItem("jwtToken")} />
                    </Route>
                    :
                    <Redirect to="/" />
                }
                {localStorage.getItem("adminLog") === "true" && boxToEdit != null ?
                    <Route path="/boxes/update">
                        <BoxEdit
                            boxToEditProps={boxToEdit}
                            jwtTokenProps={localStorage.getItem("jwtToken")}
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