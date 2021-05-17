import React,{useState,useEffect} from 'react';
import {ManagePanel} from "../components/Manage";
import {RankingChart,TendencyChart,StatisticTable,EditTable} from "../components/tool/Chart"
import {ManageHead} from "../components/head/ManageHead";
import {Avatar} from "../components/tool/Avatar";
import * as bookService from '../services/bookService';
import one from "../asserts/lufei.jpg";

const avatar_info={
    username:"guagua",
    id:"gggg",
    img_src:one
}

const chart_test={
    title:'233',
    categories:['平凡的世界','悲惨世界','三体','他改变了中国'],
    yAxis_text:'销售量',
    series_name:'销售量',
    series_data: [1,2,5,100],
}

const test= {
    title: {
        text: '热销榜'
    },
    xAxis: {
        categories: ['平凡的世界','悲惨世界','三体','他改变了中国'],
    },
    yAxis: {
        title: {
            text: '销售量',
        }
    },
    series: [{
        name: '销售量',
        data: [1,2,5,100],
    }]
}

/*
* standard table format
* */
const table_test={
    tab_title:["书籍名称","ISBN","销售量"],
    line:[
        ["平凡的世界","1234",200],
        ["我的天才女友","1234",100],
        ["CSAPP","1234",2000],

    ]

};

export default class AdminManageView extends React.Component{
    constructor(props) {
        super(props);
        this.state={
            panel_display:false,
            statistic_display:false,
            user_display:false,
            book_display:false,
            display_mod:true,//true means show in graph,false in list

            edit_table:{
                ptr:0,//beginning line to database
                step:10,//pull how many line each time
                table:[],
            }
        }
    }

    reset_panel=()=>{
        this.setState({panel_display:!this.state.panel_display});
    }

    on_statistic=()=>{
        this.setState({
            user_display:false,
            book_display:false,
            statistic_display:true
        });
    }

    on_user_manage=()=>{
        this.setState({
            statistic_display:false,
            book_display:false,
            user_display:true
        });
    }

    on_book_manage=()=>{
        this.setState({
            user_display:false,
            statistic_display:false,
            book_display:true,
        });
    }

    /*
    * change the display mod
    * */
    on_display_mod=()=>{
        let cur_mod=this.state.display_mod;
        this.setState({display_mod:!cur_mod});
    }

    render_manage_area=()=>{
        return(
            <div className={"block"}>
                <ManagePanel
                    on_statistic={this.on_statistic}
                    on_user_manage={this.on_user_manage}
                    on_display_mod={this.on_display_mod}
                    on_book_manage={this.on_book_manage}
                />
            </div>
        );
    }

    render_chart_area=(title)=>{
        return(
            <section className={"section"}>
                <h1 className="title">{title}</h1>
                <div className={"columns"}>
                    <div className={"column"}>
                        <div className={"card"}>
                            <RankingChart chart={test}/>
                        </div>
                    </div>
                    <div className={"column"}>
                        <div className={"card"}>
                            <TendencyChart/>
                        </div>
                    </div>
                </div>
            </section>
        );
    }

    render_table_area=(title,table)=>{
        return(
            <section className={"section"}>
                <h1 className="title">{title}</h1>
                <div className={"columns"}>
                    <div className={"column"}>
                        <div className={"card"}>
                            <StatisticTable
                                table={table}
                            />
                        </div>
                    </div>
                    {/*<div className={"column"}>*/}
                    {/*    <div className={"card"}>*/}
                    {/*        <StatisticTable*/}
                    {/*            table={table}*/}
                    {/*        />*/}
                    {/*    </div>*/}
                    {/*</div>*/}
                </div>
            </section>
        );
    }

    /*
    * pull database book info to front end
    * */
    get_edit_table_info=(fetch_num,fetch_begin)=>{
        const callback=(data)=>{
            let converted=this.convert_book_to_table(data);

            console.log(converted);
            this.setState({edit_table:{...this.state.edit_table,table:converted}});
        }
        const get_info={"fetch_num":fetch_num,"fetch_begin":fetch_begin};
        bookService.getBooks(get_info,callback);
        return true;
    }

    convert_book_to_table=(data)=>{

        let table={
            tab_title:["书籍名称","ISBN","售价","作者"],
            line:[]
        };

        let num=data.length;
        for(let i=0;i<num;++i)
        {
            let line=[];
            let cur_book=bookService.convert_book_info(data[i]);
            line.push(cur_book.name,cur_book.IBSN,cur_book.price,cur_book.author);
            table.line.push(line);
        }

        return table;
    }

    //TODO:when come to last page,keep click next page,ptr will still increase
    get_edit_table_next=()=>{
        console.log("call next");
        let step=this.state.edit_table.step;
        let cur_ptr=this.state.edit_table.ptr;

        this.get_edit_table_info(step,cur_ptr);

        let next_ptr=this.state.edit_table.ptr+step;
        this.setState({edit_table:{...this.state.edit_table,ptr:next_ptr}});

    }

    get_edit_table_previous=()=>{
        let step=this.state.edit_table.step;
        let cur_ptr=this.state.edit_table.ptr
        let next_ptr=this.state.edit_table.ptr-step;
        if(next_ptr>=0)
        {
            this.get_edit_table_info(step,next_ptr);
            this.setState({edit_table:{...this.state.edit_table,ptr:next_ptr}});
        }
    }

    render_edit_table_area=()=>{
        return(
            <section className={"section"}>
                <EditTable table={this.state.edit_table.table}/>
                <div className="buttons">
                    <button className="button is-primary" onClick={this.get_edit_table_previous}>上一页</button>
                    <button className="button is-primary" onClick={this.get_edit_table_next}>下一页</button>
                </div>
            </section>
        );
    }

    render()
    {
        return(
            <div>
                <div className={"block"}>
                    <ManageHead avatar={<Avatar user={avatar_info} />}
                        reset_panel={this.reset_panel}
                    />
                </div>

                <div className={"main_area"}>
                    {this.state.panel_display ?
                        ( this.render_manage_area()) :null
                    }
                </div>

                {this.state.statistic_display&&this.state.display_mod ?
                    (this.render_chart_area("书籍销售量统计")):null
                }

                {this.state.user_display&&this.state.display_mod ?
                    (this.render_chart_area("用户消费统计")):null
                }

                {this.state.statistic_display&&!this.state.display_mod ?
                    (this.render_table_area("书籍销售量统计",table_test)):null
                }

                {this.state.user_display&&!this.state.display_mod ?
                    (this.render_table_area("用户消费统计",table_test)):null
                }

                {this.state.book_display ?
                    (this.render_edit_table_area()):null
                }


            </div>

        );
    }
}