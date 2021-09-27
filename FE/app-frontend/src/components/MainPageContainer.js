import React from "react";
import { Route, Switch } from "react-router";
import NewsFeed from "./NewsFeed";
import WelcomeContent from "./WelcomeContent";
import BoxList from "./BoxList";
import Header from "./Header";
import Navbar from "./Navbar";

const MainPageContainer = () => {



    return (
        <>
            <Header />
            <Navbar />
            <Switch>
                <Route exact path="/">
                    <NewsFeed />
                    <WelcomeContent />
                </Route>

                <Route path="/boxes">
                    <BoxList />
                </Route>
            </Switch>
        </>

    )

}

export default MainPageContainer