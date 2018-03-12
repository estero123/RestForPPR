import React from 'react';
import './css/Student.css';
import Group from './Group';

export default class Student extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            sid: props.sid,
            firstName: props.firstName,
            lastName: props.lastName,
            groups: []
        };

        this.handleDelete = this.handleDelete.bind(this);
        this.handleModify = this.handleModify.bind(this);
    }

    componentDidMount() {
        fetch('http://localhost:8080/api/students/' + this.props.sid + '/groups/')
            .then((response) => response.json())
            .then((findresponse) => {
                this.setState({
                    groups: findresponse
                })
            })
    }

    handleDelete(e) {
        e.preventDefault();
        fetch('http://localhost:8080/api/students/' + this.state.sid + '/delete/', {
            method: 'DELETE',
        }).then(() => {
            window.location.reload();
        })
    }

    handleModify(e) {
        e.preventDefault();
        window.location.replace('http://localhost:3000/' + this.state.sid);

    }

    render() {
        return (
            <div className="student">
                <h3>{this.state.firstName} {this.state.lastName}</h3>
                <button onClick={this.handleDelete}>Usun</button>
                <button onClick={this.handleModify}>Modyfikuj</button>
                {this.state.groups.map((dynamicData, key) =>
                    <div key={key}>
                        <Group gid={dynamicData.gid} groupName={dynamicData.groupName}/>
                    </div>
                )}


            </div>
        );
    }
}