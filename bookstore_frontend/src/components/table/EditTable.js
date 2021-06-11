import React from 'react';

import * as bookService from '../../services/bookService';
import * as manageService from '../../services/manageService'
import {getBook} from "../../services/bookService";
import {delBook} from "../../services/manageService";

import EditableCell from "./EditableCell"

class TableRow extends React.Component{
    constructor(props) {
        super(props);
        this.state = {
            line:this.props.line,
            line_idx:this.props.line_idx,
            cell_mode:new Array(this.props.col_num).fill(false),
        }
    }

    static getDerivedStateFromProps(props, state){
        return{
            line:props.line,
            line_idx:props.line_idx
        };
    }


    submit_change=()=>{
        this.setState({cell_mode:false});
        this.setState({cell_mode:new Array(this.props.col_num).fill(false)});
        this.props.handle_submit(this.state.line);
    }

    delete_record=()=>{
        this.props.delete_record(this.state.line);
    }

    handle_change_to_input=(col_idx)=>{
        let mode_array=this.state.cell_mode;
        mode_array[col_idx]=true;
        this.setState({cell_mode:mode_array});
    }

    handle_change_modify=(col_idx,text)=>{
        let line=this.state.line;
        line[col_idx]=text;
        this.setState({line:line});
    }

    render()
    {
        return(
            <tr>
                {this.props.line.map((col,col_idx)=>{
                    return (
                        (col_idx!=0) ?(
                            <EditableCell input={this.state.cell_mode[col_idx]} text={col} col={col_idx}
                                       change_to_input={this.handle_change_to_input}
                                       modify_text={this.handle_change_modify}/>
                        ):(
                            <EditableCell input={false} text={col} col={col_idx}
                                       change_to_input={this.handle_change_to_input}
                                       modify_text={this.handle_change_modify}/>
                        )
                    );
                })}
                <th>
                    <div className={"button is-small is-primary"} onClick={this.submit_change}>提交</div>
                    <div className={"button is-small is-danger"} onClick={this.delete_record}>删除</div>
                </th>
            </tr>
        );
    }
}

/*
* ordinary table
* */
class EditTable extends React.Component{
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

    submit_modify_book=(line)=>{
        let tmp_line=line;
        let bookId=tmp_line[0];
        const callback=(book)=>{
            let modified_book = manageService.impl_backend_book_modify(book,tmp_line);
            manageService.modifyBook(modified_book,this.change_table_callback);
        }
        getBook(bookId,callback);
    }

    handle_delete_record=(line)=>{
        let bookId=line[0];
        manageService.delBook(bookId,this.change_table_callback);
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
                        <TableRow key={li_idx} line={li} line_idx={li_idx} col_num={li.length} handle_submit={this.submit_modify_book} delete_record={this.handle_delete_record}/>
                    );})
                }
                </tbody>
            </table>
        );
    }
}

export {EditTable};

