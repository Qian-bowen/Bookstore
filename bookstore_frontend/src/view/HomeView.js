import React from 'react';
import NavHead from "../components/head/NavHead";
import HomeBanner from "../components/HomeBanner";
import {withRouter} from "react-router-dom";
import {get_visitor_num} from "../services/visitService";

/*
* activity format
* */
let activity1 = {
    color: "is-link",
    act_type: "三月专题",
    act_title: "意大利文学",
    act_subtitle: "意大利文学"
}

let activity2 = {
    color: "is-info",
    act_type: "对谈",
    act_title: "余华x张怡微",
    act_subtitle: "如何写好高考作文"
}

let activity3 = {
    color: "is-warning",
    act_type: "新书上架",
    act_title: "三体",
    act_subtitle: "探索宇宙的奥秘"
}

let activity_test = [activity1, activity2, activity3];

class Home extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            visitor_num: null,
            result: null,
            showSearch: false
        }

    }

    componentDidMount() {
        let user = localStorage.getItem("user");
        this.setState({user: user});
        get_visitor_num(this.set_visitor_num);
    }

    set_visitor_num = (data) => {
        if (data.status < 0) return;
        this.setState({visitor_num: data.data.visit})
    }

    showSearchResultCallback = (msg) => {
        if (msg.status < 0) {
            alert("查询结果不存在");
            return;
        }
        console.log(msg.data.result)
        this.setState({result: msg.data.result});

    }


    renderResult = (result) => {
        if (result == null) return;
        console.log("render result")
        let len = result.length;
        let array = [];
        for (let i = 0; i < len; ++i) {
            array.push(
                <div>
                    <h1>{result[i].name}</h1>
                    <p>{result[i].description}</p>
                </div>
            )
        }
        console.log(array)
        return (
            <div className="content">
                {
                    array
                }
            </div>
        );

    }


    render() {
        return (
            <div>
                <NavHead showSearchResult={this.showSearchResultCallback}/>
                {
                    (this.state.visitor_num == null) ? null : (
                        <h5>访问人数：{this.state.visitor_num}</h5>
                    )
                }
                {
                    this.renderResult(this.state.result)
                }
                {/*<HomeBanner activity={activity_test}/>*/}
            </div>
        );
    }
}

export default withRouter(Home);