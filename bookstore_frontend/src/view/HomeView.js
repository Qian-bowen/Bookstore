import React from 'react';
import NavHead from "../components/head/NavHead";
import HomeBanner from "../components/HomeBanner";
import {withRouter} from "react-router-dom";

/*
* activity format
* */
let activity1={
    color:"is-link",
    act_type:"三月专题",
    act_title:"意大利文学",
    act_subtitle:"意大利文学"
}

let activity2={
    color:"is-info",
    act_type:"对谈",
    act_title:"余华x张怡微",
    act_subtitle:"如何写好高考作文"
}

let activity3={
    color:"is-warning",
    act_type:"新书上架",
    act_title:"三体",
    act_subtitle:"探索宇宙的奥秘"
}

let activity_test=[activity1,activity2,activity3];

class Home extends React.Component{
    constructor(props) {
        super(props);

    }

    componentDidMount(){
        let user = localStorage.getItem("user");
        this.setState({user:user});
    }

    render()
    {
        return(
            <div>
                <NavHead/>
                <HomeBanner activity={activity_test}/>
            </div>
        );
    }
}

export default withRouter(Home);