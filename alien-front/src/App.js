import React, { Component } from 'react';
import axios from 'axios';
import {Table, Button, ModalHeader, Modal, ModalFooter, ModalBody, Label, Input, FormGroup} from 'reactstrap';

class App extends Component {
  state = {
    aliens: [],
    newAlienData: {
      name:'',
      type:'',
      planet:'',
      parent:''

    },
    newAlienModal: false
  }
  componentWillMount() {
    axios.get('http://localhost:8080/aliens').then((response) => {
      this.setState({
        aliens: response.data
      })
    });
  }

  addAlien(){
    axios.post('http://localhost:8080/alien', this.state.newAlienData).then((response) => {
      let {aliens} = this.state;

      aliens.push(response.data);

      this.setState({aliens, newAlienModal: false, newAlienData: {
        title:'',
        type: '',
        planet: '',
        parent: ''
      }});
    });
  }

  toggleNewAlienModal() {
    this.setState({
      newAlienModal: ! this.state.newAlienModal
    })
    // this.state.newAlienModal  = true;
  }
  render() {
    let aliens = this.state.aliens.map((alien) => {
    return (
      <tr key={alien.name}>
        <td>{alien.name}</td>
        <td>{alien.type}</td>
        <td>{alien.planet}</td>
        <td>{alien.parent}</td>
        <td>
          <Button color="success" size="sm" className="mr-2">Edit</Button>
          <Button color="danger" size="sm">Delete</Button>
        </td>
      </tr>
      )
    });
    return (
      <div className="App container">

      <Button color="primary" onClick={this.toggleNewAlienModal.bind(this)}>Add a new alien</Button>
      <Modal isOpen={this.state.newAlienModal} toggle={this.toggleNewAlienModal.bind(this)}>
        <ModalHeader toggle={this.toggleNewAlienModal.bind(this)}>Add a new alien</ModalHeader>
        <ModalBody>
          <FormGroup>
            <Label for="name">Name</Label>
            <Input id="name" value={this.state.newAlienData.name} onChange={(e) => {
              let {newAlienData} = this.state
              newAlienData.name = e.target.value;
              this.setState({newAlienData});
            }}/>
          </FormGroup>
          <FormGroup>
            <Label for="type">Type</Label>
            <Input id="type" value={this.state.newAlienData.type} onChange={(e) => {
              let {newAlienData} = this.state
              newAlienData.type = e.target.value;
              this.setState({newAlienData});
            }}/>
          </FormGroup>
          <FormGroup>
            <Label for="planet">Birth Planet</Label>
            <Input id="planet" value={this.state.newAlienData.planet} onChange={(e) => {
              let {newAlienData} = this.state
              newAlienData.planet = e.target.value;
              this.setState({newAlienData});
            }}/>
          </FormGroup>
          <FormGroup>
            <Label for="parent">Parent</Label>
            <Input id="parent" value={this.state.newAlienData.parent} onChange={(e) => {
              let {newAlienData} = this.state
              newAlienData.parent = e.target.value;
              this.setState({newAlienData});
            }}/>
          </FormGroup>
        </ModalBody>
        <ModalFooter>
          <Button color="primary" onClick={this.addAlien.bind(this)}>Add Alien</Button>{' '}
          <Button color="secondary" onClick={this.toggleNewAlienModal.bind(this)}>Cancel</Button>
        </ModalFooter>
      </Modal>

        <Table>
          <thead>
            <tr>
              <th>Name</th>
              <th>Type</th>
              <th>Planet</th>
              <th>Parent</th>
              <th>Actions</th>
            </tr>
          </thead>

          <tbody>
            {aliens}
          </tbody>
        </Table>
      </div>
    );
  }
}
export default App;
