import React from "react";
import one from "../../asserts/lufei.jpg";
import * as userService from "../../services/userService";
/*
*  Avatar
*  args: basic info of user
*  description: render username\user id\image of user
* */

const avatar_info={
    username:"guagua",
    id:"gggg",
    img_src:one
}

class Avatar extends React.Component{
    constructor(props) {
        super(props);
        this.state={
            username:"",
            identity:"",
        }
    }

    componentDidMount() {
        const callback=(data)=>{
            console.log(data);
            let username=data.data.username;
            let usertype=data.data.userType;
            let userType;
            if(usertype===0)
            {
                userType="普通用户";
            }
            else if(usertype===1)
            {
                userType="管理员";
            }
            else
            {
                userType="非法用户";
            }
            this.setState({username:data.data.username,identity:userType});
        }
        userService.checkSession(callback);
    }

    render()
    {
        return(
            <div className="media">
                {/*<div className="media-left">*/}
                {/*    <figure className="image is-48x48">*/}
                {/*        <img className="is-rounded" src={this.props.user.img_src}*/}
                {/*             alt="Placeholder image" />*/}
                {/*    </figure>*/}
                {/*</div>*/}

                <div className="media-content">
                    <p className="title is-4">{this.state.username}</p>
                    <p className="subtitle is-6">@{this.state.identity}</p>
                </div>
            </div>
        );
    }
}

export {Avatar}