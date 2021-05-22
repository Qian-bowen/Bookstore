import React from 'react';
import { Router, Route, Switch, Redirect} from 'react-router-dom';
import {history} from "../utils/history";
import Login from "../components/Login";
import LoginRoute from "./LoginRoute";
import PrivateRoute from "./PrivateRoute";
import Home from "../view/HomeView";
import RecmdView from "../view/RecmdView";
import BookDetailView from "../view/BookDetailView";
import CartView from "../view/CartView";
import AdminManageView from "../view/AdminManageView";
import Register from "../components/Register";
import HotGdsView from "../view/HotGdsView";

class BasicRoute extends React.Component{
    constructor(props) {
        super(props);
        history.listen((location,action)=>{
            console.log(location,action);
        });
    }

    render(){
        return(
            <Router history={history}>
                <Switch>
                    <Route exact path="/" component={Home} />
                    <PrivateRoute exact path="/bookdetail" component={BookDetailView} />
                    <PrivateRoute exact path="/recmd" component={RecmdView} />
                    <LoginRoute exact path="/login" component={Login}/>
                    <PrivateRoute exact path="/cart" component={CartView} />
                    <PrivateRoute exact path="/manage" component={AdminManageView} />
                    <Route exact path="/register" component={Register} />
                    <PrivateRoute exact path="/hotgds" component={HotGdsView} />
                    <Redirect from="/*" to="/" />
                </Switch>
            </Router>
        )
    }
}

export default BasicRoute;