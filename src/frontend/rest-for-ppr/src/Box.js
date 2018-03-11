import React from 'react';
import './Box.css';
import Student from './Student'

export default class Box extends React.Component {
    constructor() {
        super();
        this.state = {
            students: []
          };
    }
    componentDidMount() { 
        fetch('http://localhost:8080/api/students/')
        .then((response) => response.json())
        .then((findresponse)=> {
            this.setState({
                students: findresponse
            })
        })
    }

    render() {
        return(
                <div className="box">

                    {this.state.students.map((dynamicData,key) =>
                    <div key={key} >
                        <Student firstName={dynamicData.firstName} sid={dynamicData.sid} lastName={dynamicData.lastName}/> 
                    </div>
                        )}
                </div>

        );
    }
}