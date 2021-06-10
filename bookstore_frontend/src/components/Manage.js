import React from 'react';
import {Selector} from "./tool/Choose";
import {RankingChart,TendencyChart} from './tool/Chart';
import * as manageService from '../services/manageService';

class AddBook extends React.Component{
    constructor(props) {
        super(props);
        this.state = {
            name:null,
            isbn:null,
            type:null,
            price:null,
            inventory:null,
            author:null,
            description:null
        }
    }

    handle_name=(e)=>{
        this.setState({name:e.target.value});
    }

    handle_isbn=(e)=>{
        this.setState({isbn:e.target.value});
    }

    handle_type=(e)=>{
        this.setState({type:e.target.value});
    }

    handle_price=(e)=>{
        this.setState({price:e.target.value});
    }

    handle_author=(e)=>{
        this.setState({author:e.target.value});
    }

    handle_inventory=(e)=>{
        this.setState({inventory:e.target.value});
    }

    handle_description=(e)=>{
        this.setState({description:e.target.value});
    }

    alert_backend_msg=(msg)=>{
        alert(msg.msg);
    }

    submit_book=()=>{
        let book_json={
            "bookId":null,
            "isbn":this.state.isbn,
            "name":this.state.name,
            "type":this.state.type,
            "author":this.state.author,
            "price":this.state.price,
            "description":this.state.description,
            "inventory":this.state.inventory,
            "image":null
        };
        manageService.addBook(book_json,this.alert_backend_msg);
    }

    render()
    {
        return(
            <div>
                <div className={"block"}>
                    <input className="input is-rounded" name="name" type="text" placeholder="请输入书名" onChange={this.handle_name}/>
                    <input className="input is-rounded" name="isbn" type="text" placeholder="ISBN" onChange={this.handle_isbn}/>
                    <input className="input is-rounded" name="type" type="text" placeholder="类型" onChange={this.handle_type}/>
                    <input className="input is-rounded" name="price" type="text" placeholder="价格（￥）" onChange={this.handle_price}/>
                    <input className="input is-rounded" name="inventory" type="text" placeholder="库存" onChange={this.handle_inventory}/>
                    <input className="input is-rounded" name="author" type="text" placeholder="作者" onChange={this.handle_author}/>
                    <input className="input is-rounded" name="description" type="text" placeholder="简介" onChange={this.handle_description}/>
                </div>
                <div className={"block"}>
                    <div className={"button is-danger"} onClick={this.submit_book}>提交</div>
                </div>
            </div>
        );
    }
}

class ManagePanel extends React.Component{
    constructor(props) {
        super(props);
        this.state={
            display_mod:true,
            is_edit_mode:false,//can edit book or not
            display_edit_area:false,

            search_text:null,
            search_text_second:null,

            is_advance_search:false,
        }
    }

    handle_search=(e)=>{
        this.setState({search_text:e.target.value});
    }

    handle_search_second=(e)=>{
        this.setState({search_text_second:e.target.value});
    }

    submit_search=()=>{
        this.props.search_items(this.state.search_text);
    }

    submit_search_book_name=()=>{
        this.props.search_order_by_book_name(this.state.search_text);
    }

    submit_search_by_time=()=>{
        this.props.search_by_time(this.state.search_text,this.state.search_text_second);
    }



    change_search_mode=()=>{
        let mode=this.state.is_advance_search;
        this.setState({is_advance_search:!mode});
    }


    handle_display_mod=()=>{
        let cur_mod=this.state.display_mod;
        this.setState({display_mod:!cur_mod},()=>
        {
            this.props.on_display_mod();
        });
    }

    handle_statistic=()=>{
        this.setState({is_edit_mode:false},()=>{
            this.props.on_statistic();
        });
    }

    handle_user_manage=()=>{
        this.setState({is_edit_mode:false},()=>{
            this.props.on_user_manage();
        });
    }

    handle_book_manage=()=>{
        this.setState({is_edit_mode:true},()=>{
            this.props.on_book_manage();
        });
    }

    handle_order_manage=()=>{
        this.setState({is_edit_mode:true},()=>{
            this.props.on_order_manage();
        });
    }

    handle_edit_area=()=>{
        let cur_state=this.state.display_edit_area;
        this.setState({display_edit_area:!cur_state});
    }

    render_advance_search=()=>{
        return(
            <div>
                <div className="panel-block">
                    <input className="input is-success" type="text" placeholder="请输入书名" onChange={this.handle_search}/>
                </div>
                <div className="panel-block">
                    <button className="button is-primary is-inverted is-rounded" onClick={this.submit_search_book_name}>通过书名搜索</button>
                </div>
                <div className="panel-block">
                    <input className="input is-success" type="text" placeholder="请输入起始时间，如：2021-05-25 15:30:55" onChange={this.handle_search}/>
                </div>
                <div className="panel-block">
                    <input className="input is-success" type="text" placeholder="请输入终止时间，如：2021-05-28 16:37:55" onChange={this.handle_search_second}/>
                </div>
                <div className="panel-block">
                    <button className="button is-primary is-inverted is-rounded" onClick={this.submit_search_by_time}>通过时间搜索</button>
                </div>
            </div>

        );
    }


    render_panel_button=()=>{
        return(
            <div>
                {
                    !this.state.is_edit_mode?
                        (
                            <div className={"button is-rounded is-danger"}
                                 onClick={this.handle_display_mod}>
                                {
                                    this.state.display_mod?
                                        (<span>以表显示</span>):
                                        (<span>以图显示</span>)
                                }
                            </div>
                        ):null
                }

                {
                    this.state.is_edit_mode?
                        (
                            <div className={"buttons has-addons"}>
                                <div className={"button is-rounded is-primary"}
                                    onClick={this.handle_edit_area}
                                >增加</div>
                                <div className={"button is-rounded is-link"} onClick={this.submit_search}>查找</div>
                                <div className={"button is-rounded is-warning"} onClick={this.change_search_mode}>搜索模式</div>
                            </div>
                        ):null
                }
            </div>
        );
    }


    render()
    {
        return(
            <div>
                <article className="panel is-success">
                    <p className="panel-heading">
                        管理面板
                    </p>
                    <p className="panel-tabs">
                        <a className="is-active"
                            onClick={this.handle_book_manage}
                        >书籍管理</a>
                        <a className="is-active"
                            onClick={this.handle_user_manage}
                        >用户管理</a>
                        <a className="is-active"
                           onClick={this.handle_order_manage}
                        >订单管理</a>
                        <a className="is-active"
                            onClick={this.handle_statistic}
                        >数据统计</a>
                        <a className="is-active"
                           onClick={this.handle_activity}
                        >活动发布</a>
                    </p>

                    {
                        (this.state.is_advance_search)?
                            this.render_advance_search()
                            :(
                                <div className="panel-block">
                                    <input className="input is-success" type="text" placeholder="开始查找" onChange={this.handle_search}/>
                                </div>
                            )
                    }


                    <div className="panel-block">
                        {this.render_panel_button()}
                    </div>

                    <div className="panel-block">
                        {
                            this.state.display_edit_area?
                                (<AddBook/>):null
                        }
                    </div>

                </article>
            </div>
        );
    }
}

class ManageAdmin extends React.Component{
    constructor(props) {
        super(props);
    }

    render()
    {
        const book_manage=["浏览书目","查找书籍","增减书籍","编辑书籍"];
        return(
          <div>
            <RankingChart />
            <TendencyChart/>
          </div>
        );
    }
}

class ManageUser extends React.Component{
    constructor(props) {
        super(props);
    }

    render()
    {
        const book_manage=["浏览书目","查找书籍","增减书籍","编辑书籍"];
        return(
            <div>

            </div>
        );
    }
}

class StatisticAdmin extends React.Component{
    constructor(props) {
        super(props);
    }

    render()
    {
        return(
            <div>

            </div>
        );
    }
}

class StatisticUser extends React.Component{
    constructor(props) {
        super(props);
    }

    render()
    {
        return(
            <div>

            </div>
        );
    }
}

export {ManagePanel,ManageAdmin,ManageUser,StatisticAdmin,StatisticUser}
