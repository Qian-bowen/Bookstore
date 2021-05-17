import React from 'react';
import ReactDOM from 'react-dom';

import SimpleNav from "../components/head/SimpleNav";
import BookFrame from "../components/BookFrame";


export default class CartView extends React.Component {
    render()
    {
        return(
            <div>
                <SimpleNav/>
                <BookFrame/>
            </div>


        );
    }
}