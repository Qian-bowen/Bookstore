import React from 'react';

import Highcharts from 'highcharts/highstock'
import HighchartsReact from 'highcharts-react-official'

/*
* ranking of one type of data
* */


class RankingChart extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            chartOptions: this.props.chart,
            hoverData: null,
        };
    }

    render() {
        const {chartOptions} = this.state;

        return (
            <div>
                <HighchartsReact
                    highcharts={Highcharts}
                    options={chartOptions}
                />
            </div>
        )
    }
}


/*
*
* */
class TendencyChart extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            chartOptions: {
                title: {
                    text: ''
                },
                xAxis: {
                    categories: ''
                },
                series: [{
                    data: [1, 2, 3, 4, 6, 7, 9],
                }, {
                    data: [5, 6, 7, 8, 10, 11, 13],
                }, {
                    data: [9, 10, 11, 12, 14, 15, 17],
                }]
            },
            hoverData: null
        }
    }

    setHoverData = (e) => {
        this.setState({hoverData: e.target.category})
    }

    render() {
        const {chartOptions} = this.state;
        return (
            <div>
                <HighchartsReact
                    highcharts={Highcharts}
                    options={chartOptions}
                />
            </div>
        );
    }
}

class TableCell extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            is_input: this.props.input,
            cell_text: this.props.text
        }
    }

    handle_input = (e) => {
        this.setState({cell_text: e.target.value});
    }


    render() {
        if (this.state.is_input) {
            return (
                <th><input className="input is-primary" type="text" placeholder={this.props.text}
                           onChange={this.handle_input}/></th>
            );
        } else {
            return (
                <th onClick={this.props.change_to_input}>{this.state.cell_text}</th>
            );
        }
    }
}

class TableRow extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            line: this.props.line,
            cell_mode: new Array(this.props.col_num).fill(false),
        }
    }


    submit_change = () => {
        this.setState({cell_mode: new Array(this.props.col_num).fill(false)});
    }

    handle_change_to_input = (idx) => {
        let tmp = this.state.cell_mode;
        tmp[idx] = true;
        //this.setState({cell_mode:tmp});
    }

    render() {
        return (
            <tr>
                {this.state.line.map((col, col_idx) => {
                    return (
                        <TableCell input={this.state.cell_mode[col_idx]} text={col}
                                   change_to_input={this.handle_change_to_input(col_idx)}/>
                    );
                })}
                <th>
                    <div className={"button is-small is-primary"} onClick={this.submit_change}>提交</div>
                    <div className={"button is-small is-danger"}>删除</div>
                </th>
            </tr>
        );
    }
}

/*
* ordinary table
* */
class StatisticTable extends React.Component {
    constructor(props) {
        super(props);
        this.state = {}
    }

    input_table_text = () => {

    }


    render() {
        const {table} = this.props;
        if (table.line == null) return null;

        return (
            <table className={"table is-striped is-bordered is-fullwidth"}>
                <thead>
                <tr>
                    {this.props.table.headers.map((it) => {
                        return (<th>{it}</th>);
                    })}
                    <th></th>
                </tr>
                </thead>
                <tbody>
                {this.props.table.line.map((li, li_idx) => {
                    return (
                        <TableRow line={li} col_num={li.length}/>
                    );
                })}
                </tbody>
            </table>
        );
    }
}

/*
* Edit table with edit button
* */
class EditTable extends React.Component {
    constructor(props) {
        super(props);
    }

    render() {
        const {table} = this.props;
        if (table.line == null) return null;

        return (
            <div>
                <StatisticTable
                    table={table}
                />
            </div>
        );
    }
}


export {RankingChart, TendencyChart, StatisticTable};