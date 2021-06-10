import React from 'react';

import * as manageService from '../../services/manageService'
import {getBook} from "../../services/bookService";
import TableCell from "./TableCell";

class UserTableRow extends React.Component{
    constructor(props) {
        super(props);
        this.state = {
            line:this.props.line,
            line_idx:this.props.line_idx,
        }
    }

    static getDerivedStateFromProps(props, state){
        return{
            line:props.line,
            line_idx:props.line_idx
        };
    }

    forbid_user=()=>{
        let user_id=this.state.line[0];
        manageService.forbidUser(user_id);
        this.props.forbid_user(user_id);
    }

    permit_user=()=>{
        let user_id=this.state.line[0];
        this.props.permit_user(user_id);
    }


    render()
    {
        return(
            (!this.state.is_delete)?(
                <tr>
                    {this.props.line.map((col,col_idx)=>{
                        return (<TableCell text={col} col={col_idx}/>)})
                    }
                    <th>
                        <div className={"button is-small is-primary"} onClick={this.forbid_user}>禁用</div>
                        <div className={"button is-small is-danger"} onClick={this.permit_user}>解禁</div>
                    </th>
                </tr>
            ):null
        );
    }
}

export default class UserEditTable extends React.Component{
    constructor(props) {
        super(props);
        this.state={
            headers:null,
            line:null,
        }
    }

    change_table_callback=(msg)=>{
        this.props.change_table();
        alert(msg.msg);
    }

    on_forbid_user=(user_id)=>{
        manageService.forbidUser(user_id,this.change_table_callback);
    }

    on_permit_user=(user_id)=>{
        manageService.permitUser(user_id,this.change_table_callback);
    }

    render_headers=()=>{
        let headers=this.props.table.headers;
        let headers_table=[];
        headers.map((it)=>{
            headers_table.push(<th>{it}</th>);
        });
        return headers_table;
    }


    render()
    {
        const {table}=this.props;
        if(table==null) return null;
        return(
            <table className={"table is-striped is-bordered is-fullwidth"}>
                <thead>
                <tr>
                    {this.render_headers()}
                    <th></th>
                </tr>
                </thead>
                <tbody>
                {this.props.table.line.map((li,li_idx)=>{
                    return(
                        <UserTableRow key={li_idx} line={li} line_idx={li_idx} col_num={li.length} forbid_user={this.on_forbid_user} permit_user={this.on_permit_user}/>
                    );})
                }
                </tbody>
            </table>
        );
    }
}
