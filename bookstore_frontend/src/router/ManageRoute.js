import React from 'react';
import {Route, Redirect} from 'react-router-dom'
import * as userService from "../services/userService";
import * as Type from "../components/constant/Type";

export default class ManageRoute extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            isAuthed: false,
            hasAuthed: false,
            userType: Type.userType.user,
        };
    }

    checkAuth = (data) => {
        //let data_json=JSON.parse(data.data);
        //console.log("data:"+data_json);
        if (data.status >= 0) {
            this.setState({isAuthed: true, hasAuthed: true, userType: data.data.userType});

        } else {
            localStorage.removeItem('user');
            this.setState({isAuthed: false, hasAuthed: true});
        }
    };


    componentDidMount() {
        userService.checkSession(this.checkAuth);
    }

    redirect_path = (props) => {
        if (!this.state.isAuthed) {
            console.log("not auth");
            return (
                <Redirect to={{
                    pathname: "/login",
                    state: {from: props.location}
                }}/>
            );
        } else if (this.state.userType === Type.userType.user) {
            return (
                <Redirect to={{
                    pathname: "/manage/user",
                    state: {from: props.location}
                }}/>
            );

        } else if (this.state.userType === Type.userType.admin) {
            return (
                <Redirect to={{
                    pathname: "/manage/admin",
                    state: {from: props.location}
                }}/>
            );
        } else {
            return (
                <Redirect to={{
                    pathname: "/",
                    state: {from: props.location}
                }}/>
            );
        }
    }


    render() {

        const {component: Component, path = "/", exact = false, strict = false} = this.props;

        if (!this.state.hasAuthed) {
            return null;
        }

        return <Route path={path} exact={exact} strict={strict} render={props => (
            this.redirect_path(props)
        )}/>
    }
}