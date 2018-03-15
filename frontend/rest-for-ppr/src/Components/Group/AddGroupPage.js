import React, {Component} from 'react';
import GroupList from './GroupList';
import AddGroupForm from './AddGroupForm';

export default class ModifyGroupPage extends Component {


    render() {
        return (
            <div>
                <AddGroupForm/>
                <GroupList/>
            </div>
        );
    }
}