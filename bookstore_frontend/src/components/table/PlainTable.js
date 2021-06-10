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

    render()
    {
        return(
            <tr>
                {this.props.line.map((col,col_idx)=>{
                    return (<TableCell text={col} col={col_idx}/>)})
                }
            </tr>
        );
    }
}

export default class PlainTable extends React.Component{
    constructor(props) {
        super(props);
        this.state={
            headers:null,
            line:null,
        }
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
                </tr>
                </thead>
                <tbody>
                {this.props.table.line.map((li,li_idx)=>{
                    return(
                        <UserTableRow key={li_idx} line={li} line_idx={li_idx} col_num={li.length}/>
                    );})
                }
                </tbody>
            </table>
        );
    }
}