import React from 'react';
import {
    Link
} from 'react-router-dom'

export default class Group extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            gid: props.gid,
            groupName: props.groupName
        };
        this.handleDelete = this.handleDelete.bind(this);
    }

    handleDelete(e) {
        e.preventDefault();
        fetch('http://localhost:8080/api/groups/' + this.state.gid + '/delete/', {
            method: 'DELETE',
        }).then(() => {
            window.location.reload();
        })
    }

    render() {
        return (
            <tr className="table-secondary">
                <th scope="row">{this.state.gid}</th>
                <td>{this.state.groupName}</td>
                <td>
                    <Link to={'/modify-group/' + this.state.gid}>
                        <button type="button" className="btn btn-success">Update</button>
                    </Link>
                    <button type="button" className="btn btn-danger" onClick={this.handleDelete}>Delete</button>
                </td>
            </tr>
        )
    }
}