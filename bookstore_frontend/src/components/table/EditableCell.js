import React from "react";

export default class EditableCell extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            col: this.props.col,
            text: this.props.text
        }
    }

    handle_input = (e) => {
        let value = e.target.value;
        this.setState({text: value});
        this.props.modify_text(this.state.col, value);
    }


    render() {
        if (this.props.input) {
            return (
                <th><input className="input is-primary" type="text" onChange={this.handle_input}/></th>
            );
        } else {
            let col_idx = this.props.col;
            return (
                <th onClick={() => this.props.change_to_input(col_idx)}>
                    {this.props.text}
                </th>
            );
        }
    }
}