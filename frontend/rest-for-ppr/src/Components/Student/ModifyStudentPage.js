import React, {Component} from 'react';
import StudentFormGivenId from './StudentFormGivenId';

export default class ModifyStudentPage extends Component {


    render() {
        return (
            <div className="ModifyStudentPage">
                <StudentFormGivenId sid={this.props.match.params.sid}/>
            </div>
        );
    }
}