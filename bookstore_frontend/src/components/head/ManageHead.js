import React from 'react';

class ManageHead extends React.Component{
    constructor(props) {
        super(props);
    }

    handle_panel=(e)=> {
        this.props.reset_panel(e.target.click);
    }

    render()
    {
        return(
            <section className="hero is-primary is-medium">
                <div className="hero-head">
                    <nav className="navbar">
                        <div className="container">
                            <div className="navbar-brand">
                                {this.props.avatar}
                            </div>
                            <div id="navbarMenuHeroA" className="navbar-menu">
                                <div className="navbar-end">
                                    <a className="navbar-item" onClick={this.handle_panel}>
                                        管理面板
                                    </a>
                                    <a className="navbar-item">
                                        设置
                                    </a>
                                </div>
                            </div>
                        </div>
                    </nav>
                </div>
            </section>
        );
    }
}

export {ManageHead}