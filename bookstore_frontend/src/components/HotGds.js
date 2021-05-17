import React from 'react';


class HotGds extends React.Component{
    render()
    {
        return(
            <div className="block">

                <div className="card">
                    <div className="box">
                        <div className="columns">
                            <div className="center_item column is-half">
                                <div className="center_item card-image">
                                    <figure className="image is-4by3">
                                        <img src={this.props.book.img} alt="image missing"/>
                                    </figure>
                                    <img className="cover_rank" src={this.props.rank_img} alt="image missing"/>
                                </div>
                            </div>
                            <div className="center_item column is-half">
                                <p>{this.props.book.brief_intro}</p>
                            </div>
                        </div>
                    </div>

                    <div className="card-content">
                        <div className="columns">
                            <div className="column is-two-thirds">
                                <p className="title is-3">{this.props.book.name}</p>
                                <div className="columns">
                                    <div className="column is-two-thirds">
                                        <progress className="progress is-primary" value={this.props.book.fin_score} max="10"></progress>
                                    </div>
                                    <div className="column">
                                        <p className="title is-6">{this.props.book.fin_score}</p>
                                    </div>
                                </div>
                                <p className="title is-6">
                                    {this.props.book.author}/{this.props.book.year}/{this.props.book.press}
                                </p>
                            </div>
                            <div className="center_item column">
                                <div className="button is-danger">想读</div>
                            </div>
                        </div>
                    </div>


                </div>

            </div>
        );
    }
}

export {HotGds}