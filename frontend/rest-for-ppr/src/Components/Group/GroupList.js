import React from 'react';
import Group from './Group'

export default class GroupList extends React.Component {
    constructor() {
        super();
        this.state = {
            groups: []
        };
    }

    componentDidMount() {
        fetch('http://localhost:8080/api/groups/')
            .then((response) => response.json())
            .then((findresponse) => {
                this.setState({
                    groups: findresponse
                })
            })
    }

    render() {
        return (
            <table className="table table-hover">
                <thead>
                <tr className="table-secondary">
                    <th scope="col">gid</th>
                    <th scope="col">groupName</th>
                    <th scope="col">Actions</th>


                </tr>
                </thead>
                <tbody>
                {this.state.groups.map((dynamicData, key) =>
                    <Group key={key} groupName={dynamicData.groupName} gid={dynamicData.gid}/>
                )}
                </tbody>
            </table>
        );
    }
}