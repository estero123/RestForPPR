import React from 'react';
import {
    BrowserRouter as Router,
    Route,
    Link,
    Switch,
    Redirect
  } from 'react-router-dom'
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
        .then((findresponse)=> {
            this.setState({
                groupsToSelect: findresponse
            })
        })

        fetch('http://localhost:8080/api/students/' + this.props.sid)
        .then((response) => response.json())
        .then((findresponse)=> {
            this.setState({
                sid: findresponse.sid,
                firstName: findresponse.firstName,
                lastName: findresponse.lastName,
            })
            
        })

        fetch('http://localhost:8080/api/students/' + this.props.sid + '/groups/')
        .then((response) => response.json())
        .then((findresponse)=> {
            let groupsId = [];
            for(let i in findresponse) {
                groupsId.push(findresponse[i].gid);
            }
            console.log(groupsId);
            this.setState({
                groups: groupsId
            })
        })

        console.log(this.state.groups);
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
        console.log(this.state.groups.length);
          this.setState({
              groups: [...e.target.selectedOptions].map(o => o.value)
            }); 

    }

    handleSubmit(e) {
        e.preventDefault();
        let groupsToSend = [];
        for(let i in this.state.groups) {
            groupsToSend.push('http://localhost:8080/api/groups/' + this.state.groups[i]);
        }

        let student = JSON.stringify({
            sid: this.state.sid,
            firstName: this.state.firstName, 
            lastName: this.state.lastName,
            groups: groupsToSend
        })

        console.log(student);
        fetch('http://localhost:8080/api/students', {
            method: 'POST',
             headers: {
                'Content-Type': 'application/json;charset=UTF-8'
            },
            body: student
        }).then((response) => {
                console.log(response.json)
                window.location.reload();
        })
    }

    handleModifyStudent(e) {
        e.preventDefault();
        console.log('handleModify: ' + this.state.id);
    }


    render() {
        return(
            <div>
                <form onSubmit={this.handleSubmit} id="addStudent">
                <label>
                    sId:
                    <input type="text" name="sid" value={this.state.sid} readOnly/>
                </label>
                <label>
                    FirstName:
                    <input type="text" onChange={this.handleFirstNameChange} value={this.state.firstName} name="firstName"/>
                </label>
                <label>
                    LastName:
                    <input type="text" onChange={this.handleLastNameChange} value={this.state.lastName} name="lastName"/>
                </label>
                <label>
                    Groups: 
                    <select multiple onChange={this.handleGroupChange} value={this.state.groups} name="groups">
                    {this.state.groupsToSelect.map((dynamicData,key) =>
                        <option key={key} value={dynamicData.gid} defaultChecked={true} >{dynamicData.groupName} : {dynamicData.gid}</option>
                    )}
                    </select>
        </label>
                <input type="submit" value="Submit" />
                </form>
            </div>
        );
    }
}