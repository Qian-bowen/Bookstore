import React from 'react';

class BookPurchase extends React.Component{
    constructor(props) {
        super(props);
    }

    handle_choose_reverse_item=(e)=>
    {
        this.props.on_choose_reverse_item(this.props.item.cart_id);
    }

    handle_remove_item=(e)=>{
        this.props.on_remove_item(this.props.item.cart_id);
    }

    handle_step_change_item=(choice)=>{
        this.props.on_step_change_item(choice,this.props.item.cart_id);
    }

    render()
    {
        const is_chosen=this.props.choose_all;
        return(
            <div className="card">
                <div className="columns is-1">
                    <div className="column is-1">
                        <label className="radio">
                            <input
                                type="checkbox"
                                checked={this.props.item.chosen}
                                onChange={this.handle_choose_reverse_item}
                            />
                        </label>
                    </div>

                    <div className="column">
                        <div className="section">
                            <div className="columns">
                                <div className="center_item column is-half">
                                    <div className="center_item card-image">
                                        <figure className="image is-4by3">
                                            <img src={this.props.item.img}
                                                 alt="image missing" />
                                        </figure>
                                    </div>
                                </div>
                                <div className="column is-half">
                                    <p className="title is-3">{this.props.item.name}</p>
                                    <div className="columns">
                                        <div className="column is-two-thirds">
                                            <p className="title is-4 is-danger">￥{this.props.item.money}</p>
                                        </div>
                                        <div className="column">
                                            <div className="button is-warning">x{this.props.item.piece}</div>
                                        </div>
                                    </div>
                                    {this.props.delete_butt?
                                        (
                                            <div className={"buttons has-addons"}>
                                                <div className={"button is-small is-link is-rounded"}
                                                     onClick={()=>this.handle_step_change_item(0)}
                                                >+1件</div>

                                                <div className={"button is-small is-danger is-rounded"}
                                                     onClick={this.handle_remove_item}
                                                >删除</div>

                                                <div className={"button is-small is-link is-rounded"}
                                                     onClick={()=>this.handle_step_change_item(1)}
                                                >-1件</div>
                                            </div>

                                        ):null
                                    }
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

class Counter extends React.Component{
    constructor(props) {
        super(props);
    }

    handle_choose_reverse=(e)=>{
        console.log("choose: "+e.target.checked);
        this.props.on_choose_reverse(e.target.checked);
    }

    handle_checkout=()=>{
        this.props.on_checkout();
    }

    render()
    {
        return(
            <div class="main_area">
                <nav class="box">
                    <div class="level-left">

                    </div>

                    <div class="level-right">
                        <p class="level-item">
                            <label className="checkbox">
                                <input
                                    type="checkbox"
                                    onClick={this.handle_choose_reverse}
                                />
                                全选
                            </label>
                        </p>
                        <p class="level-item"><a>合计：￥{this.props.sum}</a></p>
                        <p class="level-item">
                            <a class="button is-success"
                                onClick={this.handle_checkout}
                            >结算</a>
                        </p>
                    </div>
                </nav>
            </div>
        );
    }
}

class OrderForm extends React.Component{
    constructor(props) {
        super(props);
    }

    render()
    {
        return(
            <div className={"box"}>
                <div className={"table is-striped is-narrow is-hoverable is-fullwidth"}>
                    <thead>
                    <tr>
                        <th>订单信息</th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <th>订单编号：</th>
                        <th>{this.props.order.number}</th>
                    </tr>
                    <tr>
                        <th>商品ID：</th>
                        <th>
                            {
                                this.props.order.purchase.map((it)=>{
                                    return <span>{it.name}/</span>;
                                })
                            }
                        </th>
                    </tr>
                    <tr>
                        <th>交易时间：</th>
                        <th>{this.props.order.time}</th>
                    </tr>
                    <tr>
                        <th>价格：</th>
                        <th>￥{this.props.order.sum}</th>
                    </tr>
                    </tbody>
                </div>
            </div>

        );
    }
}


export{BookPurchase,Counter,OrderForm}