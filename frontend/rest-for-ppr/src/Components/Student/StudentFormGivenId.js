import React from 'react';

export default class StudentForm extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            sid: props.sid,
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

        fetch('http://localhost:8080/api/students/' + this.props.sid)
            .then((response) => response.json())
            .then((findresponse) => {
                this.setState({
                    sid: findresponse.sid,
                    firstName: findresponse.firstName,
                    lastName: findresponse.lastName,
                })

            });

        fetch('http://localhost:8080/api/students/' + this.props.sid + '/groups/')
            .then((response) => response.json())
            .then((findresponse) => {
                let groupsId = [];
                for (let i in findresponse) {
                    groupsId.push(findresponse[i].gid);
                }
                this.setState({
                    groups: groupsId
                })
            })
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
            method: 'PUT',
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
                    <input type="text" name="sid" hidden={true} value={this.state.sid} readOnly/>
                    <div className="form-group">
                        <input className="form-control"
                               placeholder="Enter first name" type="text" onChange={this.handleFirstNameChange}
                               value={this.state.firstName}
                               name="firstName"/>
                    </div>
                    <div className="form-group">
                        <input className="form-control"
                               placeholder="Enter last name" type="text" onChange={this.handleLastNameChange}
                               value={this.state.lastName}
                               name="lastName"/>
                    </div>

                    <div className="form-group">
                        <select multiple className="form-control" value={this.state.groups}
                                onChange={this.handleGroupChange}
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