import React from 'react';
import {Link} from "react-router-dom";

export class SimpleNav extends React.Component {
    constructor(props) {
        super(props);
    }

    handle_manage = (e) => {

        this.props.on_reverse_delete_button();
    }

    handle_search = (e) => {
        this.props.on_search_item(e.target.value);
    }

    render() {
        return (
            <div class="block">
                <section class="hero is-primary is-small">
                    <div class="hero-head">
                        <nav class="navbar">
                            <div class="container">

                                <div id="navbarMenuHeroA" class="navbar-menu">
                                    <div class="navbar-end">
                                        <a class="navbar-item">
                                            <Link to={"/recmd"}>
                                                <span>推荐</span>
                                            </Link>
                                        </a>
                                        <a class="navbar-item">
                                            <span>最热</span>
                                        </a>
                                        <span class="navbar-item">
                                    <a className={"button is-primary is-inverted"}
                                       onClick={this.handle_manage}
                                    >
                                            <span>管理</span>
                                    </a>
                                </span>

                                    </div>
                                </div>
                            </div>
                        </nav>
                    </div>

                    <div class="hero-body">

                        <div class="columns">
                            <div class="column">

                            </div>
                            <div class="column is-half">
                        <span class="navbar-item">
                            <input class="input is-link is-inverted" type="text" placeholder="搜索"
                                   onChange={this.handle_search}
                            />
                        </span>
                            </div>
                            <div class="column">

                            </div>
                        </div>
                    </div>
                </section>
            </div>
        );
    }
}

export class CommNav extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            search: "",
        }
    }

    set_search_value = (e) => {
        this.setState({search: e.target.value});
    }

    handle_search = () => {
        this.props.on_search_item(this.state.search);
        document.getElementById("search_input").value = "";
    }

    render() {
        return (
            <div class="block">
                <section class="hero is-primary is-small">
                    <div class="hero-head">
                        <nav class="navbar">
                            <div class="container">

                                <div id="navbarMenuHeroA" class="navbar-menu">
                                    <div class="navbar-end">
                                        <a className="navbar-item">
                                            <Link to={"/"}>
                                                <span>首页</span>
                                            </Link>
                                        </a>
                                        <a className="navbar-item">
                                            <Link to={"/recmd"}>
                                                <span>推荐</span>
                                            </Link>
                                        </a>
                                        <a class="navbar-item">
                                            <Link to={"/cart"}>
                                                <span>购物车</span>
                                            </Link>
                                        </a>
                                        <span class="navbar-item">
                                    <a className={"button is-primary is-inverted"}>
                                        <Link to={"/manage"}>
                                            <span>管理面板</span>
                                        </Link>
                                    </a>
                                </span>

                                    </div>
                                </div>
                            </div>
                        </nav>
                    </div>

                    <div class="hero-body">

                        <div class="columns">
                            <div class="column">

                            </div>
                            <div class="column is-half">
                        <span class="navbar-item">
                            <input class="input is-link is-inverted" type="text" placeholder="搜索"
                                   id={"search_input"}
                                   onChange={this.set_search_value}
                            />
                            <button className="button is-primary is-light" onClick={this.handle_search}>搜索</button>
                        </span>
                            </div>
                            <div class="column">

                            </div>
                        </div>
                    </div>
                </section>
            </div>
        );
    }
}

