import React from 'react';
import {Link} from "react-router-dom";
import {withRouter} from "react-router-dom";
import * as userService from "../services/userService";

class Login extends React.Component {
    constructor(props) {
        super(props);
        this.state={
            username:"",
            password:""
        }
    }

    handle_input_username=(event)=>{
        this.setState({
            username:event.target.value
        });
    }

    handle_input_pwd=(event)=>{
        this.setState({
            password:event.target.value
        });
    }

    login_info=()=>{
        const {username,password}=this.state;
        if(username===""||password==="")
        {
            alert("请输入用户名和密码");
            return;
        }
        const obj={username:username,password:password};
        userService.login(obj);
    }



    render(){
        return(
            <div className="center_main_area">
                <form className="box">
                    <div className="field">
                        <label className="label">用户名</label>
                        <div className="control">
                            <input
                                value={this.state.username}
                                onChange={this.handle_input_username}
                                className="input" type="" placeholder="请输入用户名" />
                        </div>
                    </div>

                    <div className="field">
                        <label className="label">密码</label>
                        <div className="control">
                            <input
                                value={this.state.password}
                                onChange={this.handle_input_pwd}
                                className="input" type="password" placeholder="请输入密码" />
                        </div>
                    </div>

                    <div className="buttons">
                        <a className="button is-primary" onClick={this.login_info}>登录</a>
                        <Link to={"/register"}>
                            <a className="button is-link">注册</a>
                        </Link>
                    </div>

                </form>
            </div>

        );
    }
}

export default withRouter(Login);