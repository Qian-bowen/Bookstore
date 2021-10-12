import React from 'react';
import {Link, useHistory} from 'react-router-dom';
import {Selector} from "../tool/Choose";
import * as userService from "../../services/userService";
import {searchBookIntro} from "../../services/bookService";

const option_item=["书名","作者","IBSN"];


class SearchBar extends React.Component{
    constructor(props) {
        super(props);
        this.state={
            searchStr:null
        }
    }

    changeSearch=(e)=>{
        this.setState({searchStr:e.target.value});
    }

    searchCallback=(msg)=>{
        console.log(msg);
        this.props.showSearchResult(msg);
    }

    submitSearch=()=>{
        if(this.state.searchStr==null||this.state.searchStr==="")
        {
            return;
        }
        searchBookIntro(this.state.searchStr,this.searchCallback);
    }


    render()
    {
        return(
            <div className="columns">
                <div className="column">
                </div>
                <div className="column is-two-thirds">
                    <div className={"field is-grouped"}>
                        <input className="input is-link is-inverted" type="text" placeholder="输入名称进行搜索" onChange={this.changeSearch}/>
                        <button className="button is-primary is-inverted is-rounded" onClick={this.submitSearch}>搜索</button>
                    </div>
                </div>
                <div className="column">
                </div>
            </div>
        );
    }
}


export default class NavHead extends React.Component{
    constructor(props) {
        super(props);
        this.state={
            isAuthed:false,
        }

    }

    checkAuth = (data) => {
        console.log(data);
        if (data.status >= 0) {
            this.setState({isAuthed: true});
        } else {
            this.setState({isAuthed: false});
        }
    };


    componentDidMount() {
        userService.checkSession(this.checkAuth);
    }

    logout=()=>{
        userService.logout();
        this.setState({isAuthed: false});
    }

    showSearchResultCallback=(msg)=>{
        this.props.showSearchResult(msg);
    }



    render()
    {
        return(
            <div className="block">
                <section className="hero is-primary is-medium">
                    <div className="hero-head">
                        <nav className="navbar">
                            <div className="container">

                                <div id="navbarMenuHeroA" className="navbar-menu">
                                    <div className="navbar-end">
                                        <a className="navbar-item">
                                            <Link to={"/manage"}>
                                                <span>管理</span>
                                            </Link>
                                        </a>
                                        <a className="navbar-item">
                                            <Link to={"/recmd"}>
                                                <span>推荐</span>
                                            </Link>
                                        </a>
                                        <a className="navbar-item">
                                            <Link to={"/hotgds"}>
                                                <span>最热</span>
                                            </Link>
                                        </a>
                                        <a className="navbar-item">
                                            <Link to={"/cart"}>
                                                <span>购物车</span>
                                            </Link>
                                        </a>
                                        <a className="navbar-item">
                                            <Link to={"/chat"}>
                                                <span>聊天室</span>
                                            </Link>
                                        </a>
                                        <span className="navbar-item">
                                            {
                                                (!this.state.isAuthed)?
                                                    (
                                                        <Link to={"/login"}>
                                                            <a className="button is-primary is-inverted">
                                                                <span>登录账户</span>
                                                            </a>
                                                        </Link>
                                                    ): (
                                                        <a className="button is-primary is-inverted" onClick={()=>{this.logout()}}>
                                                            <span>登出账户</span>
                                                        </a>
                                                    )
                                            }
                                        </span>

                                    </div>
                                </div>
                            </div>
                        </nav>
                    </div>

                    <div className="hero-body">
                        <div className="block has-text-centered">
                            <p className="title is-1">
                                西西弗在线
                            </p>
                            <p className="subtitle">
                                你的精神家园
                            </p>
                        </div>
                        <div className={"block"}>
                            <SearchBar showSearchResult={this.showSearchResultCallback}/>
                        </div>
                    </div>
                </section>
            </div>
        );
    }
}

