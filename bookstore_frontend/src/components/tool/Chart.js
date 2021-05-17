import React from 'react';

import Highcharts from 'highcharts/highstock'
import HighchartsReact from 'highcharts-react-official'

/*
* ranking of one type of data
* */


class RankingChart extends React.Component{
    constructor(props) {
        super(props);
        this.state = {
            chartOptions: this.props.chart,
            hoverData: null,
        };
    }

    render()
    {
        const { chartOptions } = this.state;

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
        this.state={
            chartOptions:{
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
        this.setState({ hoverData: e.target.category })
    }

    render()
    {
        const { chartOptions } = this.state;
        return(
            <div>
                <HighchartsReact
                    highcharts={Highcharts}
                    options={chartOptions}
                />
            </div>
        );
    }
}

/*
* ordinary table
* */
class StatisticTable extends React.Component{
    constructor(props) {
        super(props);
        this.state={

        }
    }

    render()
    {
        const {table}=this.props;
        if(table.line==null) return null;

        return(
            <table className={"table is-striped is-bordered is-fullwidth"}>
                <thead>
                    <tr>
                        {this.props.table.tab_title.map((it)=>{
                            return (<th>{it}</th>);
                        })}
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    {this.props.table.line.map(li=>{
                        return(<tr>
                            {li.map(col=>{
                                return (<th>{col}</th>);
                            })}
                            <th>
                                <div className={"button is-small is-danger"}>删除</div>
                            </th>
                        </tr>);
                    })}
                </tbody>
            </table>
        );
    }
}

/*
* Edit table with edit button
* */
class EditTable extends React.Component{
    constructor(props) {
        super(props);
    }

    render()
    {
        const {table}=this.props;
        if(table.line==null) return null;

        return(
            <div>
                <StatisticTable
                    table={table}
                />
            </div>
        );
    }
}


export {RankingChart,TendencyChart,StatisticTable,EditTable};