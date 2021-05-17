import React from 'react';
import './App.css';
import Login from "./components/Login";
import {HashRouter, Route,Router, BrowserRouter,} from "react-router-dom";
import HomeView from "./view/HomeView";
import CartView from "./view/CartView";
import RecmdView from "./view/RecmdView";
import BookDetailView from "./view/BookDetailView";
import AdminManageView from "./view/AdminManageView";
import Register from "./components/Register"
import HotGdsView from "./view/HotGdsView";
import {Switch} from "react-router";
import {history} from "./utils/history";



export default class App extends React.Component {
    constructor(props) {
        super(props);

        history.listen((location, action) => {
            // clear alert on location change
            console.log(location,action);
        });
    }

      render() {
        return (
            <Router history={history}>
                <Switch>
                    <Route exact path="/" component={HomeView}/>
                    <Route exact path="/recmd" component={RecmdView} />
                    <Route exact path="/login" component={Login} />
                    <Route exact path="/cart" component={CartView} />
                    <Route exact path="/bookdetail" component={BookDetailView} />
                    <Route exact path="/manage" component={AdminManageView} />
                    <Route exact path="/register" component={Register} />
                    <Route exact path="/hotgds" component={HotGdsView} />
                </Switch>
           </Router>
        );
      }
}