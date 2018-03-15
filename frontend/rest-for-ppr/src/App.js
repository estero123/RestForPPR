import React, {Component} from 'react';
import {
    BrowserRouter as Router,
    Route
} from 'react-router-dom'
import ModifyStudentPage from './Components/Student/ModifyStudentPage';
import Homepage from './Components/Homepage';
import AddStudentPage from './Components/Student/AddStudentPage';
import Menu from "./Components/Menu";
import StudentListPage from "./Components/Student/StudentListPage";
import './Components/css/bootstrap.min.css';
import ModifyGroupPage from './Components/Group/ModifyGroupPage';
import AddGroupPage from './Components/Group/AddGroupPage';
import GroupListPage from './Components/Group/GroupListPage';
export default class App extends Component {


    render() {
        return (
            <div className="App">
                {/*<Header/>*/}
                <Router>
                    <div className="container-fluid">
                        <Menu />
                        <Route exact path="/" component={Homepage}/>
                        <Route exact path="/modify-student/:sid" component={ModifyStudentPage}/>
                        <Route exact path="/add-student" component={AddStudentPage}/>
                        <Route exact path="/student-list" component={StudentListPage}/>
                        <Route exact path="/modify-group/:gid" component={ModifyGroupPage}/>
                        <Route exact path="/add-group" component={AddGroupPage}/>
                        <Route exact path="/group-list" component={GroupListPage}/>
                    </div>
                </Router>
            </div>
        );
    }
}
