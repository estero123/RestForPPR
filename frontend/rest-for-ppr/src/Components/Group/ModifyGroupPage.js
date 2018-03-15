import React, {Component} from 'react';
import GroupFormGivenId from './GroupFormGivenId';

export default class ModifyGroupPage extends Component {


    render() {
        return (
            <div>
                <GroupFormGivenId gid={this.props.match.params.gid}/>
            </div>
        );
    }
}