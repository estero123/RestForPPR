import React from 'react';

export default class AddGroupForm extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            gid: "",
            groupName: ""
        };
        this.handleGroupNameChange = this.handleGroupNameChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);

    }

    handleGroupNameChange(e) {
        e.preventDefault();
        this.setState({
            groupName: e.target.value
        })
    }

    handleSubmit(e) {
        e.preventDefault();
        let group = JSON.stringify({
            gid: this.state.gid,
            groupName: this.state.groupName
        });

        fetch('http://localhost:8080/api/groups', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json;charset=UTF-8'
            },
            body: group
        }).then(() => {
            window.location.reload();
        })
    }


    render() {
        return (
            <div>
                <form onSubmit={this.handleSubmit} id="addGroup">
                    <div className="form-group">
                        <input className="form-control"
                               placeholder="Enter group name" type="text" onChange={this.handleGroupNameChange}
                               name="groupName"/>
                    </div>
                    <input type="submit" className="btn btn-primary" value="Submit"/>
                </form>
            </div>
        );
    }
}