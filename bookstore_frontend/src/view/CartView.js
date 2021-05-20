import React,{useState} from 'react';
import ReactDOM from 'react-dom';
import * as orderService from '../services/orderService';
import * as cartService from '../services/cartService';

import SimpleNav from "../components/head/SimpleNav";
import {BookPurchase,Counter,OrderForm} from "../components/CheckOut";
import {history} from "../utils/history";
import {convert_book_info} from "../services/bookService";


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

/*
* standard form of single item
* cart_id is unique to every item in cart
* */
// const single_item1={
//     name:"平凡的世界",piece:1,money:25,chosen:false,cart_id:0
// }
//
// const single_item2={
//     name:"我的天才女友",piece:2,money:5,chosen:false,cart_id:1
// }
//
// const single_item3={
//     name: "高等数学", piece: 2, money: 22,chosen:false,cart_id:2
// }
/*
* standard final order form
* */
// const order_test= {
//     number: 123456,//number equals user_id and time
//     time: '2020/1/21/06/25',
//     purchase: [
//         {name: "平凡的世界", piece: 1, money: 25},
//         {name: "我的天才女友", piece: 2, money: 35}
//     ],
//     sum: 150
// };

/*
* standard cart form
* */
// const cart_test=[
//     single_item1,single_item2,single_item3
// ];

export default class CartView extends React.Component{
    constructor(props) {
        super(props);
        this.state={
            choose_all:false,
            cart_pool:[],
            sum:0,
            delete_butt:false,//whether show delete button
            search:false,//whether in search mod
            search_pool:[],//the item searched
            checkout:false,
            order:[]//final order
        };
    }

    //TODO:REDIRECT TO LOGIN AND MODIFY USERID TO VAR
    //TODO:HANDLE PIECE FROM BACKEND TO FRONTEND
    componentDidMount() {
        cartService.getCarts({},this.get_cart_content);
    }

    get_cart_content=(data)=>{
        console.log(data);
        let user_id=data['user_id'];
        let books=data['books'];
        let books_len=books.length;
        console.log("book len:"+books_len);
        let tmp_cart_id=0;
        let cart=[];
        for(let i=0 ;i<books_len;++i)
        {
            let cur_book=convert_book_info(books[i]);
            console.log("push book id:"+cur_book['id']);

            let cart_item={book_id:cur_book['id'],name: cur_book['name'], piece: 1 ,img:cur_book['img'],money: cur_book['price'],chosen:false,cart_id:tmp_cart_id};
            cart.push(cart_item);
            tmp_cart_id++;
        }
        this.setState({cart_pool:cart});

    }

    /*
    * calculate the sum money of all items checked
    * */
    check_out=()=>{

        let tmp_sum=0;
        let tmp_cart=this.state.cart_pool;
        tmp_cart.map((it)=>{
            if(it.chosen===true)
            {
                tmp_sum+=it.money*it.piece;
            }
        });
        this.setState((state)=>({
            sum:tmp_sum
        }))
    }

    //TODO
    //Never mutate this.state directly,
    //Treat this.state as if it were immutable.

    /*
    * reverse the state: choose all && not choose all
    * */
    on_choose_reverse=(props)=>{
        let reset_chosen=!this.state.choose_all;
        this.setState((state)=>({
            choose_all:reset_chosen
        }));

        let tmp_cart=this.state.cart_pool;
        tmp_cart.map((it,key)=>{
            it.chosen=reset_chosen;
        });

        this.setState({
            cart_pool:tmp_cart
        },()=>{this.check_out();});
    }

    /*
    * reverse the choose state of single item
    * */
    on_choose_reverse_item=(cart_id)=>{
        let tmp_cart=this.state.cart_pool;
        tmp_cart.map((it,key)=>{
            if(cart_id===it.cart_id)
            {
                let reset_chosen=!it.chosen;
                it.chosen = reset_chosen;
            }
        });

        this.setState({
            cart_pool:tmp_cart
        },()=>{this.check_out();});
    }

    /*
    * show or hide delete button
    * */
    on_reverse_delete_button=()=>{
        let reset_butt=!this.state.delete_butt;
        this.setState((state)=>({
            delete_butt:reset_butt
        }));
    }

    /*
    * remove item according to cart_id
    * */
    on_remove_item=(cart_id)=>{
        let tmp_cart=this.state.cart_pool;
        let filter_cart=tmp_cart.filter(item => item.cart_id !== cart_id);

        this.setState({
            cart_pool:filter_cart
        },()=>{this.check_out();});
    }

    /*
    * add the num of item by one
    * args: choice==0 increase, choose==1 decrease
    * */
    on_step_change_item=(choice,cart_id)=>{
        let tmp_cart=this.state.cart_pool;
        for(let it=0;it<tmp_cart.length;++it) {
            if (cart_id === tmp_cart[it].cart_id) {
                if (choice === 0)
                    tmp_cart[it].piece += 1;
                else if (choice === 1 && tmp_cart[it].piece > 0) {
                    tmp_cart[it].piece -= 1;
                    if (tmp_cart[it].piece === 0) {
                        tmp_cart.splice(it, 1);
                    }
                }
                break;
            }
        }

        this.setState({
            cart_pool:tmp_cart
        },()=>{this.check_out();});
    }

    /*
    * search item in cart
    * TODO:handle english string(convert to lowercase)
    * */
    on_search_item=(substr)=>{
        //substr is empty ,means not in search mod
        if(substr==="")
        {
            this.setState({
                search:false
            })
            return;
        }

        let tmp_cart=this.state.cart_pool;
        this.setState({
            search:true,
            search_pool:tmp_cart.filter((it)=>it.name.search(substr)!==-1)
        },()=>{this.check_out();});
    }




    /*
    * generate the order based on the chosen item
    * TODO: generate order number
    *  TODO:convey book_id is enough
    *   TODO:add pieces to database table
    * */
    on_checkout=()=>{

        let tmp_user_id=localStorage.getItem('user_id');
        let tmp_sum=this.state.sum;
        let tmp_cart=this.state.cart_pool;
        let tmp_items_array=[];

        for(let item=0;item<tmp_cart.length;++item)
        {
            if(tmp_cart[item].chosen===true)
            {
                let tmp_order_item={book_id:tmp_cart[item].book_id,price:tmp_cart[item].money};
                tmp_items_array.push(tmp_order_item);
            }
        }


        let submit_order={
            user_id:tmp_user_id,
            total_price:tmp_sum,
            orderItems:tmp_items_array
        };
        console.log("order to submit:"+submit_order);
        orderService.submitOrder(submit_order,this.show_order);
    }

    show_order=(data)=>{
        console.log(data);
        let order_id=data['order_id'];
        let timestamp=data['timestamp'];
        let price=data['total_price'];
        let items=data['orderItems'];
        let purchase_items=[];

        //name piece money
        for(let item=0;item<items.length;++item)
        {
            let cur_item=items[item];
            let purchase={name:cur_item['book_id'],money:cur_item['price']};
            purchase_items.push(purchase);
        }


        let tmp_order={
            number:order_id,
            time:timestamp,
            purchase:purchase_items,
            sum:price
        };
        this.setState({
            checkout:true,order:tmp_order
        });
    }




    /*
    * render  books in array
    * */
    render_book_purchase=(tmp_item_pool)=>{
        let bk_purchase=[];
        tmp_item_pool.map((it,index)=>{
            bk_purchase.push(
                <BookPurchase
                    item={it}
                    delete_butt={this.state.delete_butt}
                    on_remove_item={this.on_remove_item}
                    on_choose_reverse_item={this.on_choose_reverse_item}
                    on_step_change_item={this.on_step_change_item}
                />
            );
        })
        return bk_purchase;
    }

    render()
    {
        return(
            <div>
                <SimpleNav
                    on_reverse_delete_button={this.on_reverse_delete_button}
                    on_search_item={this.on_search_item}
                />

                {
                    (!this.state.search)?(
                        <div className="block">
                            <div className="main_area">
                                {this.render_book_purchase(this.state.cart_pool)}
                            </div>
                        </div>
                    ):(
                        <div className="block">
                            <div className="main_area">
                                {
                                    this.render_book_purchase(this.state.search_pool)
                                }
                            </div>
                        </div>
                    )
                }

                <Counter
                    sum={this.state.sum}
                    choose_all={this.choose_all}
                    on_choose_reverse={this.on_choose_reverse}
                    on_checkout={this.on_checkout}
                />

                {
                    (this.state.checkout)?(
                        <div className="main_area">
                            <OrderForm order={this.state.order}/>
                        </div>
                    ):null
                }

            </div>
        );
    }
}








