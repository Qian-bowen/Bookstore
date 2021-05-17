import React from "react";

const color=["is-primary","is-link","is-danger","is-warning","is-info"];
/*
* color_selector
* return color orderly based on color array and index
* */
function color_selector(index)
{
    let len=color.length;
    return color[index%len];
}

/*
* BookTag
* arg: array of string of tag name
* description: colorful tag
* */
export default class BookTag extends React.Component{
    constructor(props) {
        super(props);
    }

    make_tag=()=>{
        let tag_box=[];
        let tmp=this.props.tag.map((item,index)=>
            (
                <span className={"tag "+ color_selector(index)} key={index}>{item}</span>
            ));
        tag_box.push(tmp);
        return tag_box;
    }

    render()
    {
        return(
            <div className="tags are-small">
                {this.make_tag()}
            </div>
        );
    }
}