import React from 'react';
import {
    Link
} from 'react-router-dom';
import './css/Menu.css';
export default class Menu extends React.Component {

    render() {
        return (
            <nav className="navbar navbar-expand-lg navbar-dark bg-primary">
                <Link to="/" className="navbar-brand">Rest-for-ppr</Link>
                <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarColor01"
                        aria-controls="navbarColor01" aria-expanded="false" aria-label="Toggle navigation">
                    <span className="navbar-toggler-icon"></span>
                </button>
                <div className="collapse navbar-collapse" id="navbarColor01">
                    <ul className="navbar-nav mr-auto">
                        <li className="nav-item">
                            <Link to="/" className="nav-link">HOME</Link>
                        </li>
                        <li className="nav-item">
                            <Link to="/add-student/" className="nav-link">ADD STUDENT</Link>
                        </li>
                        <li className="nav-item">
                            <Link to="/student-list/" className="nav-link">STUDENT LIST</Link>
                        </li>
                        <li className="nav-item">
                            <Link to="/add-group/" className="nav-link">ADD GROUP</Link>
                        </li>
                        <li className="nav-item">
                            <Link to="/group-list/" className="nav-link">GROUP LIST</Link>
                        </li>
                    </ul>
                </div>
            </nav>
        );
    }
}