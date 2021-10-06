import React, { useState } from "react";
import { Route, Switch, useHistory } from "react-router";
import NewsFeed from "./mainPage/NewsFeed";
import WelcomeContent from "./mainPage/WelcomeContent";
import BoxList from "./boxesPage/BoxList";
import Header from "./Header";
import Navbar from "./Navbar";
import SelectedBoxPage from "./selectedBoxPage/SelectedBoxPage";
import BoxAdd from "./boxesPage/BoxAdd";
import BoxEdit from "./boxesPage/BoxEdit";
import LoginPage from "./logAndRegPage/LoginPage";

const MainContainer = () => {

    //needs update to be taken from the login
    var adminLog = true;
    const setAdminLog = (loginResult) => {
        this.adminLog = loginResult;
    }

    const history = useHistory();

    const [selectedBox, setSelectedBox] = useState();
    const getSelectedBox = (box) => {
        setSelectedBox(box);
        history.push("/boxes/selectedBox");
    }

    const [boxToEdit, setBoxToEdit] = useState();
    const getBoxToEdit = (box) => {
        setBoxToEdit(box);
        history.push("/boxes/update");
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
                        adminLogProps={adminLog}
                        getSelectedBoxProps={getSelectedBox}
                        getBoxToEditProps={getBoxToEdit}
                    />
                </Route>
                <Route path="/boxes/selectedBox">
                    <SelectedBoxPage
                        selectedBoxProps={selectedBox}
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
                        getLoginResultProps={setAdminLog}
                    />
                </Route>
            </Switch>
        </>

    )

}

export default MainContainer