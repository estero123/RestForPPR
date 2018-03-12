import React, {Component} from 'react';
import {
    BrowserRouter as Router,
    Route,
    Switch
} from 'react-router-dom'
import Homepage from './Components/Homepage';

export default class App extends Component {


    render() {
        return (
            <Router>
                <Switch>
                    <Route exact path="/" component={Homepage}/>
                    <Route path="/:sid" component={Homepage}/>
                </Switch>
            </Router>
        );
    }
}
