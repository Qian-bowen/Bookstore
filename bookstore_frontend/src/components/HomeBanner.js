import React from 'react';

class Activity extends React.Component {
    constructor(props) {
        super(props);
    }
}

class SingleBanner extends React.Component {
    constructor(props) {
        super(props);
        this.state = {}
    }

    render() {
        return (
            <section className={"hero is-medium " + this.props.activity.color}>

                <div className="columns">

                    <div className="column is-half">
                        <div className="hero-body">
                            <p className="title is-4">
                                #{this.props.activity.act_type}
                            </p>
                            <p className="title">
                                {this.props.activity.act_title}
                            </p>
                            <p className="subtitle">
                                {this.props.activity.act_subtitle}
                            </p>
                        </div>
                    </div>

                    <div className="column">
                        <div>
                            <figure className="image is-128x128">

                            </figure>
                        </div>
                    </div>

                </div>
            </section>
        );
    }
}


export default class HomeBanner extends React.Component {
    constructor(props) {
        super(props);
        this.state = {}
    }


    render() {
        return (
            <div>
                {
                    this.props.activity.map((it, index) => {

                        return (
                            <div className="block">
                                <SingleBanner
                                    activity={it}/>
                            </div>
                        );
                    })
                }
            </div>
        );
    }
}