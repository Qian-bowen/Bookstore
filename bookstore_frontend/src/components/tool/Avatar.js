import React from "react";
/*
*  Avatar
*  args: basic info of user
*  description: render username\user id\image of user
* */
class Avatar extends React.Component{
    constructor(props) {
        super(props);
    }

    render()
    {
        return(
            <div className="media">
                <div className="media-left">
                    <figure className="image is-48x48">
                        <img className="is-rounded" src={this.props.user.img_src}
                             alt="Placeholder image" />
                    </figure>
                </div>

                <div className="media-content">
                    <p className="title is-4">{this.props.user.username}</p>
                    <p className="subtitle is-6">@{this.props.user.id}</p>
                </div>
            </div>
        );
    }
}

export {Avatar}