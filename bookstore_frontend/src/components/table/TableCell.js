import React from "react";

export default class TableCell extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            col: this.props.col,
            text: this.props.text
        }
    }

    render() {
        return (
            <th>
                {this.props.text}
            </th>
        );
    }
}