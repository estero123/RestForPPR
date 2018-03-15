import React from 'react';

export default class AddStudentForm extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            sid: "",
            firstName: "",
            lastName: "",
            groups: [],
            groupsToSelect: []
        };
        this.handleFirstNameChange = this.handleFirstNameChange.bind(this);
        this.handleLastNameChange = this.handleLastNameChange.bind(this);
        this.handleGroupChange = this.handleGroupChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);

    }


    componentDidMount() {
        fetch('http://localhost:8080/api/groups/')
            .then((response) => response.json())
            .then((findresponse) => {
                this.setState({
                    groupsToSelect: findresponse
                })
            });
    }

    handleFirstNameChange(e) {
        e.preventDefault();
        this.setState({
            firstName: e.target.value
        })
    }

    handleLastNameChange(e) {
        e.preventDefault();
        this.setState({
            lastName: e.target.value
        })

    }


    handleGroupChange(e) {
        e.preventDefault();
        this.setState({
            groups: [...e.target.selectedOptions].map(o => o.value)
        });

    }

    handleSubmit(e) {
        e.preventDefault();
        let groupsToSend = [];
        for (let i in this.state.groups) {
            groupsToSend.push('http://localhost:8080/api/groups/' + this.state.groups[i]);
        }

        let student = JSON.stringify({
            sid: this.state.sid,
            firstName: this.state.firstName,
            lastName: this.state.lastName,
            groups: groupsToSend
        });

        fetch('http://localhost:8080/api/students', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json;charset=UTF-8'
            },
            body: student
        }).then(() => {
            window.location.reload();
        })
    }


    render() {
        return (
            <div>
                <form onSubmit={this.handleSubmit} id="addStudent">
                        <div className="form-group">
                                <input className="form-control"
                                       placeholder="Enter first name" type="text" onChange={this.handleFirstNameChange}
                                       name="firstName"/>
                        </div>
                        <div className="form-group">
                                <input className="form-control"
                                       placeholder="Enter last name" type="text" onChange={this.handleLastNameChange}
                                       name="lastName"/>
                        </div>
                        <div className="form-group">
                                <select multiple className="form-control" onChange={this.handleGroupChange}
                                        name="groups">
                                    {this.state.groupsToSelect.map((dynamicData, key) =>
                                        <option key={key}
                                                value={dynamicData.gid}>{dynamicData.groupName} : {dynamicData.gid}</option>
                                    )}
                                </select>
                        </div>
                        <input type="submit" className="btn btn-primary" value="Submit"/>
                </form>
            </div>
        );
    }
}