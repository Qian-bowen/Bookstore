import React from 'react';
import Book from './Book';
import * as bookService from '../services/bookService';

import img1 from '../asserts/300.jfif'

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

export default class BookFrame extends React.Component {
    constructor(props) {
        super(props);
        this.state={books:[]};
    }

    componentDidMount()
    {
        const callback=(data)=>{
            this.setState({books:data});
        }
        const get_info={"fetch_num":20,"fetch_begin":0};
        bookService.getBooks(get_info,callback);
    }




    create_book_single(book_info) {

        return (
            <div className={"block"}>
                <Book book={book_info}
                      on_show_detail={this.on_show_detail}
                />
            </div>
        );
    }

    create_store(col_idx,col_num) {
        let box=[];
        let books=this.state.books;
        let books_num=books.length;
        for(let i=0;i<books_num;++i)
        {
            if((i%col_num)===col_idx) {
                let front_book = bookService.convert_book_info(books[i]);
                box.push(this.create_book_single(front_book));
            }
        }
        return(
            box
        );
    }

    render() {
        return (
            <div className={"columns"}>
                <div className={"column"}>
                    {this.create_store(0,4)}
                </div>
                <div className={"column"}>
                    {this.create_store(1,4)}
                </div>
                <div className={"column"}>
                    {this.create_store(2,4)}
                </div>
                <div className={"column"}>
                    {this.create_store(3,4)}
                </div>
            </div>
        );
    }
}
