import React from 'react';

export default class SimpleNav extends React.Component{
    constructor(props) {
        super(props);
    }

    handle_manage=(e)=>{

        this.props.on_reverse_delete_button();
    }

    handle_search=(e)=>{
        this.props.on_search_item(e.target.value);
    }

    render(){
        return(
            <div class="block">
                <section class="hero is-primary is-small">
                    <div class="hero-head">
                        <nav class="navbar">
                            <div class="container">

                                <div id="navbarMenuHeroA" class="navbar-menu">
                                    <div class="navbar-end">
                                        <a class="navbar-item is-active">
                                            <span>推荐</span>
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
                            <input class="input is-link is-inverted" type="text" placeholder="搜索购物车"
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