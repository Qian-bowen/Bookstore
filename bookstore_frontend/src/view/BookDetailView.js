import React from 'react';
import BookDetail from "../components/BookDetail";
import {getBook} from "../services/bookService";
import * as bookService from '../services/bookService';

//standard book info format
// const book_info={
//     name:"我的天才女友",
//     price:109,
//     tag:["爱情","亲情"],
//     img:img1,
//     fin_score:8.2,
//     score:[10,50,15,15,10],
//     IBSN:"1234567",
//     author:"埃莱娜·费兰特",
//     press:"四川人民出版社",
//     year:"2021",
//     brief_intro: "那不勒斯三部曲",
//     spec_intro:"《平凡的世界》是中国作家路遥创作的一部全景式地表现中国当代城乡社会生活的百万字长篇小说。全书共三部。1986年12月首次出版。\n" +
//         "该书以中国70年代中期到80年代中期十年间为背景，通过复杂的矛盾纠葛，以孙少安和孙少平两兄弟为中心。"
// }

export default class BookDetailView extends React.Component {
    constructor(props) {
        super(props);
        this.state = {book_info: null};
    }

    componentDidMount() {
        console.log("bookid call");
        const query = this.props.location.search;
        const arr = query.split('&');
        const bookId = arr[0].substr(4);
        console.log("bookid:" + bookId);
        const callback = (data) => {
            let convert_book = bookService.convert_book_info(data);
            console.log(convert_book);
            this.setState({book_info: convert_book})
        }
        getBook(bookId, callback);
    }

    render() {
        return (
            <div className={"main_area"}>
                <BookDetail book={this.state.book_info}/>
            </div>
        );
    }
}