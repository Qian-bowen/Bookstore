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
import ManageRoute from "./ManageRoute";
import UserManageView from "../view/UserManageView";
import ChatRoomView from "../view/ChatRoomView";

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
                    <ManageRoute exact path="/manage"/>
                    <PrivateRoute exact path="/manage/user" component={UserManageView} />
                    <PrivateRoute exact path="/manage/admin" component={AdminManageView} />
                    <Route exact path="/register" component={Register} />
                    <PrivateRoute exact path="/hotgds" component={HotGdsView} />
                    <PrivateRoute exact path="/chat" component={ChatRoomView} />
                    <Redirect from="/*" to="/" />

                </Switch>
            </Router>
        )
    }
}

export default BasicRoute;