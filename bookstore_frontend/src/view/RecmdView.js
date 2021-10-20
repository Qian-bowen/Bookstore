import React from 'react';
import '../css/BookFrame.css';

import {CommNav} from "../components/head/SimpleNav";
import BookFrame from "../components/BookFrame";
import '../services/manageService';
import {searchBook} from "../services/manageService";
import * as bookService from "../services/bookService";
import * as Type from "../components/constant/Type";


export default class RecmdView extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            books: [],
            searched_books: [],
            fetch_num: 8,
            fetch_begin: 0,
            is_search_books: false,
        };
    }

    componentDidMount() {
        window.addEventListener('scroll', this.handleScroll);
        this.fetch_books();
    }

    componentWillUnmount() {
        window.removeEventListener('scroll', this.handleScroll);
    }

    handleScroll = () => {
        const css_offset = 5;
        // console.log("first:"+(window.innerHeight + window.scrollY));
        // console.log("second:"+document.body.offsetHeight);
        if ((window.innerHeight + window.scrollY + css_offset) >= document.body.offsetHeight) {
            //console.log("you're at the bottom of the page");
            this.fetch_books();
        }
    }

    fetch_books = () => {
        let cur_book = this.state.books;
        const callback = (data) => {
            console.log("length:" + data.length);
            if (data.length === 0) {
                alert("您已浏览到底，没有更多商品");
                return;
            }
            console.log("fetch:" + data);
            this.setState({books: cur_book.concat(data)});
        }
        let begin = this.state.fetch_begin;
        let num = this.state.fetch_num;

        const get_info = {"fetch_num": num, "fetch_begin": begin};
        bookService.getBooks(get_info, callback);
        this.setState({fetch_begin: (begin + num)});
    }

    search_items = (book_name) => {
        if (book_name === "") {
            this.setState({is_search_books: false});
            return;
        }
        const callback = (data) => {
            this.setState({searched_books: data, is_search_books: true});
        }
        let item = {"type": Type.searchByType.by_name, "name": book_name};
        searchBook(item, callback);
    }

    render_search_books = () => {
        let searched_books = this.state.searched_books;
        console.log("search:" + searched_books);
        return (
            <div>
                {
                    (searched_books.length === 0) ?
                        (
                            <div>
                                <article className="message is-primary">
                                    <div className="message-header">
                                        <p>搜索不到相关内容</p>
                                    </div>
                                </article>
                                <article className="message is-warning">
                                    <div className="message-header">
                                        <p>您可能还喜欢</p>
                                    </div>
                                </article>
                            </div>
                        ) : (
                            <div>
                                <BookFrame books={searched_books}/>
                                <article className="message is-warning">
                                    <div className="message-header">
                                        <p>您可能还喜欢</p>
                                    </div>
                                </article>
                            </div>


                        )
                }

            </div>
        );
    }


    render() {
        return (
            <div>
                <CommNav on_search_item={this.search_items}/>
                {
                    (this.state.is_search_books) ?
                        (
                            this.render_search_books()
                        ) : null
                }
                <BookFrame books={this.state.books}/>
            </div>
        );
    }
}
