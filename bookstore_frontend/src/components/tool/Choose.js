import React from 'react';

class Selector extends React.Component {
    constructor(props) {
        super(props);
    }

    make_option=()=>{
        let option_box=[];
        let tmp=this.props.option.map((item,index)=>
            (
                <option key={index}>{item}</option>
            ));
        option_box.push(tmp);
        return option_box;
    }

    render()
    {
        return(
            <div className="select is-rounded">
                <select>
                    {this.make_option()}
                </select>
            </div>
        );
    }
}


export {Selector}