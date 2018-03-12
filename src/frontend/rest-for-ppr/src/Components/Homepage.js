import React, {Component} from 'react';
import logo from '../logo.svg';
import './css/Homepage.css';
import Box from './Box';
import StudentForm from './StudentForm';

export default class Homepage extends Component {


    render() {
        return (
            <div className="Homepage">
                <header className="Homepage-header">
                    <img src={logo} className="Homepage-logo" alt="logo"/>
                    <h1 className="Homepage-title">Welcome to React</h1>
                </header>
                <StudentForm sid={this.props.match.params.sid}/>
                <Box/>
            </div>
        );
    }
}
