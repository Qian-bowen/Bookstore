import React from 'react';
import {Selector} from "./tool/Choose";
import {RankingChart,TendencyChart} from './tool/Chart';

class AddBook extends React.Component{
    constructor(props) {
        super(props);
    }

    render()
    {
        return(
            <div>
                <div className={"block"}>
                    <input className="input is-rounded" type="text" placeholder="请输入书名"/>
                    <input className="input is-rounded" type="text" placeholder="ISBN"/>
                    <input className="input is-rounded" type="text" placeholder="价格（￥）"/>
                    <input className="input is-rounded" type="text" placeholder="作者"/>
                    <input className="input is-rounded" type="text" placeholder="简介"/>
                </div>
                <div className={"block"}>
                    <div className={"button is-danger"}>提交</div>
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
        }
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

    handle_edit_area=()=>{
        let cur_state=this.state.display_edit_area;
        this.setState({display_edit_area:!cur_state});
    }

    handle_handle_activity=()=>{

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
                                <div className={"button is-rounded is-link"}>查找</div>
                                <div className={"button is-rounded is-warning"}>编辑</div>
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
                            onClick={this.handle_statistic}
                        >数据统计</a>
                        <a className="is-active"
                           onClick={this.handle_activity}
                        >活动发布</a>
                    </p>
                    <div className="panel-block">
                        <input className="input is-success" type="text" placeholder="开始查找" />
                    </div>

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
