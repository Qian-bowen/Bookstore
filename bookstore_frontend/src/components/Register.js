import React from 'react';
import * as userService from "../services/userService";

export default class Register extends React.Component {
    constructor(props) {
        super(props);
        this.state={
            username:"",
            password:"",
            password_again:"",
            email:"",
            nickname:"",
            name:"",
            tel:"",
            address:"",
            user_type:0,
            admin_valid_pwd:""
        }
    }

    handle_type_choice=(type)=>{
        if(type==="user")
        {
            this.setState({user_type:0});
        }
        if(type==="admin")
        {
            this.setState({user_type:1});
        }
    }

    handle_input=(event,type)=>{
        let value=event.target.value;
        if(type==="username")
        {
            this.setState({username:value});
        }
        if(type==="password")
        {
            this.setState({password:value});
        }
        if(type==="password_again")
        {
            this.setState({password_again:value});
        }
        if(type==="email")
        {
            this.setState({email:value});
        }
        if(type==="nickname")
        {
            this.setState({nickname:value});
        }
        if(type==="name")
        {
            this.setState({name:value});
        }
        if(type==="tel")
        {
            this.setState({tel:value});
        }
        if(type==="address")
        {
            this.setState({address:value});
        }
        if(type==="admin_valid_pwd")
        {
            this.setState({admin_valid_pwd:value});
        }

    }

    submit_register=()=>{
        let username=document.getElementById('username').value;
        let password=document.getElementById('password').value;
        let password_again=document.getElementById('password_again').value;
        let email=document.getElementById('email').value;
        let nickname=document.getElementById('nickname').value;
        let name=document.getElementById('name').value;
        let tel=document.getElementById('tel').value;
        let address=document.getElementById('address').value;
        let admin_valid_pwd=document.getElementById('admin_valid_pwd').value;
        let ordinary_type=document.getElementById('ordinary_user_type').checked;
        let admin_type=document.getElementById('admin_type').checked;
        let type;
        if(admin_type===true)
        {
            type=1;
        }
        else if(ordinary_type===true)
        {
            type=0;
        }

        if(username===""||password===""||password_again===""||nickname===""||name===""||tel===""||address===""||(admin_type===true&&admin_valid_pwd===""))
        {
            alert("信息缺失");
            return;
        }

        if(document.getElementById("email").validity.valid===false)
        {
            alert("邮箱格式不合法");
            return;
        }

        else if(password!==password_again)
        {
            alert("两次输入密码不一致");
            return;
        }

        let obj={nickname:nickname,name:name,tel:tel,address:address,email:email,username:username,password:password,user_type:type,admin_valid_pwd:admin_valid_pwd};
        userService.register(obj);
    }

    render()
    {
        return(
            <div className="center_main_area">
                <form className="box">
                    <div className="field">
                        <label className="label">用户名</label>
                        <div className="control">
                            <input
                                className="input" type="" placeholder="e.g Hello World" id={"username"}
                            />
                        </div>
                    </div>

                    <div className="field">
                        <label className="label">密码</label>
                        <div className="control">
                            <input
                                className="input" type="password" placeholder="请输入密码" id={"password"}
                            />
                        </div>
                    </div>

                    <div className="field">
                        <label className="label">重复密码</label>
                        <div className="control">
                            <input
                                className="input" type="password" placeholder="请再次输入密码" id={"password_again"}
                            />
                        </div>
                    </div>

                    <div className="field">
                        <label className="label">邮箱</label>
                        <div className="control">
                            <input
                                className="input" type="email" placeholder="" id={"email"}
                                required pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$"
                            />
                        </div>
                    </div>

                    <div className="field">
                        <label className="label">昵称</label>
                        <div className="control">
                            <input
                                className="input" type="" placeholder="" id={"nickname"}
                            />
                        </div>
                    </div>

                    <div className="field">
                        <label className="label">真实姓名</label>
                        <div className="control">
                            <input
                                className="input" type="" placeholder="" id={"name"}
                            />
                        </div>
                    </div>

                    <div className="field">
                        <label className="label">电话</label>
                        <div className="control">
                            <input
                                className="input" type="" placeholder="" id={"tel"}
                            />
                        </div>
                    </div>

                    <div className="field">
                        <label className="label">地址</label>
                        <div className="control">
                            <input
                                className="input" type="" placeholder="" id={"address"}
                            />
                        </div>
                    </div>

                    <div className="field">
                        <div className="control">
                            <label className="radio">
                                <input type="radio" name="user_type" id={"ordinary_user_type"}
                                />
                                    普通用户
                            </label>
                            <label className="radio">
                                <input type="radio" name="user_type" id={"admin_type"}
                                />
                                    管理员
                            </label>
                        </div>
                    </div>

                    <div className="field">
                        <label className="label">管理员验证码</label>
                        <div className="control">
                            <input
                                className="input" type="password" placeholder="若注册为管理员，请填写验证码" id={"admin_valid_pwd"}
                            />
                        </div>
                    </div>


                    <a type="submit" className="button is-link" onClick={this.submit_register}>注册</a>


                </form>
            </div>

        );
    }
}