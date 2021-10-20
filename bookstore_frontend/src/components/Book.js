import React from 'react';
import BookTag from "./tool/Tag";
import {Link} from "react-router-dom";


export default class Book extends React.Component {
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <Link to={{
                pathname: "/bookdetail",
                search: '?id=' + this.props.book.id
            }}
                  target={"_blank"}
            >
                <div className="card">
                    <div className="card-image">
                        <figure className="image">
                            <img className={"book_img"} src={`data:image/jpeg;base64,${this.props.book.img}`}
                                 alt="image missing"/>
                        </figure>
                    </div>

                    <div className="card-content">
                        <div className={"title is-4"}>{this.props.book.name}</div>
                        <div className={"title is-5"}>￥{this.props.book.price}</div>
                        {this.props.book.brief_intro}
                        <BookTag tag={this.props.book.tag}/>
                    </div>
                </div>
            </Link>
        );
    }
}