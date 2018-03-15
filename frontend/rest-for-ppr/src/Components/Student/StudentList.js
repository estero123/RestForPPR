import React from 'react';
import Student from './Student'
export default class StudentList extends React.Component {
    constructor() {
        super();
        this.state = {
            students: []
        };
    }

    componentDidMount() {
        fetch('http://localhost:8080/api/students/')
            .then((response) => response.json())
            .then((findresponse) => {
                this.setState({
                    students: findresponse
                })
            })
    }

    render() {
        return (
                <table className="table table-hover">
                    <thead>
                    <tr className="table-secondary">
                        <th scope="col">sid</th>
                        <th scope="col">firstName</th>
                        <th scope="col">lastName</th>
                        <th scope="col">Groups</th>
                        <th scope="col">Actions</th>


                    </tr>
                    </thead>
                    <tbody>
                        {this.state.students.map((dynamicData, key) =>
                                <Student key={key} firstName={dynamicData.firstName} sid={dynamicData.sid}
                                         lastName={dynamicData.lastName}/>
                        )}
                    </tbody>
                </table>
        );
    }
}