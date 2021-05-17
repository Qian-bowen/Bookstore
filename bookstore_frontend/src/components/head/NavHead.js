import React from 'react';
import { Link } from 'react-router-dom';
import {Selector} from "../tool/Choose";

const option_item=["书名","作者","IBSN"];


class SearchBar extends React.Component{
    constructor(props) {
        super(props);
    }

    render()
    {
        return(
            <div className="columns">
                <div className="column">
                </div>
                <div className="column is-two-thirds">
                    <div className={"field is-grouped"}>
                        <input className="input is-link is-inverted" type="text" placeholder="输入名称进行搜索"/>
                        <Selector option={option_item}/>
                        <button className="button is-primary is-inverted is-rounded">搜索</button>
                    </div>
                </div>
                <div className="column">
                </div>
            </div>
        );
    }
}


export default class NavHead extends React.Component{

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
                                                <span>管理测试</span>
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
                                        <span className="navbar-item">
                                            <Link to={"/login"}>
                                                <a className="button is-primary is-inverted">
                                                    <span>登录账户</span>
                                                </a>
                                            </Link>
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
                            <SearchBar/>
                        </div>
                    </div>
                </section>
            </div>
        );
    }
}

