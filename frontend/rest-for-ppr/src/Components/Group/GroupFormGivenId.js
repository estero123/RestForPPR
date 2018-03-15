import React from 'react';

export default class GroupForm extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            gid: props.gid,
            groupName: "",
        };
        this.handleGroupNameChange = this.handleGroupNameChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);

    }


    componentDidMount() {
        fetch('http://localhost:8080/api/groups/' + this.props.gid)
            .then((response) => response.json())
            .then((findresponse) => {
                console.log(findresponse);
                this.setState({
                    gid: findresponse.gid,
                    groupName: findresponse.groupName
                })

            });
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
            groupName: this.state.groupName,
        });

        fetch('http://localhost:8080/api/groups', {
            method: 'PUT',
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
                <form onSubmit={this.handleSubmit} id="addStudent">
                    <input type="text" name="gid" hidden={true} value={this.state.gid} readOnly/>
                    <div className="form-group">
                        <input className="form-control"
                               placeholder="Enter group name" type="text" onChange={this.handleGroupNameChange}
                               value={this.state.groupName}
                               name="groupName"/>
                    </div>


                    <input type="submit" className="btn btn-primary" value="Submit"/>
                </form>
            </div>
        );
    }
}