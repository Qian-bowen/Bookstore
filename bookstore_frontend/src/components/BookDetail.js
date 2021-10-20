import React from 'react';
import {Avatar} from "./tool/Avatar";
import BookTag from "./tool/Tag";
import {addCart} from "../services/cartService";

class BookCard extends React.Component {

    add_to_cart = () => {
        let book_id = this.props.book.id;
        let user_str = localStorage.getItem('user');
        let user = JSON.parse(user_str);
        console.log("add to cart user_id:" + user['userID'] + " book id:" + book_id);
        let obj = {"user_id": user['userID'], "book_id": book_id, "piece": 1};
        addCart(obj);
    }

    render() {
        return (
            <div class="card">
                <div class="card-image">
                    <figure className={"image is-4by3"}>
                        <img className={"book_img"} src={`data:image/jpeg;base64,${this.props.book.img}`}
                             alt="image missing"/>
                    </figure>
                </div>

                <div class="card-content">
                    <p class="title is-4">￥{this.props.book.price}</p>
                </div>

                <p class="buttons is-rounded">
                    <button class="button is-primary" onClick={this.add_to_cart}>加入购物车</button>
                    <button class="button is-info">立即购买</button>
                </p>

            </div>
        );
    }
}


/*
* BookScoreBoard
* arg: array of percentage that represents score
* */
class BookScoreBoard extends React.Component {
    constructor(props) {
        super(props);
    }

    make_score = () => {
        let score_box = [];
        let tmp = this.props.score.map((item, index) =>
            (
                <progress className="progress is-primary" value={item} max="100" key={index}></progress>
            ));
        score_box.push(tmp);
        return score_box;
    }

    render() {
        return (
            <div>
                {this.make_score()}
            </div>
        );
    }
}


const userinfo = {
    username: "股呱",
    id: "23der",
    img_src: "https://bulma.io/images/placeholders/96x96.png"
};


/*
*  CommentContent
*  args: comment content of user
*  description:
* */
class CommentContent extends React.Component {
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <article className="message">
                <div className="message-body">
                    {this.props.content}
                </div>
            </article>
        );
    }
}

/*
*  BookComment
*  description: combine avatar with corresponding comment
* */
class BookComment extends React.Component {
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <div className={"box"}>
                <Avatar user={userinfo}/>
                <CommentContent content={this.props.content}/>
            </div>
        );
    }
}

class AddComment extends React.Component {
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <div className={"box"}>

            </div>
        );
    }
}

class BookBriefIntro extends React.Component {
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <div className="box">
                <p className="title is-4">简介</p>
                <div className="content">
                    {this.props.book.spec_intro}
                </div>
            </div>
        );
    }
}


export default class BookDetail extends React.Component {
    constructor(props) {
        super(props);
    }

    render() {
        const {book} = this.props;
        if (book == null) {
            return null;
        }

        return (
            <div>
                <div className="block">
                    <BookCard book={book}/>
                </div>
                <div className={"box"}>
                    <p className="title is-4">相关分类</p>
                    <BookTag tag={book.tag}/>
                </div>

                <BookBriefIntro book={book}/>

                <div className={"box"}>
                    <p className="title is-4">书籍评分</p>
                    <div className="columns">
                        <div className="column is-one-fifth">
                            <p className="title is-2">{book.fin_score}</p>
                        </div>
                        <div className="column">
                            <div className="column is-four-fifths">
                                <BookScoreBoard score={book.score}/>
                            </div>
                        </div>
                    </div>
                </div>

                <div className="box">
                    <p className="title is-4">读者评价</p>
                    <BookComment/>
                    <BookComment/>
                </div>
            </div>
        );
    }
}