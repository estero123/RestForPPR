import React from 'react';

export default class Group extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            gid: props.gid,
            groupName: props.groupName
        }
    }


    render() {
        return(
        <div>
        {this.state.groupName}
        </div>
        )}
}