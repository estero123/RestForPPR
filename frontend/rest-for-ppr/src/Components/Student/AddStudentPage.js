import React, {Component} from 'react';
import StudentList from './StudentList';
import AddStudentForm from './AddStudentForm';

export default class ModifyStudentPage extends Component {


    render() {
        return (
            <div>
                <AddStudentForm/>
                <StudentList/>
            </div>
        );
    }
}