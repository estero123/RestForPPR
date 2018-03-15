import React from 'react';
import {
    Link
} from 'react-router-dom'

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

    render() {
        return (
            <tr className="table-secondary">
                <th scope="row">{this.state.sid}</th>
                <td>{this.state.firstName}</td>
                <td>{this.state.lastName}</td>
                <td>
                    {this.state.groups.map((dynamicData, key) =>
                        <div key={key}>
                           {dynamicData.groupName}
                        </div>
                    )}
                </td>
                <td>
                    <Link to={'/modify-student/' + this.state.sid}>
                        <button type="button" className="btn btn-success">Update</button>
                    </Link>
                    <button type="button" className="btn btn-danger" onClick={this.handleDelete}>Delete</button>
                </td>
            </tr>
        );
    }
}