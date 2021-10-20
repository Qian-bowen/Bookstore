import React, {useState, useEffect} from 'react';
import {ManagePanel} from "../components/Manage";
import {RankingChart, TendencyChart, StatisticTable} from "../components/tool/Chart"
import {ManageHead} from "../components/head/ManageHead";
import {Avatar} from "../components/tool/Avatar";
import {EditTable} from "../components/table/EditTable";
import UserEditTable from "../components/table/UserEditTable";
import PlainTable from "../components/table/PlainTable";
import * as bookService from '../services/bookService';
import * as manageService from '../services/manageService';
import * as orderService from '../services/orderService';
import one from "../asserts/lufei.jpg";
import * as tableData from '../utils/tableData';
import * as searchEnum from '../components/constant/Type';

const avatar_info = {
    username: "guagua",
    id: "gggg",
    img_src: one
}

const chart_test = {
    title: '233',
    categories: ['平凡的世界', '悲惨世界', '三体', '他改变了中国'],
    yAxis_text: '销售量',
    series_name: '销售量',
    series_data: [1, 2, 5, 100],
}

const test = {
    title: {
        text: '热销榜'
    },
    xAxis: {
        categories: ['平凡的世界', '悲惨世界', '三体', '他改变了中国'],
    },
    yAxis: {
        title: {
            text: '销售量',
        }
    },
    series: [{
        name: '销售量',
        data: [1, 2, 5, 100],
    }]
}

/*
* standard table format
* */
const table_test = {
    headers: ["书籍名称", "ISBN", "销售量"],
    line: [
        ["平凡的世界", "1234", 200],
        ["我的天才女友", "1234", 100],
        ["CSAPP", "1234", 2000],
    ]
};

export default class AdminManageView extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            panel_display: false,
            statistic_display: false,
            user_display: false,
            book_display: false,
            order_display: false,
            display_mod: true,//true means show in graph,false in list

            book_table: null,
            user_table: null,
            order_table: null,

            stat_table: null,
        }
    }

    reset_panel = () => {
        this.setState({panel_display: !this.state.panel_display});
    }

    on_statistic = () => {
        this.setState({
            user_display: false,
            book_display: false,
            order_display: false,
            statistic_display: true
        });
    }

    on_user_manage = () => {
        this.get_user_data();
        this.setState({
            statistic_display: false,
            book_display: false,
            order_display: false,
            user_display: true
        });
    }

    on_book_manage = () => {
        this.get_table_data();
        this.setState({
            user_display: false,
            statistic_display: false,
            order_display: false,
            book_display: true,
        });
    }

    on_order_manage = () => {
        this.get_order_data();
        this.setState({
            user_display: false,
            statistic_display: false,
            book_display: false,
            order_display: true,
        });
    }


    /*
    * change the display mod
    * */
    on_display_mod = () => {
        let cur_mod = this.state.display_mod;
        this.setState({display_mod: !cur_mod});
    }

    //search items and update the table
    on_search_book_items = (search_text) => {
        let book_search = {
            "type": 0,//by name
            "name": search_text
        };
        const update_table = (data) => {
            let converted = tableData.convert_book_to_table(data);
            this.setState({book_table: converted});
        }
        manageService.searchBook(book_search, update_table);
    }

    on_search_order_by_book_name = (name) => {
        const update_table = (data) => {
            let converted = tableData.convert_order_to_table(data);
            this.setState({order_table: converted});
        }
        orderService.searchOrderByName(name, update_table);
    }

    on_search_by_time = (begin_time, end_time, type) => {
        if (searchEnum.searchType.order_search === type) {
            const update_table = (data) => {
                let converted = tableData.convert_order_to_table(data);
                this.setState({order_table: converted});
            }
            orderService.searchOrderByTime(begin_time, end_time, update_table);
        } else if (searchEnum.searchType.user_stat === type) {
            const update_stat_table = (data) => {
                console.log(data);
                let converted = tableData.convert_user_consume_to_table(data);
                this.setState({stat_table: converted});
            }
            manageService.statUserByTime(begin_time, end_time, 50, update_stat_table);
        } else if (searchEnum.searchType.book_stat === type) {
            const update_stat_table = (data) => {
                let converted = tableData.convert_book_sell_to_table(data);
                this.setState({stat_table: converted});
            }
            manageService.statBookByTime(begin_time, end_time, 50, update_stat_table);
        }
    }


    render_manage_area = () => {
        return (
            <div className={"block"}>
                <ManagePanel
                    on_statistic={this.on_statistic}
                    on_user_manage={this.on_user_manage}
                    on_display_mod={this.on_display_mod}
                    on_book_manage={this.on_book_manage}
                    on_order_manage={this.on_order_manage}
                    search_items={this.on_search_book_items}
                    search_order_by_book_name={this.on_search_order_by_book_name}
                    search_by_time={this.on_search_by_time}
                    add_refresh={this.get_table_data}
                />
            </div>
        );
    }

    //get book table data
    get_table_data = () => {
        const callback = (data) => {
            let converted = tableData.convert_book_to_table(data);
            console.log(converted);
            this.setState({book_table: converted});
        }
        const get_info = {"fetch_num": 50, "fetch_begin": 0};
        bookService.getBooks(get_info, callback);
    }


    get_user_data = () => {
        const callback = (data) => {
            let converted = tableData.convert_user_to_table(data.userJsonList);
            this.setState({user_table: converted});
        }
        manageService.getUsers(50, 0, callback);
    }

    get_order_data = () => {
        const callback = (data) => {
            console.log(data);
            let converted = tableData.convert_order_to_table(data);
            this.setState({order_table: converted});
        }
        orderService.getOrders(50, 0, callback);
    }

    render() {
        return (
            <div>
                <div className={"block"}>
                    <ManageHead avatar={<Avatar/>}
                                reset_panel={this.reset_panel}
                    />
                </div>

                <div className={"main_area"}>
                    {this.state.panel_display ?
                        (this.render_manage_area()) : null
                    }
                </div>

                {this.state.user_display ?
                    (
                        <section>
                            <UserEditTable table={this.state.user_table} change_table={this.get_user_data}/>
                        </section>
                    ) : null
                }

                {this.state.order_display ?
                    (
                        <section>
                            <PlainTable table={this.state.order_table}/>
                        </section>
                    ) : null
                }


                {this.state.book_display ?
                    (
                        <section>
                            <EditTable table={this.state.book_table} change_table={this.get_table_data}/>
                        </section>
                    ) : null
                }

                {this.state.statistic_display ?
                    (
                        <section>
                            <PlainTable table={this.state.stat_table}/>
                        </section>
                    ) : null
                }


            </div>

        );
    }
}