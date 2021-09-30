import React, { useState } from "react";
import { Route, Switch, useHistory } from "react-router";
import NewsFeed from "./mainPage/NewsFeed";
import WelcomeContent from "./mainPage/WelcomeContent";
import BoxList from "./boxesPage/BoxList";
import Header from "./Header";
import Navbar from "./Navbar";
import SelectedBoxPage from "./singleBoxPage/SelectedBoxPage";
import BoxAddOrEdit from "./boxesPage/BoxAddOrEdit";

const MainContainer = () => {

    const history = useHistory();

    const [boxToEdit, setBoxToEdit] = useState();
    const getBoxToEdit = (box) => {
        setBoxToEdit(box);
        history.push("/boxes/createOrUpdate");
    }

    const [selectedBox, setSelectedBox] = useState();
    const getSelectedBox = (box) => {
        setSelectedBox(box);
        history.push("/boxes/selectedBox");
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
                <Route path="/boxes/selectedBox">
                    <SelectedBoxPage
                        selectedBoxProps={selectedBox}
                    />
                </Route>
                <Route path="/boxes/createOrUpdate">
                    <BoxAddOrEdit
                        boxToEditProps={boxToEdit}
                    />
                </Route>
            </Switch>
        </>

    )

}

export default MainContainer